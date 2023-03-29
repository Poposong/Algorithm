import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * dp 풀이
 */
public class Main {

	static int[][] dp = new int[31][31];

	static int combi(int n, int r) {
		if (dp[n][r] > 0) {
			return dp[n][r];
		}
		if (n == r || r == 0) {
			return 1;
		}

		return dp[n][r] = combi(n - 1, r - 1) + combi(n - 1, r);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			sb.append(combi(M, N) + "\n");
		}
		System.out.println(sb);

	}

}