
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	public static int N, white, blue;// 0은 white, 1은 blue
	public static int[][] color;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		color = new int[N][N];
		
		String[] str;
		for(int i =0; i<N; i++) {
			str = br.readLine().split(" ");
			for(int j =0; j<N; j++)
				color[i][j] = Integer.parseInt(str[j]);
		}
		
		colorCheck(0,0,N);
		
		System.out.println(white+"\n"+blue);


	}
	public static void colorCheck(int x, int y, int len) {
		int sum = 0;
		
		for(int i = x, xEnd = x + len; i < xEnd; i++) {
			for(int j = y, yEnd = y + len; j < yEnd; j++) {
				sum += color[i][j];
			}
		}
		
		if(sum == len * len)
			blue++;
		else if(sum == 0)
			white++;
		else {
			int half = len/2;
			colorCheck(x, y, half);
			colorCheck(x+half, y, half);
			colorCheck(x, y+half, half);
			colorCheck(x+half, y+half, half);
		}
	}

}
