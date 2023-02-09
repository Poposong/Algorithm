

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/*
6
0 1 2 3 4 5
1 0 2 3 4 5
1 2 0 3 4 5
1 2 3 0 4 5
1 2 3 4 0 5
1 2 3 4 5 0
	
 * */


public class Main {
	
	//static int[] dx = {0, -1, 0, 1};
	//static int[] dy = {-1, 0, 1, 0};
	
	public static int N;
	
	public static int[][] skill;
	
	public static boolean[] visited;
	
	public static int result = Integer.MAX_VALUE;
	
	public static List<Integer> startList = new ArrayList<>();
	
	public static List<Integer> linkList = new ArrayList<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		String[] str;
		
		skill = new int[N][N];
		
		visited = new boolean[N];
		
		for(int i =0; i<N; i++) {
			str = br.readLine().split(" ");
			for(int j = 0; j<N; j++) 
				skill[i][j] = Integer.parseInt(str[j]);
		}
		
		for(int i = 1; i<=N; i++) {
			perm(i, 0, 0,"");
			//System.out.println("----");
		}
		System.out.println(result);
		
	}
	
	static void perm(int target, int current, int startIdx, String str) {
		if(target == current) {
			int start_team = 0;
			int link_team = 0;
			for(int i =0; i<N; i++) {
				for(int j = 0 ; j<N; j++) {
					if(i != j) {
						if(visited[i] && visited[j]) {
							start_team += (skill[i][j] + skill[j][i]);
							//System.out.println("start_team : "+ i+","+j);
						}
						if(!visited[i] && !visited[j]){
							link_team += (skill[i][j] + skill[j][i]);
							//System.out.println("link_team : "+ i+","+j);
						}
					}
				}
			}
			
			start_team = start_team != 0? start_team/2 : 0;
			link_team = link_team != 0? link_team/2 : 0;
			result = Math.min(result, Math.abs(start_team - link_team));
			//System.out.println(str+" : "+start_team +" / "+ link_team + " -> " +  Math.abs(start_team - link_team));
			return;
		}
		
		for(int i = startIdx; i<N; i++) {
			if(!visited[i]) {
				visited[i] = true;
		
				perm(target, current+1, i+1, str+i+" ");
				
				visited[i] = false;
			}
		}
	}
	
	



	
}
