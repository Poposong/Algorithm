import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] R = new int[N][N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			R[from - 1][to - 1] = 1;
		}
		// input end

		for (int mid = 0; mid < N; mid++) {
			for (int from = 0; from < N; from++) {
				if (from == mid) {
					continue;
				}
				if (R[from][mid] == 0) {
					continue;
				}
				for (int to = 0; to < N; to++) {
					if (mid == to || from == to || R[from][to] == 1) {
						continue;
					}
					if (R[mid][to] == 1) {
						R[from][to] = 1;
					}
				}
			}
		}

		int res = N;
		for (int from = 0; from < N; from++) {
			for (int to = 0; to < N; to++) {
				if (from == to) {
					continue;
				}
				if (R[from][to] == 0 && R[to][from] == 0) {
					res--;
					break;
				}
			}
		}

		System.out.println(res);

	}
}