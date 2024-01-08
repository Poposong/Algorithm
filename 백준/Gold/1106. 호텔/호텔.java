package Baekjoon;
import java.io.*;
import java.util.*;
/**
 * C, N(1<=C<=1,000, 1<=N<=20)
 * N개의 줄에는 각 도시에서 홍보할 때 대는 비용과 그 비용으로 얻을 수 있는 고객의 수 (1<= <=100) cost, person
 * */
public class BJ_1106_호텔 {
    static int result = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int C = Integer.parseInt(str[0]);
        int N = Integer.parseInt(str[1]);

        int[] dp = new int[C+101];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0]=0;

        int min = Integer.MAX_VALUE;
        for(int i = 0; i<N; i++){
            str = br.readLine().split(" ");
            int cost = Integer.parseInt(str[0]);
            int person = Integer.parseInt(str[1]);
            for(int j = person; j<dp.length; j++){
                if(dp[j-person] != Integer.MAX_VALUE && dp[j-person]+cost < dp[j]){
                    dp[j] = dp[j-person]+cost;
                }
            }
        }

        for(int i = C; i<dp.length; i++){
            result = Math.min(result, dp[i]);
        }
        System.out.println(result);
    }
}
