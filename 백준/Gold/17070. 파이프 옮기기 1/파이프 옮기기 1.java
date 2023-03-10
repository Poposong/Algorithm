import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Dot{
	int x;
	int y;
	
	public Dot(int x,int y) {
		this.x=x;
		this.y=y;
	}
}
public class Main {
	
	public static int N;
	
	public static int[][] map;
	
	public static boolean[][] visited;
	
	public static int total = 0;
	
	// 가로 움직임
	public static Dot[] r_move1 = {new Dot(0,1), new Dot(0,1)}
					  , r_move2 = {new Dot(0,1), new Dot(1,1)}
					  , c_move1 = {new Dot(1,0), new Dot(1,0)}
					  , c_move2 = {new Dot(1,0), new Dot(1,1)}
					  , d_move1 = {new Dot(1,1), new Dot(1,1), new Dot(1,1)}
					  , d_move2 = {new Dot(0,1), new Dot(1,0), new Dot(1,1)}; // 대각선 움직임
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		visited = new boolean[N][N];
		
		String[] str;
		for(int i =0; i<N; i++) {
			str = br.readLine().split(" ");
			for(int j =0; j<N; j++) 
				map[i][j] = Integer.parseInt(str[j]);		
		}
		
		dfs(-1, 0, 0);
		
		System.out.println(total);
		
		
	}
	// 현재 파이프의 상태(-1, 0, 1) -> (가로, 세로, 대각선) ,  현재 x,y의 위치
	static void dfs(int current, int x, int y) { 
		
		
		int x1,x2,y1,y2;
		if(current == -1) { // 현재 파이프의 상태 : 가로

			for(int i =0; i<2; i++) {
				Dot dot1 = r_move1[i];
				Dot dot2 = r_move2[i];
				
				x1 = x + dot1.x;
				y1 = y + dot1.y;
				
				x2 = x + dot2.x;
				y2 = y + 1 + dot2.y;
				
				if(rangeCheck(x2, y2)) {
				
					
					if(i == 0) { // 가로 - 가로
						if(!visited[x2][y2] && map[x2][y2] == 0) {
							
							if(x2 == N-1 && y2 == N-1) {
								total++;
								return;
							}
							
							visited[x2][y2] = true;
							dfs(-1, x1, y1); // 가로 - 가로
							visited[x2][y2] = false;
						}
					}else if(i == 1) { // 가로 - 대각선
						if(!visited[x1][y1+1] && !visited[x1+1][y1] && !visited[x1+1][y1+1] && 
								map[x1][y1+1] == 0 && map[x1+1][y1] == 0 && map[x1+1][y1+1] == 0) {
							
							if(x2 == N-1 && y2 == N-1) {
								total++;
								return;
							}
							
							visited[x1][y1+1] = true;
							visited[x1+1][y1] = true;
							visited[x1+1][y1+1] = true;
							
							
							
							dfs(1, x1, y1);
							
							visited[x1][y1+1] = false;
							visited[x1+1][y1] = false;
							visited[x1+1][y1+1] = false;
						}
					}
				}
			}
		}else if(current == 0) { // 현재 파이프의 상태 : 세로
			for(int i =0; i<2; i++) {
				Dot dot1 = c_move1[i];
				Dot dot2 = c_move2[i];
				
				x1 = x + dot1.x;
				y1 = y + dot1.y;
				
				x2 = x + 1 + dot2.x;
				y2 = y + dot2.y;
				
				if(rangeCheck(x2,y2)) {

					
					if(i == 0) { // 세로 - 세로
						if(!visited[x2][y2] && map[x2][y2] == 0) {
							
							if(x2 == N-1 && y2 == N-1) {
								total++;
								return;
							}
							
							visited[x2][y2] = true;
							dfs(0, x1, y1); // 세로 - 세로
							visited[x2][y2] = false;
							
						}
					}else { // 세로 - 대각선
						if(!visited[x1][y1+1] && !visited[x1+1][y1] && !visited[x1+1][y1+1] && 
								map[x1][y1+1] == 0 && map[x1+1][y1] == 0 && map[x1+1][y1+1] == 0) {
							
							if(x2 == N-1 && y2 == N-1) {
								total++;
								return;
							}
							
							visited[x1][y1+1] = true;
							visited[x1+1][y1] = true;
							visited[x1+1][y1+1] = true;
							
							dfs(1, x1, y1);
							
							visited[x1][y1+1] = false;
							visited[x1+1][y1] = false;
							visited[x1+1][y1+1] = false;
						}
					}
				}
			}
		}else if(current == 1) { // 현재 파이프의 상태 : 대각선
			for(int i =0; i<3; i++) {
				Dot dot1 = d_move1[i];
				Dot dot2 = d_move2[i];
				
				x1 = x + dot1.x;
				y1 = y + dot1.y;
				
				x2 = x + 1 + dot2.x;
				y2 = y + 1 + dot2.y;
				
				if(rangeCheck(x2, y2)) {
					
					if(i == 0) { // 대각선 - 가로
						if(!visited[x2][y2] && map[x2][y2] == 0) {
							
							if(x2 == N-1 && y2 == N-1) {
								total++;
								return;
							}
							
							visited[x2][y2] = true;
							dfs(-1, x1, y1);
							visited[x2][y2] = false;
						}
					}else if(i == 1) { // 대각선 - 세로
						if(!visited[x2][y2] && map[x2][y2] == 0) {
							
							if(x2 == N-1 && y2 == N-1) {
								total++;
								return;
							}
							
							visited[x2][y2] = true;
							dfs(0, x1, y1);
							visited[x2][y2] = false;
						}
					}else { // 대각선 - 대각선
						if(!visited[x1][y1+1] && !visited[x1+1][y1] && !visited[x1+1][y1+1] && 
								map[x1][y1+1] == 0 && map[x1+1][y1] == 0 && map[x1+1][y1+1] == 0) {
							
							if(x2 == N-1 && y2 == N-1) {
								total++;
								return;
							}
							
							visited[x1][y1+1] = true;
							visited[x1+1][y1] = true;
							visited[x1+1][y1+1] = true;
							
							dfs(1, x1, y1);
							
							visited[x1][y1+1] = false;
							visited[x1+1][y1] = false;
							visited[x1+1][y1+1] = false;
						}
					}
				}
			}
		}
		
	}
	
	static boolean rangeCheck(int x, int y) {
		if(0<= x && x <N && 0<= y && y < N)
			return true;
		return false;
	}
	
	
}