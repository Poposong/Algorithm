import java.util.*;

class Solution {
	public int answer = 0 ;
    public int solution(int[] people, int limit) {
        Arrays.sort(people);

        int start = 0;
        int end = people.length - 1;
        
        while(start <= end) {
        	if(people[start] + people[end] <= limit) {
        		start++;
        		end--;
        		answer++;
        	}else {
        		end--;
        		answer++;
        	}
        }
           
        return answer;
    }
    
}