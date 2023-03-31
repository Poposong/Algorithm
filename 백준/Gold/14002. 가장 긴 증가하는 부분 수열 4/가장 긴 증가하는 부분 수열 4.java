import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] inputs;
	static int[] index;
	static int[] arr;

	static void binarySearch(int now, int arrLen) {
		int mid = arrLen / 2;
		int low = 0;
		int high = arrLen;

		while (low <= high) {
			mid = (low + high) / 2;

			if (low == high) {
				break;
			}

			if (arr[mid] == inputs[now]) {
				index[now] = mid;
				return;
			} else if (arr[mid] > inputs[now]) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}

		}
		if (arr[mid] == inputs[now]) {
			index[now] = mid;
		} else if (arr[mid] > inputs[now]) {
			index[now] = mid;
			arr[mid] = inputs[now];
		} else {
			index[now] = mid + 1;
			arr[mid + 1] = inputs[now];
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());

		inputs = new int[N];
		index = new int[N];
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			inputs[i] = Integer.parseInt(st.nextToken());
		}

		int arrLen = 0;
		arr[0] = inputs[0];
		index[0] = 0;
		for (int i = 1; i < N; i++) {
			if (inputs[i] > arr[arrLen]) { // 큰 수이면 뒤에 붙인다.
				arr[++arrLen] = inputs[i];
				index[i] = arrLen;
			} else if (inputs[i] == arr[arrLen]) { // 값이 같으면 index값만 넣어준다.
				index[i] = arrLen;
			} else { // 작은 수이면 이분 탐색한다.
				binarySearch(i, arrLen + 1);
			}
		}

		sb.append((arrLen + 1)).append("\n");
		int idx = arrLen;
		int[] res = new int[arrLen + 1];
		for (int i = N - 1; i >= 0; i--) {
			if (index[i] == idx) {
				res[idx] = inputs[i];
				idx--;
			}
		}

		for (int i = 0; i <= arrLen; i++) {
			sb.append(res[i] + " ");
		}
		System.out.println(sb);
	}

}