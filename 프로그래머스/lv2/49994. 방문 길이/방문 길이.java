import java.util.*;

class Solution {
    public int solution(String dirs) {
        int answer = 0;
        int x=0, y=0;
   
        ArrayList<String> list = new ArrayList<>();
        
        for(int i =0; i<dirs.length(); i++) {
        	char c = dirs.charAt(i);
        	int next_x = x;
        	int next_y = y;
        	if(c == 'U') 
        		next_y++;
        	else if(c == 'D')
        		next_y--;
        	else if(c == 'L')
        		next_x--;
        	else if(c == 'R')
        		next_x++;
        	
        	if(-5<=next_x && next_x <= 5 && -5 <= next_y && next_y <= 5) {
        		String str1 = String.valueOf(x+" "+y+" "+next_x+" "+next_y);
        		String str2 = String.valueOf(next_x+" "+next_y+" "+x+" "+y);
        		x = next_x;
        		y = next_y;
        		if(!list.contains(str1) && !list.contains(str2)) {
        		
        			list.add(str1);
        			list.add(str2);
        			answer++;	
        		}
        		
        		
        	}
        }
        return answer;
    }
}