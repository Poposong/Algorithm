import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        String[] str = br.readLine().split(" ");
        int[] number = new int[N+1]; // 1~N까지
        for(int i = 1; i<N+1; i++){
            number[i] = Integer.parseInt(str[i-1]);
        }

        boolean[][] dp = new boolean[N+1][N+1]; // dp[i][j]가 true라면 i~j범위까지 팰린드롬이라는 말이다.
        // dp 세팅하기

        // 1. 범위가 1개인 경우
        for(int i = 1; i<N+1; i++){
            dp[i][i] = true;
        }

        // 2. 범위가 2개인 경우
        for(int i = 1; i<N; i++){
            if(number[i] == number[i+1]){
                dp[i][i+1] = true;
            }
        }

        // 3. 범위가 3이상인 경우
        for(int i = 3; i<N+1; i++){
            for(int j = 1; j<= N-i+1; j++){
                if(number[j] == number[i+j-1] && dp[j+1][i+j-2]){
                    dp[j][i+j-1] = true;
                }
            }
        }

        int M = Integer.parseInt(br.readLine());
        while(M-- != 0){
            str = br.readLine().split(" ");
            sb.append(dp[Integer.parseInt(str[0])][Integer.parseInt(str[1])] ? 1 : 0).append("\n");
        }
        System.out.println(sb.toString());

    }
}
