import java.io.*;
import java.util.*;
/**
n : 1~60,000까지의 자연수
*/

class Solution {
    public int solution(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for(int i = 2; i<n; i++){
            dp[i] = (dp[i-1]+dp[i-2])%1000000007;
        }
        
        return dp[n-1];
    }
}