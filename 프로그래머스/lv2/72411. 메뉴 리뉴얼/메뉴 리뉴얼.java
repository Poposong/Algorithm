import java.util.*;


class Solution {
	
	public HashMap<String,Integer> map;
	public int max_num=0;
    public String[] solution(String[] orders, int[] course) {
    	String[] answer = {};
    	PriorityQueue<String> pq = new PriorityQueue<>();
    	for(int i =0; i<course.length; i++) {
    		map = new HashMap<>();
    		max_num=0;
    		
    		for(int j =0; j<orders.length; j++) {
    			perm(0,"",course[i],0,orders[j]);
    		}
    		
    		for(String key : map.keySet()) {
    			if(map.get(key) == max_num && map.get(key) > 1)
    				pq.offer(key);
    		}
    	}
    	
    	answer = new String[pq.size()];
    	int t=0;
    	while(!pq.isEmpty())
    		answer[t++] = pq.poll();
    	
        return answer;
    }
    
    public void perm(int cnt, String str, int target_num, int index, String course_str) {
    	
    	if(cnt == target_num) {
    		char c_arr[] = str.toCharArray();
    		Arrays.sort(c_arr);
    		String temp="";
    		for(char c : c_arr)
    			temp += c;
    		map.put(temp, map.getOrDefault(temp, 0) + 1);
    		max_num = Math.max(max_num, map.get(temp));
    		return;
    	}
    	
    	for(int i =index; i<course_str.length(); i++) {
    		char c = course_str.charAt(i);
    		perm(cnt+1, str + c,target_num, i+1,course_str);
    	}
    }
   
}