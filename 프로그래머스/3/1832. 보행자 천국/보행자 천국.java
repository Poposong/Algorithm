import java.io.*;
import java.util.*;
/**
0 : 자유롭게 지나갈 수 있음
1 : 자동차의 통행이 금지되어 지나갈 수 없음
2 : 좌회전이나 우회전이 금지됨

방향이 다를 때 다른 곳으로 가는 경우에는 방향에 따른 경우의 수를 나눠야 한다.
*/
class Solution {
    static int MOD = 20170805;
    static int answer = 0;
    static int[] dx = {0, 1};
    static int[] dy = {1, 0};
    public int solution(int m, int n, int[][] cityMap) {
        int[][][] dp = new int[2][m+1][n+1]; // [0]번째는 오른쪽, [1]번째는 왼쪽
        
        dp[0][1][1] = 1;
        dp[1][1][1] = 1;
        
        for(int i = 1; i<m+1; i++){
            for(int j = 1; j<n+1; j++){
                if(i == 1 && j == 1) continue;
                if(cityMap[i-1][j-1] == 0){
                    dp[0][i][j] = (dp[0][i][j-1]+dp[1][i-1][j])%MOD; // 오른쪽 방향에서 오는것 + 아래쪽 방향에서 오는 것
                    dp[1][i][j] = (dp[0][i][j-1]+dp[1][i-1][j])%MOD;
                }else if(cityMap[i-1][j-1] == 1){
                    dp[0][i][j] = 0;
                    dp[1][i][j] = 0;
                }else{ // cityMap[i-1][j-1] == 2
                    // 오른쪽 -> 오른쪽, 아래->아래
                    dp[0][i][j] = dp[0][i][j-1];
                    dp[1][i][j] = dp[1][i-1][j];
                }
            }
        }
        
        return dp[0][m][n];
    }
    
}
