import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;


public class Main {
	public static int N;
	public static int[][] map;
	
	public static StringBuilder sb;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		
		String s;
		for(int i =0; i<N; i++) {
			s = br.readLine();
			for(int j = 0; j<N; j++) 
				map[i][j] = Integer.parseInt(s.charAt(j)+"");
		}
		
		move(0,0,N);
		
		System.out.println(sb.toString());
		
	}
	
	static void move(int x, int y, int len) {
		
		int count = 0;
		
		for(int i = x, xEnd = x + len; i<xEnd; i++) {
			for(int j = y, yEnd = y + len; j < yEnd; j++) {
				count += map[i][j];
			}
		}
		
		if(count == len*len) { // 한 변이 len인 사각형이 모두 1인 경우
			sb.append(1);
		}else if(count == 0) { // 한 변이 len인 사각형이 모두 0인 경우
			sb.append(0);
		}else {
			sb.append("(");
			
			int half = len/2;
			move(x, y, half);
			move(x, y+half, half);
			move(x+half, y, half);
			move(x+half, y+half, half);
			
			sb.append(")");
		}
		
		
	}
	
}