import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;




public class Main {

	
	public static int N;
	
	public static int[] sour, bitter;
	
	public static boolean[] visited;
	
	public static int result = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		String[] str;
		
		sour = new int[N];
		bitter = new int[N];
		
		visited = new boolean[N];
		
		for(int i =0; i<N; i++) {
			 str = br.readLine().split(" ");
			 sour[i] = Integer.parseInt(str[0]);
			 bitter[i] = Integer.parseInt(str[1]);
		}
		
		
		for(int i = 1; i<=N; i++)
			perm(i, 0, 0, 1, 0);
		System.out.println(result);
	
	}
	
	
	public static void perm(int target, int current, int startIdx, int s, int b) {
		
		if(target == current) {
			result = Math.min(result, Math.abs(s-b));
			return;
		}
		
		for(int i = startIdx; i<N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				perm(target, current+1, i, s * sour[i], b + bitter[i]);
				visited[i] = false;
			}
		}
	}



	
}
