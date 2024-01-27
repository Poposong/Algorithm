import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if(N%2 != 0){
            System.out.println(0);
        }else{
            int[] dp = new int[N+1];
            dp[0]=1;
            for(int i = 2; i<N+1; i+=2){ // 이전의 수에 3가지의 경우가 존재하는 경우, 특이한 케이스는 전전단계에 붙는 경우
                dp[i] = dp[i-2]*3;
                for(int j = i-4; j>=0; j-=2){
                    dp[i] += dp[j]*2;
                }
            }
            System.out.println(dp[N]);
        }
    }
}