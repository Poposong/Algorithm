import java.io.*;
import java.util.*;
class Solution {
    public int solution(int n, int[] money) {
        int answer = 0;
        Arrays.sort(money);
        
        long[] dp = new long[n+1];
        
        // 초기화(제일 작은 화폐로 지불할 수 있는 경우)
        for(int i = 0; i<n+1; i++){
            if(i%money[0] == 0){
                dp[i] = 1;
            }
        }
        
        //세팅
        for(int i = 1; i<money.length ; i++){
            for(int j = money[i]; j<=n; j++){
                dp[j] += dp[j-money[i]];
            }
        }
        
        return (int)(dp[n]%1000000007);
    }
}