import java.util.*;

class Solution {
    public String solution(String s) {
        String answer = "";
        if('a'<= s.charAt(0) && s.charAt(0) <= 'z' )
        	answer += String.valueOf(s.charAt(0)).toUpperCase();
        else
        	answer += String.valueOf(s.charAt(0));
        
        for(int i =1; i<s.length(); i++) {
        	if(s.charAt(i-1) == ' ') {
        		if('0'<=s.charAt(i) && s.charAt(i)<= '9')
        			answer += String.valueOf(s.charAt(i));
        		else
        			answer += String.valueOf(s.charAt(i)).toUpperCase();
        	}else {
        		if('0'<=s.charAt(i) && s.charAt(i)<= '9')
        			answer += String.valueOf(s.charAt(i));
        		else
        			answer += String.valueOf(s.charAt(i)).toLowerCase();
        	}
        }
        
        return answer;
    }
}