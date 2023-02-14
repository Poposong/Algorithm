import java.io.*;
import java.util.*;


public class Main{
	
	public static int dx[] = {-1,0,1,0};
	public static int dy[] = {0,-1,0,1};
	public static int N,M;
	public static int cheese[][];
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s[] = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        M = Integer.parseInt(s[1]);
        cheese = new int[N][M];
        
        for(int i =0; i<N; i++) {
        	s = br.readLine().split(" ");
        	for(int j =0; j<M; j++) {
        		cheese[i][j] = Integer.parseInt(s[j]);
        	}
        }
        int cnt = bfs(0,0);
        int count = 0;
        int before = 0;
        while(cnt != 0) {
        	count++;
        	before = cnt;
        	cnt = bfs(0,0);
        }
        System.out.println(count+"\n"+before);
        /*
         for(int i =0; i<N; i++) {
        	for(int j =0; j<M; j++)
        		System.out.print(cheese[i][j]+" ");
        	System.out.println();
        }
         * */
        
    }
    
    public static int bfs(int x, int y) {
    	Queue<dot> que = new LinkedList<>();
    	que.add(new dot(x, y));
    	boolean visited[][] = new boolean[N][M];
    	int cnt = 0;
    	while(!que.isEmpty()) {
    		dot d = que.poll();
    		//System.out.println(d.x+" "+d.y);
    		visited[d.x][d.y] = true;
    		
    		
    		for(int i =0; i<4; i++) {
    			int next_x = d.x + dx[i];
    			int next_y = d.y + dy[i];
    			
    			if(next_x<0 || N<=next_x || next_y <0 || M<=next_y) continue;
    			if(visited[next_x][next_y] == true) continue;
    			
    			if(cheese[next_x][next_y] == 1) {
    				cheese[next_x][next_y] = 0;
    				cnt++;
    				visited[next_x][next_y] = true;
    			}else {
    				visited[next_x][next_y] = true;
    				que.add(new dot(next_x, next_y));
    			}
    		}
    		
    		
    	}
    	return cnt;
    	
    	
    }
    
    static class dot{
    	int x;
    	int y;
    	
    	public dot(int x, int y) {
    		this.x = x;
    		this.y = y;
    	}
    }
}

