import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int[] dp = new int[str.length()+1];
        dp[0] = 1;

        for(int i = 1; i<str.length()+1; i++){
            int firstNumber = str.charAt(i-1) - '0';
            if(1<= firstNumber && firstNumber <= 9){
                dp[i] = dp[i-1];
                dp[i] %= 1000000;
            }

            if(i == 1) continue;

            int secondNumber = str.charAt(i-2) - '0';

            if(secondNumber == 0){
                if(firstNumber == 0){ // 00이 연속으로 나왔다.
                    System.out.println(0);
                    return;
                }
                continue;
            };

            int sumNumber = secondNumber*10 + firstNumber;

            if(10 <= sumNumber && sumNumber <= 26){
                dp[i] += dp[i-2];
                dp[i] %= 1000000;
            }
        }

        System.out.println(dp[str.length()]);
    }
}