import java.io.*;
import java.util.*;

public class Main {
    static boolean result;
    static String strA, strB, strC;

    static boolean[][] dp;

    static Queue<String> queue;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());



        String[] str;
        for(int i = 1; i<n+1; i++){
            str = br.readLine().split(" ");
            strA = str[0];
            strB = str[1];
            strC = str[2];
            dp = new boolean[strA.length()+1][strB.length()+1];

            for(int j = 1; j<strA.length()+1; j++){
                if(strA.charAt(j-1) == strC.charAt(j-1)){
                    dp[j][0] = true;
                }else{
                    break;
                }
            }

            for(int j = 1; j<strB.length()+1; j++){
                if(strB.charAt(j-1) == strC.charAt(j-1)){
                    dp[0][j] = true;
                }else{
                    break;
                }
            }

            for(int j = 1; j<strA.length()+1; j++){
                for(int k = 1; k<strB.length()+1; k++){
                    if(dp[j-1][k] && strA.charAt(j-1) == strC.charAt(j+k-1)){
                        dp[j][k] = true;
                    }
                    if(dp[j][k-1] && strB.charAt(k-1) == strC.charAt(j+k-1)){
                        dp[j][k] = true;
                    }
                }
            }

            
            sb.append("Data set ").append(i).append(dp[strA.length()][strB.length()] ? ": yes" : ": no").append("\n");
        }

        System.out.println(sb.toString());
    }


}