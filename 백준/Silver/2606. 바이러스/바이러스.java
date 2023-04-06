import java.io.*;
import java.util.*;

public class Main {
	static LinkedList<Integer>[] list;
	static boolean[] visited;

	static int bfs(int s) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(s);
		visited[s] = true;
		int cnt = 0;
		while (!q.isEmpty()) {
			int now = q.poll();
			cnt++;
			for (Integer it : list[now]) {
				if (!visited[it]) {
					visited[it] = true;
					q.add(it);
				}
			}
		}
		return cnt;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		list = new LinkedList[N + 1];
		visited = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			list[i] = new LinkedList<Integer>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			list[from].add(to);
			list[to].add(from);
		}
		// input end

		System.out.println(bfs(1) - 1);
	}

}