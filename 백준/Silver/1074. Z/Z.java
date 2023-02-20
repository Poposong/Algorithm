import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
// 3 0 5
public class Main {
	public static int N,r,c;

	public static int ranking = 0;
	public static int answer;
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		r = Integer.parseInt(str[1]);
		c = Integer.parseInt(str[2]);
		
		
		N = (int)Math.pow(2, N);
		
		buildPath(0,0,N);
		
		System.out.println(ranking-1);
	
	}
	
	public static void buildPath(int x, int y, int len) {
		
		
		
		if(len == 2) {
			for(int i = x, xEnd = x + len; i<xEnd; i++) {
				for(int j = y, yEnd = y + len; j<yEnd; j++) {
					ranking++;
					if(i == r && j == c) {
						answer = ranking;
						return;
					}
				}
			}
		}
		
		int half = len/2;
		
		if(x == r && y == c) {
			
			
			ranking++;
			return;
		}

		if(x<= r && r < x+half) {
			if(y <= c && c < y+half) { // 1사분면

				buildPath(x,y, half); 
			}else if(y+half <= c && c < y+len) { // 2사분면

				ranking += half*half;
			
				buildPath(x,y+half,half); 
			}
		}else if(x+half <= r && r < x+len) { 
			if(y <= c && c < y+half) { // 3사분면
		
				ranking += 2*(half*half);
				buildPath(x+half,y, half);
			}else if(y+half <= c && c < y+len) { // 4사분면

				ranking += 3*(half*half);
				buildPath(x+half, y+half, half);
			}
		}

		
	}

}