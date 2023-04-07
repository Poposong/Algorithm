import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M, X;
	static int[][] map, dist;
	static int INF = 100001;

	static void floydWarshal() {
		for (int mid = 1; mid <= N; mid++) {
			for (int i = 1; i <= N; i++) {
				if (mid == i) {
					continue;
				}
				for (int j = 1; j <= N; j++) {
					if (mid == j || i == j) {
						continue;
					}
					map[i][j] = Math.min(map[i][j], map[i][mid] + map[mid][j]);
				}
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 학생수
		M = Integer.parseInt(st.nextToken()); // 도로개수
		X = Integer.parseInt(st.nextToken()); // 목적지 마을 번호

		map = new int[N + 1][N + 1];
		dist = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			Arrays.fill(map[i], INF);
			map[i][i] = 0;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			map[from][to] = cost;
		}
		// input end

		floydWarshal();

		int res = 0;
		for (int i = 1; i <= N; i++) {
			if (i == X) {
				continue;
			}
			res = Math.max(res, map[i][X] + map[X][i]);
		}

		System.out.println(res);

	}

}