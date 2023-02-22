import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Main {

	public static int M, N, K; // 가로, 세로, 배추가 심어져 있는 위치의 개수

	public static int answer = 0;

	public static int[][] map;

	public static boolean[][] visited;

	public static int[] dx = { -1, 1, 0, 0 };
	public static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		
		String[] str;
		for (int t = 0; t < T; t++) {
			
			str = br.readLine().split(" ");

			M = Integer.parseInt(str[0]);
			N = Integer.parseInt(str[1]);
			K = Integer.parseInt(str[2]);
			
			
			map = new int[M][N];
			visited = new boolean[M][N];

			for(int i = 0; i<K; i++) {
				str = br.readLine().split(" ");

				map[Integer.parseInt(str[0])][Integer.parseInt(str[1])] = 1;
			}
			
			
			
			answer = 0;
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 1 && !visited[i][j]) {
						visited[i][j] = true;
						answer++;
						dfs(i, j);
					}
				}
			}
			
			sb.append(answer+"\n");

		}
		
		
		System.out.println(sb.toString());

	}

	static void dfs(int x, int y) {

		int nextX, nextY;
		for (int i = 0; i < 4; i++) {
			nextX = x + dx[i];
			nextY = y + dy[i];

			if (0 <= nextX && nextX < M && 0 <= nextY && nextY < N) {
				if (map[nextX][nextY] == 1 && !visited[nextX][nextY]) {
					visited[nextX][nextY] = true;
					dfs(nextX, nextY);
				}
			}
		}
	}

}