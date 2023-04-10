import java.io.*;
import java.util.*;

class Solution {
	static int N, M, res;
	static boolean flag;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int[] citySize;

	static class Position {
		int y, x;

		public Position(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}

	static void bfs(int y, int x) {
		Queue<Position> q = new LinkedList<Position>();
		int home = map[y][x];
		int cost = 1;
		int k = 1;
		visited = new boolean[N][N];
		visited[y][x] = true;
		q.add(new Position(y, x));

		while (!q.isEmpty()) {

			int qSize = q.size();
			for (int i = 0; i < qSize; i++) {
				Position now = q.poll();

				for (int d = 0; d < 4; d++) {
					int ny = now.y + dy[d];
					int nx = now.x + dx[d];

					if (ny < 0 || ny >= N || nx < 0 || nx >= N) {
						continue;
					}

					if (visited[ny][nx]) {
						continue;
					}

					visited[ny][nx] = true;
					if (map[ny][nx] == 1) {
						home++;
					}
					q.add(new Position(ny, nx));
				}
			}

			k++;
			cost = k * k + (k - 1) * (k - 1);
			if ((home * M) - cost >= 0) {
				res = Math.max(res, home);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// input end
			flag = false;
			res = 1;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					bfs(i, j);
				}
			}

			// output
			sb.append("#" + tc + " ");
			sb.append(res + "\n");
		}

		System.out.println(sb);
	}

}