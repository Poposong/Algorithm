import java.io.*;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static int[] paper = { 0, 5, 5, 5, 5, 5 };
	static int min = Integer.MAX_VALUE;

	public static void dfs(int x, int y, int cnt) {
		if (x >= 9 && y > 9) {
			min = Math.min(min, cnt);
			return;
		}

		// 고려할 필요X
		if (min <= cnt) {
			return;
		}

		if (y > 9) {
			dfs(x + 1, 0, cnt);
			return;
		}

		if (map[x][y] == 1) {
			for (int i = 5; i >= 1; i--) {
				if (paper[i] > 0 && isAvailable(x, y, i)) {
					change(x, y, i, 0); // attach
					paper[i]--;
					dfs(x, y + 1, cnt + 1);
					change(x, y, i, 1); // detach
					paper[i]++;
				}
			}
		} else { // 오른쪽으로 이동.
			dfs(x, y + 1, cnt);
		}

	}

	static void change(int x, int y, int l, int state) {
		for (int nx = x; nx < x + l; nx++) {
			for (int ny = y; ny < y + l; ny++) {
				map[nx][ny] = state;
			}
		}
	}

	static boolean isAvailable(int x, int y, int l) {
		for (int nx = x; nx < x + l; nx++) {
			for (int ny = y; ny < y + l; ny++) {
				if (nx < 0 || nx >= 10 || ny < 0 || ny >= 10) {
					return false;
				}

				if (map[nx][ny] != 1) {
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		map = new int[10][10];

		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0, 0, 0);

		if (min == Integer.MAX_VALUE) {
			System.out.println("-1");
		} else {
			System.out.println(min);
		}
	}

}