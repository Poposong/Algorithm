import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
// 
public class Main {
	public static int N;
	public static int[][] map;
	
	public static int[] paper = new int[3]; // -1, 0, 1
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		
		String[] str;
		map = new int[N][N];
		
		for(int i =0; i<N; i++) {
			str = br.readLine().split(" ");
			for(int j = 0; j<N; j++) 
				map[i][j] = Integer.parseInt(str[j]);
		}
		
		mapSearch(0, 0, N);
		
		sb.append(paper[0] + "\n" + paper[1] + "\n" + paper[2]);
		System.out.println(sb.toString());
	}
	
	static void mapSearch(int x, int y, int len) {
		
		int minus = 0, zero = 0, one = 0;
		
		for(int i = x, xEnd = x + len; i < xEnd; i++) {
			for(int j = y, yEnd = y + len; j < yEnd; j++) {
				switch(map[i][j]) {
				case -1:
					minus++;
					break;
				case 0:
					zero++;
					break;
				case 1:
					one++;
					break;
				}
				
			}
		}
		
		int size = len*len;
		
		if(minus == size) {
			paper[0]++;
		}else if(zero == size) {
			paper[1]++;
		}else if(one == size) {
			paper[2]++;
		}else {
			if(len == 3) {
				paper[0] += minus;
				paper[1] += zero;
				paper[2] += one;
			}else {
				int term = len/3;
				for(int i = x, xEnd = x + len; i < xEnd; i+= term) {
					for(int j = y , yEnd = y + len; j < yEnd; j+=term) {
					//	System.out.printf("%d %d %d\n", i,j,len/3);
						mapSearch(i,j,term);
					}
				}
				//System.out.println("-------");
			}
			
			
			
		}
		
		
	}

}