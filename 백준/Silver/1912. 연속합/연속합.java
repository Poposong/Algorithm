import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());

		int[] arr = new int[n];
		int[] dp = new int[n];
		int maxPsum;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		dp[0] = arr[0];
		maxPsum = dp[0];
		for (int i = 1; i < n; i++) {
			// dp[i - 1] + arr[i] : 값이 이어지는 경우
			// arr[i] : 새로운 값이 더 크기 때문에 새로운 부분수열이 시작하는 경우
			dp[i] = Math.max(dp[i - 1] + arr[i], arr[i]);

			maxPsum = Math.max(maxPsum, dp[i]);
		}

		System.out.println(maxPsum);
	}

}