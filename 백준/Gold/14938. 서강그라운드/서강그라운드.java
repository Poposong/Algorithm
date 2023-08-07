import java.io.*;
import java.util.*;
/*
첫째 줄에는 지역의 개수 n (1 ≤ n ≤ 100)과 예은이의 수색범위 m (1 ≤ m ≤ 15), 길의 개수 r (1 ≤ r ≤ 100)이 주어진다.

둘째 줄에는 n개의 숫자가 차례대로  각 구역에 있는 아이템의 수 t (1 ≤ t ≤ 30)를 알려준다.

세 번째 줄부터 r+2번째 줄 까지 길 양 끝에 존재하는 지역의 번호 a, b, 그리고 길의 길이 l (1 ≤ l ≤ 15)가 주어진다.

5 5 4
5 7 8 2 3
1 4 5
5 2 4
3 2 3
1 2 3
*/

class Main {

	public static int MAX = Integer.MAX_VALUE;

	//public static int dx[] = {-1,0,1,0};
	//public static int dy[] = {0,-1,0,1};
	public static int n,m,r;
	public static int score[];
	public static int d[];
	public static ArrayList<ArrayList<dot>> graph;
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String s[] = br.readLine().split(" ");
		n = Integer.parseInt(s[0]); m = Integer.parseInt(s[1]); r= Integer.parseInt(s[2]);
		score = new int[n+1];
		
		s = br.readLine().split(" ");
		for(int i =0; i<n; i++)
			score[i+1] = Integer.parseInt(s[i]);
		graph = new ArrayList<>();
		for(int i =0; i<n+1; i++) 
			graph.add(new ArrayList<>());
		for(int i =0; i<r; i++) {
			s = br.readLine().split(" ");
			graph.get(Integer.parseInt(s[0])).add(new dot(Integer.parseInt(s[1]), Integer.parseInt(s[2])));
			graph.get(Integer.parseInt(s[1])).add(new dot(Integer.parseInt(s[0]), Integer.parseInt(s[2])));
		}
		int sum = Integer.MIN_VALUE;
		for(int i =1; i<n+1; i++) {
			sum = Math.max(sum, Dijkstra(i));
		}
		System.out.println(sum);
		
		
	}
	
	public static int Dijkstra(int start) {
		PriorityQueue<dot> que = new PriorityQueue<>();
		d = new int[n+1];
		Arrays.fill(d, MAX);
		d[start] = 0;
		que.offer(new dot(start, 0));
		
		while(!que.isEmpty()) {
			dot dots = que.poll();
			
			
			for(int i =0; i<graph.get(dots.num).size(); i++) {
				int next_num = graph.get(dots.num).get(i).num;
				int next_dis = graph.get(dots.num).get(i).dis;
				
				if(next_dis + d[dots.num] < d[next_num]) {
					d[next_num] = next_dis + d[dots.num];
					que.offer(new dot(next_num, d[next_num]));
				}
				
			}
		}
		
		//System.out.println(Arrays.toString(d));
		return total(d);
		
	}
	public static int total(int d[]) {
		int res = 0;
		for(int i =1; i<n+1; i++) {
			if(d[i]<=m)
				res += score[i];
		}
		return res;
	}
	
	public static class dot implements Comparable<dot>{
		int num;
		int dis;
		
		public dot(int num,int dis) {
			this.num = num;
			this.dis = dis;
		}

		@Override
		public int compareTo(dot o) {
			return this.dis - o.dis;
		}
	}
	
	

}