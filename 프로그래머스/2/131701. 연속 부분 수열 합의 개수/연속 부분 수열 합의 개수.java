import java.io.*;
import java.util.*;

class Solution {

    public int solution(int[] elements) {
        int answer = 0;
        boolean[] visited = new boolean[998001];
        Arrays.fill(visited, false);
        
        int[] dp = new int[elements.length*2+1];
        dp[0] = 0;
        dp[1] = elements[0];
        for(int i = 2; i<dp.length; i++){  
            if(i <= elements.length){
                dp[i] = dp[i-1]+elements[i-1];
            }else{
                dp[i] = dp[i-1]+elements[i-elements.length-1];
            }
        }
        
       // System.out.println(Arrays.toString(dp));
        
        for(int i = 1; i<=elements.length; i++){
            for(int j = 0; j<elements.length; j++){
                int num = dp[j+i] - dp[j];
                if(!visited[num]){
                    visited[num] = true;
                    answer++;
                }
            }
        }
        
        
        
        return answer;
    }
}