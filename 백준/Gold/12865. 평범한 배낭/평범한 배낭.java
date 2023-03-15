import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

class Node{
	int w;
	int v;
	
	public Node(int w, int v) {
		this.w = w;
		this.v = v;
	}
}
public class Main {
	public static int N, K;
	
	public static int[] dp;
		
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		K = Integer.parseInt(str[1]);

		dp = new int[K+1];
		
		Arrays.fill(dp, 0);
	
		for(int i =0; i<N; i++) {
			str = br.readLine().split(" ");
			bagCheck(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
			//System.out.println(Arrays.toString(dp));
		}

		System.out.println(dp[K]);
	
	}
	
	static void bagCheck(int w, int v) {
		
		int cost;
		for(int i = K; i>=w; i--) {
			cost = v + dp[i-w];
			//System.out.println("cost : "+cost);
			if(cost > dp[i]) {
				dp[i] = cost;
			}
		}
		
	}
	

}