import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	public static int N;
	
	public static int[][] color; // zeroColor, oneColor
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		
		//color = new int[100001][100001];
		//color[1][0] = 1; // zero
		//color[1][1] = 2; // one
		
		long zero = 1;
		long one = 2;
		
		
		
		for(int i = 2; i<N+1; i++) {
			long tempZero = zero , tempOne = one;
			//color[i][0] = color[i-1][0]*1 + color[i-1][1];
			//color[i][1] = color[i-1][0]*2 + color[i-1][1];
			zero = tempZero + one;
			one = tempZero*2 + one;
			
			if(zero>=9901)
				zero = zero%9901;
			if(one>=9901)
				one = one%9901;
		}
		
		System.out.println((zero+one)%9901);
		
		
		
		
	}

}