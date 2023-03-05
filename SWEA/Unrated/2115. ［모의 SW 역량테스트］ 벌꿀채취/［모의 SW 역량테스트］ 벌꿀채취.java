import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/*


*/

class Dot{
	int x;
	int y;
	public Dot(int x,int y) {
		this.x=x;
		this.y=y;
	}
}

public class Solution {
    
	public static int T, N, M, C;
	
	public static int map[][];
	
	public static int result = Integer.MIN_VALUE;
	
	public static Dot[] honeyDot;
	
	public static int target;
	
	public static int max;
	
	public static int end;
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        
        
        for(int t = 1; t<= T; t++) {
        	result = Integer.MIN_VALUE;
        	
        	String[] str = br.readLine().split(" ");
            N = Integer.parseInt(str[0]); // 벌통의 크기
            M = Integer.parseInt(str[1]); // 선택할 수 있는 벌통의 크기
            C = Integer.parseInt(str[2]); // 채취할 수 있는 꿀의 최대 양
            
            map = new int[N][N];
            
            for(int i =0; i<N; i++) {
            	str = br.readLine().split(" ");
            	for(int j =0; j<N; j++) {
            		map[i][j] = Integer.parseInt(str[j]);
            	}
            }

            honeyDot = new Dot[2];
            
            Combination(0, 0, 0);
            
            sb.append("#"+t+" "+result+"\n");
        }
        System.out.println(sb.toString());
        
	}
	
	static void Combination(int r, int c, int count) {
		if(count == 2) {

			catchHoney();
		
			return;
		}
		
		int j;
		for(int i = r; i<N; i++) {
			if(i == r)
				j = c;
			else
				j = 0;
			
			for(; j<=N-M; j++) {
				honeyDot[count] = new Dot(i,j);
				Combination(i, j+M, count+1);
			}
		}
	}
	
	static void catchHoney() {
		int total = 0;
		
		Dot dot = honeyDot[0];
		
		max = Integer.MIN_VALUE;
		for(int i = 1; i<=M; i++) {
			target = i;
		
			end = dot.y + M;

			subSetHoney(dot.x, dot.y, 0, 0, 0);

		}

		
		total = max;
		
		max = Integer.MIN_VALUE;
		
		dot = honeyDot[1];
		
		for(int i = 1; i<=M; i++) {
			target = i;
			end = dot.y + M;

			subSetHoney(dot.x, dot.y, 0, 0, 0);
		}

		
		result = Math.max(result, total+max); 

		
	}
	
	static void subSetHoney(int x, int start, int count, int subSum, int sum) {
		
		if(count == target) {
			max = Math.max(max, sum);
			return;
		}
		
		for(int j = start; j<end; j++) {
			if(subSum + map[x][j] <= C)
				subSetHoney(x, j+1, count+1, subSum + map[x][j] , sum + (map[x][j] * map[x][j]));
		}

	}
	
}