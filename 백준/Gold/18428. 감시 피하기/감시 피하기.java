import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*

*/
class Dot{
	int x;
	int y;
	
	public Dot() {}
	
	public Dot(int x,int y) {
		this.x=x;
		this.y=y;
	}
}
public class Main {
	public static int N;
	
	public static char[][] map;
	
	public static boolean[][] isSelected;
	
	public static ArrayList<Dot> teacher;
	
	public static int dx[] = {-1,1,0,0};
	public static int dy[] = {0,0,-1,1};
	
	public static boolean answer;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new char[N][N];
		isSelected = new boolean[N][N];
		
		teacher = new ArrayList<>();
		
		String[] str;
		for(int i =0; i<N; i++) {
			str = br.readLine().split(" ");
			for(int j =0; j<N; j++) {
				map[i][j] = str[j].charAt(0);
				if(map[i][j] == 'T') {
					teacher.add(new Dot(i,j));
				}
			}
		}
		
		obstacle(0);
		
		System.out.println(answer? "YES" : "NO");
		
	}
	
	static void obstacle(int count) {
		if(count == 3) {
			// 3개의 장애물을 설치함
			if(isCheck()) { // 장애물을 설치할 수 있는 경우
				answer = true;
			}
			
			return;
		}
		
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				if(map[i][j] == 'X') {
					map[i][j] = 'O';
					obstacle(count+1);
					map[i][j] = 'X';
				}
			}
		}
	}
	
	static boolean isCheck() {
		for(Dot dot : teacher) {
			for(int i =0; i<4; i++) {
				Dot temp = new Dot(dot.x,dot.y);
				
				temp.x += dx[i];
				temp.y += dy[i];
				
				while(rangeCheck(temp.x,temp.y)) {
					
					if(map[temp.x][temp.y] == 'O')
						break;
					else if(map[temp.x][temp.y] == 'S')
						return false;
					
					temp.x += dx[i];
					temp.y += dy[i];
				}
			}
		}
		return true;
	}
	
	static boolean rangeCheck(int x, int y) {
		if(0<=x && x < N && 0 <= y && y < N)
			return true;
		return false;
	}

}