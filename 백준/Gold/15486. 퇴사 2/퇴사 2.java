import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] t = new int[n+2]; // 시간
        int[] p = new int[n+2]; // 비용

        for(int i = 1; i<n+1; i++){
            String[] str = br.readLine().split(" ");
            t[i] = Integer.parseInt(str[0]);
            p[i] = Integer.parseInt(str[1]);
        }

        int[] dp = new int[n+2];
        int max = 0;
        for(int i = 1; i<n+2; i++){

            // 현재 좌표까지의 더 큰 값이 있으면 최대값을 갱신한다.
            if(max < dp[i]){
                max = dp[i];
            }

            // System.out.println(i+"::"+max+"=>"+Arrays.toString(dp)); // 현재까지의 최대값을 이용한다.

            int next = i + t[i];
            if(next < n+2){
                dp[next] = Math.max(dp[next], max + p[i]);
            }
        }

       System.out.println(max);




    }
}