import java.io.*;
import java.util.*;


class Solution {
    static int len;
    static int[] dp;
    public int solution(int sticker[]) {
        int answer = 0;
        
        len = sticker.length;
        
        if(len == 1) return sticker[0];
        
        dp = new int[len];

        dp[0] = sticker[0]; // 처음 뽑은 경우
        dp[1] = dp[0];
        for(int i = 2; i<sticker.length-1; i++){
            dp[i] = Math.max(dp[i-2]+sticker[i], dp[i-1]);
        } 
        
        answer = dp[len-2];

        dp[0] = 0;
        dp[1] = sticker[1];
        for(int i = 2; i<sticker.length; i++){
            dp[i] = Math.max(dp[i-2]+sticker[i], dp[i-1]);
        }
        
        answer = Math.max(answer, dp[len-1]);
        
        return answer;
       
    }
}