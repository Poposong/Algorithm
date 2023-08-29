import java.util.*;


// C, C#, D, D#, E, F, F#, G, G#, A, A#, B 

class Solution {
	public String m1[] = {"C#","D#","F#","G#","A#"};
	public String m2[] = {"c","d","f","g","a"};
    public String solution(String m, String[] musicinfos) {
        String answer = "";

        
        String Melody = replace(m);
        String Music_arr[][] = replace(musicinfos);
        
        //running_time이 긴 순서대로 2차원 순서를 정렬
        Arrays.sort(Music_arr, new Comparator<String[]>() {

			@Override
			public int compare(String[] music1, String[] music2) {
				return -((Integer.parseInt(music1[0])) - (Integer.parseInt(music2[0])));
			}
		});
        
        for(int i =0; i<Music_arr.length; i++) {
        	if(Music_arr[i][2].contains(Melody))
        		return Music_arr[i][1];
        }
        return "(None)";
    }
    
    public String replace(String m) {
    	for(int i =0; i<m1.length; i++) {
    		m = m.replaceAll(m1[i], m2[i]);
    	}
    	return m;
    }
    
    public String[][] replace(String[] musicinfos){
    	
    	String[][] result = new String[musicinfos.length][3];
    	
    	for(int i =0; i<musicinfos.length; i++) {
    		String s[] = musicinfos[i].split(",");
    		
    		String time1 = s[0];
    		String time2 = s[1];
    		String title = s[2];
    		String music_temp = s[3];
    		String music = "";
    		
    		for(int j =0; j<m1.length; j++) {
    			music_temp = music_temp.replaceAll(m1[j], m2[j]);
    		}
    		
    		int music_len = music_temp.length();
    		int running_time = time(time1,time2);
    		int music_idx = 0;
    		
    		for(int j=0; j<running_time; j++) {
    			music += music_temp.charAt(music_idx++);
    			music_idx = music_idx % music_len;
    		}
    		
    		result[i][0] = String.valueOf(running_time);
    		result[i][1] = title;
    		result[i][2] = music;
    	}
    	
    	return result;
    }
    
    public int time(String time1, String time2) {
    	int hour = Integer.parseInt(time2.substring(0,2)) - Integer.parseInt(time1.substring(0,2));
    	int minute = Integer.parseInt(time2.substring(3,5)) - Integer.parseInt(time1.substring(3,5));
    	return hour*60 + minute;
    }
}