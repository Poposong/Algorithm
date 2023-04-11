import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, cnt;
	static int[][] map;
	static boolean[][] clean;
	static int[] dx = { 0, 1, 0, -1 }; // 시계: 0:북, 1:동, 2:남, 3:서
	static int[] dy = { -1, 0, 1, 0 };

	static int clean(int y, int x, int d) {
		int res = 0;
		int dir = d;
		while (cnt > res) {
//			System.out.println(y + " : " + x);
			// 1. 현재 칸이 청소 안된 경우, 현재 칸 청소
			if (map[y][x] == 0 && !clean[y][x]) {
				clean[y][x] = true;
				res++;
			}
			// 2. 주변 4칸 중 청소할 곳이 있는지 확인
			boolean flag = false;
			int tmp = dir;
			for (int i = 0; i < 4; i++) {

				tmp--;
				tmp = (tmp == -1) ? 3 : tmp;
				int ny = y + dy[tmp];
				int nx = x + dx[tmp];

				if (ny < 0 || ny >= N || nx < 0 || nx >= M) {
					continue;
				}

				if (map[ny][nx] == 1 || clean[ny][nx]) {
					continue;
				}

				// 청소할 곳이 있다.
				if (map[ny][nx] == 0 && !clean[ny][nx]) {
					dir = tmp;
					y = ny;
					x = nx;

					flag = true;
					break;
				}
			}

			// 3. !flag
			if (!flag) {
				tmp = dir + 2;
				tmp = (tmp >= 4) ? tmp - 4 : tmp;
				int ny = y + dy[tmp];
				int nx = x + dx[tmp];
				if (ny >= 0 && ny < N && nx >= 0 && nx < M) {
					if (map[ny][nx] == 1) {
						return res;
					}
					y = ny;
					x = nx;
				}
			}
		}
		return res;

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int startY = Integer.parseInt(st.nextToken());
		int startX = Integer.parseInt(st.nextToken());
		int direction = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		clean = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0) {
					cnt++;
				}
			}
		}
		// input end

		// output
		System.out.println(clean(startY, startX, direction));
	}
}