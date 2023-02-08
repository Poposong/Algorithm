import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
 

public class Main {



    
    public static void main(String[] args) throws IOException{
    		
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);
        
        int[] number = new int[N];
        int[] dp = new int[N];
        
        str = br.readLine().split(" ");
        
        dp[0] = number[0] = Integer.parseInt(str[0]);

        for(int i =1; i<N; i++) {
        	number[i] = Integer.parseInt(str[i]);
        	dp[i] = number[i] + dp[i-1];
        }
        
        for(int i =0 ;i<M; i++) {
        	str = br.readLine().split(" ");
        	int l1 = Integer.parseInt(str[0]) - 1;
        	int l2 = Integer.parseInt(str[1]) - 1;
        	sb.append(dp[l2] - dp[l1] + number[l1]+"\n");
        }
        
        System.out.println(sb.toString());
        
 
    }
    
 

     
}