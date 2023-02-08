import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;


public class Main{
	
    public static void main(String[] args) throws Exception {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String s[]  = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]);
		int M = Integer.parseInt(s[1]);
		int arr[][] = new int[N+1][N+1];
		int dp[][] = new int[N+1][N+1];
		
		for(int i =1; i<N+1; i++) {
			s = br.readLine().split(" ");
			for(int j = 1; j<N+1; j++) {
				arr[i][j] = Integer.parseInt(s[j-1]);
				dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + arr[i][j];
			}	
		}
		/*
		 for(int i =1; i<N+1; i++) {
			for(int j = 1; j<N+1; j++) {
				System.out.print(dp[i][j]+" ");
			}
			System.out.println();
		}
		 */
		
		for(int t = 0; t<M; t++) {
			s = br.readLine().split(" ");
			int x1 = Integer.parseInt(s[0]);
			int y1 = Integer.parseInt(s[1]);
			int x2 = Integer.parseInt(s[2]);
			int y2 = Integer.parseInt(s[3]);
			int sum = 0;
			
			sum = dp[x2][y2] - dp[x2][y1-1] - dp[x1-1][y2] + dp[x1-1][y1-1];
			
			sb.append(sum+"\n");
		}
		System.out.println(sb);
	

		
    }// main()
}