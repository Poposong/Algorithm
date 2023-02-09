
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;






public class Main {

	public static int N,S;
	
	public static int[] setArray;
	
	public static boolean[] visited;
	
	public static int count = 0;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] str = br.readLine().split(" ");
		
		N = Integer.parseInt(str[0]);
		
		S = Integer.parseInt(str[1]);
		
		str = br.readLine().split(" ");
		
		setArray = new int[N];
		visited = new boolean[N];
		
		for(int i =0; i<N; i++)
			setArray[i] = Integer.parseInt(str[i]);
		
		for(int i =1; i<=N; i++) 
			perm(i, 0, 0, 0);
		
		
		System.out.println(count);
		
	}
	
	public static void perm(int target, int current, int startIdx, int sum) {

		if(current == target) {
			if(sum == S)
				count++;
			return;
		}
		
		for(int i = startIdx; i<N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				perm(target, current+1, i, sum + setArray[i]);
				visited[i] = false;
			}
		}
	}
	



	
}
