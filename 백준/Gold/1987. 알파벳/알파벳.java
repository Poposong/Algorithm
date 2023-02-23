import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class Main {
	public static int R,C;
	
	public static char[][] map;
	
	public static int count = 1;
	
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	
	public static boolean[] alphabet;
	
	public static int result = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] str = br.readLine().split(" ");
		
		R = Integer.parseInt(str[0]);
		C = Integer.parseInt(str[1]);
		
		map = new char[R][C];
		
		alphabet = new boolean[26];
		
		String s;
		for(int i = 0; i<R; i++) {
			s = br.readLine();
			for(int j =0; j<C; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		
		
		alphabet[map[0][0]-'A'] = true;
		
		dfs(0,0);
		
		System.out.println(result);
	}
	
	static void dfs(int x, int y) {
		
		result = Math.max(result, count);
		
		int nextX, nextY;
		for(int i =0; i<4; i++) {
			nextX = x + dx[i];
			nextY = y + dy[i];
			
			if(rangeCheck(nextX, nextY)) {
				char c = map[nextX][nextY];
				if(!alphabet[c-'A']) {
					alphabet[c-'A'] = true;
					count++;
					dfs(nextX,nextY);
					count--;
					alphabet[c-'A'] = false;
				}
			}
		}
	}
	
	static boolean rangeCheck(int x, int y) {
		if(0<= x && x < R && 0 <= y && y < C)
			return true;
		return false;
	}
}