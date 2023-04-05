import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int min, N, B, S;
	static int[] height;

	static void combi(int s, int r, int sum) {
		if (r == 0) {
			if (sum >= B) {
				min = Math.min(min, sum);

			}
			return;
		}

		for (int i = s; i < N; i++) {
			combi(i + 1, r - 1, sum + height[i]);
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
			B = Integer.parseInt(st.nextToken());

			height = new int[N];

			S = 0;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				height[i] = Integer.parseInt(st.nextToken());
				S += height[i];
			}
			min = S;

			for (int i = 1; i < N; i++) {
				combi(0, i, 0);
			}

			sb.append("#" + tc + " ");
			sb.append((min - B) + "\n");
		}

		System.out.println(sb);
	}

}