import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Dot{
	int rx;
	int ry;
	int bx;
	int by;
	int count;
	
	public Dot(int rx, int ry, int bx, int by, int count) {
		this.rx = rx;
		this.ry = ry;
		this.bx = bx;
		this.by = by;
		this.count = count;
	}
}
public class Main {
	static int N,M;
	
	static char[][] graph;
	
	static boolean[][][][] visited;
	
	static int[] dx = {-1, 0, 1, 0};
	
	static int[] dy = {0, 1, 0, -1};
	
	static int goalX, goalY;
	
	static Dot redDot, blueDot;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        
        graph = new char[N][M];
        
        visited = new boolean[N][M][N][M];
        
        String s;
        for(int i = 0; i<N; i++) {
        	s = br.readLine();
        	for(int j = 0; j<M; j++) {
        		graph[i][j] = s.charAt(j);
        		if(graph[i][j] == 'R')
        			redDot = new Dot(i, j, 0, 0, 0);
        		
        		if(graph[i][j] == 'B')
        			blueDot = new Dot(0, 0, i, j, 0);
        		
        		if(graph[i][j] == 'O') {
        			goalX = i;
        			goalY = j;
        		}
        	}
        } 
        
        System.out.println(findGoal());

    }
    
    
    static int findGoal() {
    	Queue<Dot> queue = new ArrayDeque<>();
    	queue.add(new Dot(redDot.rx, redDot.ry, blueDot.bx, blueDot.by, 0));
    	visited[redDot.rx][redDot.ry][blueDot.bx][blueDot.by] = true;
    	
    	Dot dot;
    	while(!queue.isEmpty()) {
    		dot = queue.poll();
    		
    		if(dot.count>=10)
    			return -1;
    		
    		int curRx = dot.rx;
    		int curRy = dot.ry;
    		int curBx = dot.bx;
    		int curBy = dot.by;
    		
    		for(int i = 0; i<4; i++) {
    			int nextRx = curRx;
    			int nextRy = curRy;
    			int nextBx = curBx;
    			int nextBy = curBy;
    			
    			boolean redBoalCheck = false;
    			boolean blueBoalCheck = false;
    			
    			// 빨간색 이동
    			while(graph[nextRx+dx[i]][nextRy+dy[i]] != '#') {
    				nextRx += dx[i];
    				nextRy += dy[i];
    				
    				if(nextRx == goalX && nextRy == goalY) {
    					redBoalCheck = true;
    					break;
    				}
    			}
    			
    			// 파란색 이동
    			while(graph[nextBx+dx[i]][nextBy+dy[i]] != '#') {
    				nextBx += dx[i];
    				nextBy += dy[i];
    				
    				if(nextBx == goalX && nextBy == goalY) {
    					blueBoalCheck = true;
    					break;
    				}
    			}
    			
    			if(blueBoalCheck) {
    				continue;
    			}
    			
    			if(redBoalCheck) {
    				return dot.count+1;
    			}
    			
    			if(nextRx == nextBx && nextRy == nextBy) {
    				if(i == 0) { // 위로 가는 방향
    					if(curRx < curBx) {
    						nextBx -= dx[i];
    					}else {
    						nextRx -= dx[i];
    					}
    				}else if(i == 1) { // 오른쪽으로 가는 방향
    					if(curRy < curBy) {
    						nextRy -= dy[i];
    					}else {
    						nextBy -= dy[i]; 
    					}
    				}else if(i == 2) { // 아래로 가는 방향
    					if(curRx < curBx) {
    						nextRx -= dx[i];
    					}else {
    						nextBx -= dx[i];
    					}
    				}else {
    					if(curRy < curBy) { // 왼쪽으로 가는 방향
    						nextBy -= dy[i];
    					}else {
    						nextRy -= dy[i]; 
    					}
    				}
    			}
    			
    			if(!visited[nextRx][nextRy][nextBx][nextBy]) {
    				visited[nextRx][nextRy][nextBx][nextBy] = true;
    				queue.offer(new Dot(nextRx, nextRy, nextBx, nextBy, dot.count+1));
    			}
    		}
    	}
    	
    	
    	
    	return -1;
    }

}