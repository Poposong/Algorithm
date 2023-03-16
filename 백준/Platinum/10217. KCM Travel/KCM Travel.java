import java.io.*;
import java.util.*;

public class Main {

	public static int N, M, K;
	public static int dp[][];
	public static ArrayList<ArrayList<vector>> graph;
	public static long result = Long.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		//System.out.println(Integer.MAX_VALUE);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t= 0; t<T; t++) {
			result = Integer.MAX_VALUE;
			String s[]  = br.readLine().split(" ");
			N = Integer.parseInt(s[0]); M = Integer.parseInt(s[1]); K = Integer.parseInt(s[2]);
			graph = new ArrayList<>();
			dp = new int[N+1][M+1];
			for(int i =0; i<N+1; i++) {
				Arrays.fill(dp[i], Integer.MAX_VALUE);
				graph.add(new ArrayList<>());
			}
			
			for(int i =0; i<K; i++) {
				s = br.readLine().split(" ");
				graph.get(Integer.parseInt(s[0])).add(new vector(Integer.parseInt(s[1]), Integer.parseInt(s[2]), Integer.parseInt(s[3])));
			}
			
			
			PriorityQueue<vector> que = new PriorityQueue<>();
			que.offer(new vector(1, 0, 0));
			dp[1][0] = 0;
			while(!que.isEmpty()) {
				vector v1 = que.poll();
				if(v1.arrive == N) {
					result = Math.min(v1.time, result);
					break;
				}
				for(vector v2 : graph.get(v1.arrive)) {
					int sum_cost = v1.cost + v2.cost;
					if(sum_cost > M) continue;
					
					int sum_time = v1.time + v2.time;
					
					if(dp[v2.arrive][sum_cost] >  sum_time) {  // DP[i][j] = 시작 공항부터 i 공항까지 j의 비용을 소비하며 이동한 시간 중 최소 시간 
						dp[v2.arrive][sum_cost] = sum_time;
						que.offer(new vector(v2.arrive, sum_cost, sum_time));
					}
					
					
				}
			}
			
			
			
			if(result == Integer.MAX_VALUE)
				sb.append("Poor KCM\n");
			else
				sb.append(String.valueOf(result)+"\n");
			
		}
		System.out.println(sb.toString());
		
	}


	public static class vector implements Comparable<vector> {
		int arrive;
		int cost;
		int time;

		public vector(int arrive, int cost, int time) {
			this.arrive = arrive;
			this.cost = cost;
			this.time = time;
		}

		@Override
		public int compareTo(vector o) {
			if(this.time == o.time) {
				return this.cost - o.cost;
			}else
				return this.time - o.time;
			
		}

	}
}
