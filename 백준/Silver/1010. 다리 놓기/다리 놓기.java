import java.io.*;
import java.util.*;


public class Main {

	
	public static int N,M, dp[][] = new int[30][30];
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
	
		int T = Integer.parseInt(br.readLine());
		String str[];
		
		while(T--!=0) {
			str = br.readLine().split(" ");
			N = Integer.parseInt(str[0]);
			M = Integer.parseInt(str[1]);
			sb.append(combi(M,N)+"\n");
		}
		System.out.println(sb.toString());
		
		
	}
	static int combi(int n, int r) {
		
		if(dp[n][r]>0)
			return dp[n][r];
		
		if(r == n || r == 0)
			return dp[n][r] = 1;
		
		return dp[n][r]= combi(n-1,r-1) + combi(n-1,r);
	}

	

}
