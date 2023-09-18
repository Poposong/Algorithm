import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2];
        
        
        while(s.length() != 1) {
        	
        	int len = s.length(); // 0을 제거하기 전의 1의 길이
        	
        	s = s.replaceAll("0", "");
        	
        	int one_cnt = s.length(); // 0을 제거하고 남은 1의 길이
        	answer[1] += (len - one_cnt); // (0을 제거하기 전의 1의 길이) - (0의 제거하고 남은 1의 길이) => 0의 길이
        	answer[0]++;// 이진 변환을 반복한 횟수
        	
        	String temp = "";
        	
        	// one_cnt를 2진법으로 표현한 문자열을 구한다.
        	while(one_cnt > 0) {
        		temp = (one_cnt % 2) + temp;
        		one_cnt = one_cnt/2;
        	}
        	s = temp;
        }
        
        return answer;
    }
}