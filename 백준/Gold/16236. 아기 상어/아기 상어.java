import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;


class Dot{
    int x;
    int y;
    public Dot(int x,int y) {
        this.x = x;
        this.y = y;
    }
}
public class Main {
    
    public static int N;
    
    public static int[][] map; 
    
    public static int[][] minPath;
    
    public static boolean[][] fish_visited; // 현재 자리의 물고기가 존재하는 물고기인지?
    
    
    public static ArrayList[] fish;
    
    public static int[] dx = {-1, 1, 0, 0};
    
    public static int[] dy = {0, 0, -1, 1};
    
    public static Dot shark;
    
    public static int timer;
    
    public static int shark_size = 2; // 상어의 사이즈
    public static int shark_count = 0; // 지금 사이즈에서 상어가 먹은 물고기의 수
    
    public static int fish_total; // 현재 존재하는 전체 물고기의 수
    
    public static boolean ischeck;
    
    @SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        
        map = new int[N][N];
     //   visited = new boolean[N][N];
        fish_visited = new boolean[N][N];
        
        minPath = new int[N][N];
        
        fish = new ArrayList[10];
        
        for(int i =0; i<10; i++)
        	fish[i] = new ArrayList<Dot>();
        
        
        
        String[] str;
        for(int i =0; i<N; i++) {
        	str = br.readLine().split(" ");
        	for(int j =0; j<N; j++) {
        		map[i][j] = Integer.parseInt(str[j]);
        		if(map[i][j] == 9) {
        			shark = new Dot(i, j);
        		}else if(map[i][j] != 0) {
        			fish_total++;
        			fish[map[i][j]].add(new Dot(i,j));
        		}
        	}
        }
        //removeFish();
        int c = 0;
        int before = timer;
        while(fish_total!=0) {
        	//System.out.println("sharkSize : "+ shark_size);
        	removeFish();
        	
        	if(before == timer)
        		break;
        	
        	before = timer;
        	
        	//System.out.println("timer : "+ timer);
        	
        }
        System.out.println(timer);
    }
    
    static void removeFish() {
    	// 상어가 먹을 수 있는 최소 경로의 물고기 좌표를 찾기
    	//System.out.println(shark.x+" - "+shark.y);
    	
    	for(int k =0; k<N; k++) {
			//Arrays.fill(visited[k], false);
			Arrays.fill(minPath[k], Integer.MAX_VALUE);
    	}
    	
		
    	//visited[shark.x][shark.y] = true;
    	minPath[shark.x][shark.y] = 0;
    	
		dfs(shark.x, shark.y);

		
		int len = Integer.MAX_VALUE;

		Dot dot1 = new Dot(0,0);
		
	
    	for(int i = 1; i<shark_size; i++) {
    		
    		for(int j =0; j<fish[i].size(); j++) {
    			
    			Dot dot2 = (Dot)fish[i].get(j);
    			//System.out.println(dot2.x +"/"+dot2.y);
    			if(!fish_visited[dot2.x][dot2.y]) {
    				//System.out.println("come!"+minPath[dot2.x][dot2.y]+","+len);
    				if(minPath[dot2.x][dot2.y] < len) {
    					len = minPath[dot2.x][dot2.y];
    					dot1.x = dot2.x;
    					dot1.y = dot2.y;
    				}else if(minPath[dot2.x][dot2.y] == len){
    					if(dot1.x > dot2.x) { // x좌표가 큰 것이 우선순위가 높다
    						dot1.x = dot2.x;
    						dot1.y = dot2.y;
    					}else if(dot1.x == dot2.x) {
    						if(dot2.y < dot1.y) { // y좌표가 작은 것이 우선순위가 높다
    							dot1.x = dot2.x;
    							dot1.y = dot2.y;
    						}
    					}
    				}
    			}
    		}
    	} 
    	
    	

    	
    
    	
    	// 더 이상 죽일 수 있는 물고기가 없는 경우
    	if(len == Integer.MAX_VALUE) {
    		fish_total = -1;
    		//System.out.println("print");
    	}else {
    		timer += len;
    		
    		map[shark.x][shark.y] = 0;
    		
    		map[dot1.x][dot1.y] = 9;
    		
    		shark.x = dot1.x;
    		shark.y = dot1.y;
    		
    		fish_visited[dot1.x][dot1.y] = true;
    		
    		fish_total--;
    		
    		shark_count++;
    
    		if(shark_count == shark_size) {
    			shark_size++;
    			shark_count = 0;
    			
    			if(shark_size >= 7)
    				shark_size = 7;
    		}
    	}
    	
    	

    	
    	
    	 
    	 

		
    }
    
    // 현재 상어가 갈 수 있는 최단경로의 물고기를 찾는다.
    static void dfs(int x, int y) {
    	int nx, ny;
    	for(int i =0; i<4; i++) {
    		nx = x + dx[i];
    		ny = y + dy[i];
    		
    		if(rangeCheck(nx, ny)) {
    			if(map[nx][ny] <= shark_size) {
    				int cost = minPath[x][y] + 1;
    				if(cost < minPath[nx][ny]) {
    					 minPath[nx][ny] = cost;
    					 dfs(nx, ny);
    				}
    			}
    		}
    	}
    	
    	
    }
    
    static boolean rangeCheck(int x, int y) {
    	if(0<=x && x<N && 0<=y && y<N)
    		return true;
    	return false;
    }
    
   

}