import java.io.*;
import java.util.*;

class Solution {
    
    static int[][] dp;
    
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        
        dp = new int[n][m];
        
        for(int[] value : puddles){
            dp[value[1]-1][value[0]-1] = -1;
        }
        
        for(int i = 1; i<n; i++){
            dp[i][0] = (dp[i][0] == -1 || dp[i-1][0] == -1) ? -1 : 1;
        }
        for(int j = 1; j<m; j++){
            dp[0][j] = (dp[0][j] == -1 || dp[0][j-1] == -1) ? -1 : 1;
        }
        
        int num1, num2;
        for(int i = 1; i<n; i++){
            for(int j =1; j<m; j++){
                if(dp[i][j] != -1){
                    num1 = dp[i][j-1] == -1 ? 0 : dp[i][j-1];
                    num2 = dp[i-1][j] == -1 ? 0 : dp[i-1][j];
                    
                    dp[i][j] = (num1+num2)%1000000007;
                }
            }
        }
        
        
        
        
        
        return dp[n-1][m-1] == -1 ? 0 : dp[n-1][m-1];

    }
    
  
}