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

		int n = Integer.parseInt(br.readLine());

		int[][] cost = new int[n][n];

		for (int i = 0; i < n; i++) {
			Arrays.fill(cost[i], 0);
		}

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// input end

		for (int mid = 0; mid < n; mid++) {
			for (int from = 0; from < n; from++) {
				if (from == mid) {
					continue;
				}
				for (int to = 0; to < n; to++) {
					if (to == mid || cost[from][to] == 1) {
						continue;
					}

					if (cost[from][mid] == 1 && cost[mid][to] == 1) {
						cost[from][to] = 1;
					}
				}
			}
		}

		// output
		for (int from = 0; from < n; from++) {
			for (int to = 0; to < n; to++) {
				sb.append(cost[from][to] + " ");
			}
			sb.append("\n");
		}

		System.out.println(sb);

	}
}