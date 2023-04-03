import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] R = new int[N][N];
		int INF = 101;

		for (int i = 0; i < N; i++) {
			Arrays.fill(R[i], INF);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			R[from - 1][to - 1] = 1;
			R[to - 1][from - 1] = 1;
		}
		// input end

		for (int mid = 0; mid < N; mid++) {
			for (int from = 0; from < N; from++) {
				if (from == mid) {
					R[from][mid] = 0;
					continue;
				}
				for (int to = 0; to < N; to++) {
					if (mid == to || from == mid) {
						continue;
					}
					R[from][to] = Math.min(R[from][to], R[from][mid] + R[mid][to]);
					R[to][from] = R[from][to];
				}
			}
		}

		int min = 101;
		int res = 0;
		for (int from = 0; from < N; from++) {
			int sum = 0;
			for (int to = 0; to < N; to++) {
				sum += R[from][to];
			}

			if (sum < min) {
				min = sum;
				res = from;
			}
		}

		// output
		System.out.println(res + 1);

	}
}