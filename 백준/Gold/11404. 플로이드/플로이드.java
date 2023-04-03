import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int INF = 10000001;

		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());

		int[][] cost = new int[n][n];

		for (int i = 0; i < n; i++) {
			Arrays.fill(cost[i], INF);
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			cost[from - 1][to - 1] = Math.min(cost[from - 1][to - 1], c);
		}
		// input end

		for (int mid = 0; mid < n; mid++) {
			for (int from = 0; from < n; from++) {
				if (from == mid) {
					cost[from][mid] = 0;
					continue;
				}
				for (int to = 0; to < n; to++) {
					if (to == mid || to == from) {
						continue;
					}
					cost[from][to] = Math.min(cost[from][to], cost[from][mid] + cost[mid][to]);
				}
			}
		}

		// output
		for (int from = 0; from < n; from++) {

			for (int to = 0; to < n; to++) {
				if (from == to || cost[from][to] >= INF) {
					sb.append("0 ");
				} else {
					sb.append(cost[from][to] + " ");
				}
			}
			sb.append("\n");
		}

		System.out.println(sb);

	}
}