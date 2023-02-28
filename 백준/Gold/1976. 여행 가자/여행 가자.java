import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	
	public static int N,M;
	
	public static ArrayList[] graph;
	
	public static boolean[][] visited;
	
	public static int count;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		String[] str;
		
		graph = new ArrayList[N+1];
		
		for(int i =0; i<N+1; i++)
			graph[i] = new ArrayList<Integer>();
		
		for(int i = 1; i<N+1; i++) {
			str = br.readLine().split(" ");
			for(int j = 0; j<str.length; j++) {
				if(str[j].equals("1")) {
					graph[i].add(j+1);
				}
			}
		}
		
	
		
		
		visited = new boolean[N+1][2];
		
		count = 0;
		
		int num = 0;
		str = br.readLine().split(" ");
		for(int i = 0; i<M; i++) {
			num = Integer.parseInt(str[i]);
			if(!visited[num][0])
				count++;
			visited[num][0] = true; 
		}
		
		bfs(num);
		System.out.println(count==0? "YES" : "NO");
	}
	
	static void bfs(int start) {
		Queue<Integer> queue = new LinkedList<>();
		
		queue.offer(start);
		visited[start][0] = false;
		visited[start][1] = true;
		
		count--;
		int number;
		while(!queue.isEmpty()) {
			number = queue.poll();
		
			int value;
			for(int i =0; i<graph[number].size(); i++) {
				value = (int) graph[number].get(i);
				if(!visited[value][1]) {
					if(visited[value][0]) {
						visited[value][0] = false;
						count--;
					}
					visited[value][1] = true;
					queue.add(value);
				}
			}
			

		}
	}
}