


import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

class Dot{
	int x;
	int y;
	
	public Dot(int x, int y) {
		this.x=x;
		this.y=y;
	}
}

public class Main{
	public static char[][] result;
	
	public static Dot[] distance = {new Dot(-1,-1), new Dot(-1,0), new Dot(-1,+1), new Dot(0, -1), new Dot(0, +1), new Dot(+1, -1), new Dot(+1, 0), new Dot(+1, +1)};

	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		result = new char[N][N];
		for(int i =0; i<result.length; i++)
			Arrays.fill(result[i], ' ');
		
		
		
		star(N,new Dot(0,0), new Dot(N-1, N-1));
		
		StringBuilder sb = new StringBuilder();
		
		
		
		
		
		for(int i = 0; i<result.length; i++) {
			for(int j =0; j<result[i].length;j++)
				sb.append(result[i][j]);
			sb.append("\n");
		}
		System.out.print(sb.toString());
		
	}
	
	static void star(int term,Dot start, Dot end) {

		if(term == 3) {
			for(int i =start.x; i<=end.x; i++) {
				for(int j = start.y ; j<=end.y ; j++) {
					if(i == start.x+1 && j == start.y+1) {
						result[i][j] = ' ';
					}else
						result[i][j] = '*';
				}
			}
			
		}else {
			
			int differ = term/3;

			Dot d1 = new Dot(start.x+ differ, start.y + differ);
			Dot d2 = new Dot(d1.x + differ - 1, d1.y + differ -1);
			
			for(int i =0; i<distance.length; i++) {
				
				star(differ, 
						new Dot(d1.x + distance[i].x*differ, d1.y + distance[i].y*differ), 
						new Dot(d2.x + distance[i].x*differ, d2.y + distance[i].y*differ));
	
				
			}
		}
	}
	
	

}
