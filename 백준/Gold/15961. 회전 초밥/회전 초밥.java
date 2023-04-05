import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 회전초밥 벨트에 있는 접시 수
		int d = Integer.parseInt(st.nextToken()); // 초밥의 가짓수
		int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시 수
		int c = Integer.parseInt(st.nextToken()); // 쿠폰번호

		boolean flag = false;
		int maxType = k;
		int max = Integer.MIN_VALUE;

		int[] sushi = new int[N]; 		// 회전초밥 배열
		int[] type = new int[d + 1]; 	// 종류별 카운트 배열

		for (int i = 0; i < N; i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}
		// input end

		// 맨 앞에 4개의 스시 종류 확인
		for (int i = 0; i < k; i++) {
			if (++type[sushi[i]] > 1) {
				maxType--;
			}
			if (sushi[i] == c) {
				flag = true;
			}
		}

		int last = k;
		for (int first = 0; first < N; first++) {
			if (first == last) {
				last++;
				if (last >= N) {
					last = 0;
				}
				continue;
			}

			// 1. 전 맨앞 count 빼기
			if (--type[sushi[first]] == 0) {
				maxType--;
				if (sushi[first] == c) {
					flag = false;
				}
			}

			// 2. 뒤에 count 더하기
			int tmp = ++type[sushi[last]];
			if (tmp == 1) {
				maxType++;
				if (sushi[last] == c) {
					flag = true;
				}
			}

			if (flag) {
				max = Math.max(max, maxType);
			} else {
				max = Math.max(max, maxType + 1);
			}

			if (++last >= N) {
				last = 0;
			}

		}
		System.out.println(max);
	}
}