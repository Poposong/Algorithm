import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int N, L;
	static int[][] graph;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		
		N = Integer.parseInt(str[0]);
		L = Integer.parseInt(str[1]);
		
		graph = new int[N][N];
		
		for(int i = 0; i<N; i++) {
			str = br.readLine().split(" ");
			for(int j = 0; j<N; j++) {
				graph[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		int count = 0;
		for(int i = 0; i<N; i++) {
			if(calRow(i)) count++;
			if(calCol(i)) count++;
		}
		System.out.println(count);
	}
	
	static boolean calCol(int col) {
		boolean[] visited = new boolean[N];
		for(int i = 0; i<N-1; i++) {
			int differ = graph[i][col] - graph[i+1][col];
			
			if(differ > 1 || differ < -1) return false;
			else if(differ == -1) { // 계단이 위로 있음
				for(int j = 0; j<L ; j++) {
					if(i - j < 0 || visited[i-j]) return false;
					if(graph[i][col] != graph[i-j][col]) return false;
					visited[i-j] = true;
				}
			}else if(differ == 1) { // 계단이 아래로 있음
				for(int j = 1; j<=L; j++) {
					if(i + j > N-1 || visited[i+j]) return false;
					if(graph[i][col] - 1 != graph[i+j][col]) return false;
					visited[i+j] = true;
				}
			}
		}
		return true;
	}
	
	static boolean calRow(int row) {
		boolean[] visited = new boolean[N];
		
		for(int i = 0; i < N-1; i++) {
			int differ = graph[row][i] - graph[row][i+1];
			
			if(differ > 1 || differ < -1) return false;
			else if(differ == -1) { // 계단이 위로 있는 경우
				for(int j = 0; j < L; j++) {
					if(i - j < 0 || visited[i-j]) return false;
					if(graph[row][i] != graph[row][i-j]) return false;
					visited[i-j] = true;
				}
			}else if(differ == 1) { // 계단이 아래로 있는 경우
				for(int j = 1; j<=L; j++) {
					if(i + j > N-1 || visited[i+j]) return false;
					if(graph[row][i] -1 != graph[row][i+j]) return false;
					visited[i+j] = true;
				}
			}
		}
		return true;
	}

}