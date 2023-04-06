import java.io.*;
import java.util.*;

public class Main {
	static int N, M, V;
	static List<List<Integer>> list = new ArrayList<>();
	static Queue<Integer> queue = new LinkedList<Integer>();
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();

	static void dfs(int n) {
		sb.append(n + " ");

		for (Integer tmp : list.get(n)) {
			if (!visited[tmp]) {
				visited[tmp] = true;
				dfs(tmp);
			}
		}
	}

	static void bfs(int n) {
		sb.append(n + " ");

		for (Integer it : list.get(n)) {
			if (!visited[it]) {
				visited[it] = true;
				queue.add(it);
			}
		}
		if (!queue.isEmpty()) {
			bfs(queue.poll());
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());

		visited = new boolean[N + 1];

		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int tmp1 = Integer.parseInt(st.nextToken());
			int tmp2 = Integer.parseInt(st.nextToken());

			list.get(tmp1).add(tmp2);
			list.get(tmp2).add(tmp1);
		}

		for (int i = 1; i <= N; i++) {
			Collections.sort(list.get(i));
		}

		visited[V] = true;
		dfs(V);
		sb.append("\n");

		Arrays.fill(visited, false);
		visited[V] = true;
		bfs(V);

		System.out.println(sb);
	}

}