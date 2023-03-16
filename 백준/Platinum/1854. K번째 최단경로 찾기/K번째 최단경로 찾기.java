import java.io.*;
import java.util.*;

public class Main {

	public static ArrayList<ArrayList<vector>> graph;
	public static Queue<Integer>[] d;

	public static int n,m,k;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String s[] = br.readLine().split(" ");
		n = Integer.parseInt(s[0]);
		m = Integer.parseInt(s[1]);
		k = Integer.parseInt(s[2]);
		
		d = new PriorityQueue[n+1]; // 우선순위 큐 정의
		
		graph = new ArrayList<>();

		for(int i =0; i<n+1; i++) {
			graph.add(new ArrayList<>());
			d[i] = new PriorityQueue<Integer>(Collections.reverseOrder()); // 내림차순으로 정렬
		}
		
		for(int i =0; i<m; i++) {
			s = br.readLine().split(" ");
			graph.get(Integer.parseInt(s[0])).add(new vector(Integer.parseInt(s[1]), Integer.parseInt(s[2])));
		}
		
		Dijkstra(1);
		for(int i =1; i<n+1; i++) {
			if(d[i].size() == k)
				System.out.println(d[i].peek()); 
			else
				System.out.println(-1);
		}
		
	}
	
	public static void Dijkstra(int start) {
		PriorityQueue<vector> que = new PriorityQueue<>();
		que.add(new vector(start, 0));
		d[start].add(0);
		
		while(!que.isEmpty()) {
			vector vec1 = que.poll();
			
			for(vector vec2 : graph.get(vec1.num)) {
				if(d[vec2.num].size() < k) {
					d[vec2.num].add(vec1.weight + vec2.weight);
					que.offer(new vector(vec2.num, vec1.weight + vec2.weight));
				}else if(d[vec2.num].peek() > vec1.weight + vec2.weight) { // d[vec2.num].size() >= k 이고 k번째 수가 더 작게 정의될 수 있는 경우
					d[vec2.num].poll();
					d[vec2.num].add(vec1.weight + vec2.weight);
					que.offer(new vector(vec2.num, vec1.weight + vec2.weight));
				}
			}
		}
	}
	
	public static class vector implements Comparable<vector>{
		int num;
		int weight;
		
		public vector(int num, int weight) {
			this.num = num;
			this.weight = weight;
		}

		@Override
		public int compareTo(vector o) {
			return this.weight - o.weight;
		}
	}
}