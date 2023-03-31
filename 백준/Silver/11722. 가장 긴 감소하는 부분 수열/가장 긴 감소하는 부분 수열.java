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
				return;
			} else if (arr[mid] < inputs[now]) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}

		}

		if (arr[mid] < inputs[now]) {
			arr[mid] = inputs[now];
		} else if (arr[mid] > inputs[now]) {
			arr[mid + 1] = inputs[now];
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());

		inputs = new int[N];
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			inputs[i] = Integer.parseInt(st.nextToken());
		}

		int arrLen = 0;
		arr[0] = inputs[0];
		for (int i = 1; i < N; i++) {
			if (inputs[i] < arr[arrLen]) {
				arr[++arrLen] = inputs[i];
			} else if (inputs[i] > arr[arrLen]) { // 큰 수이면 이분 탐색
				binarySearch(i, arrLen + 1);
			}
		}

		System.out.println(arrLen + 1);
	}

}