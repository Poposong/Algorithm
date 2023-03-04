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
    
    public static int R,C,T;
    
    public static int[][] map; 
    public static int[][] copyMap;
    
    
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};

    
    public static int x1 = -1, x2, y1, y2;

    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        String[] str = br.readLine().split(" ");
        
        R = Integer.parseInt(str[0]);
        C = Integer.parseInt(str[1]);
        T = Integer.parseInt(str[2]);
        
        map = new int[R][C];
        copyMap = new int[R][C];
       
        for(int i = 0; i<R; i++) {
            str = br.readLine().split(" ");
            for(int j =0; j<C; j++) {
                map[i][j] = Integer.parseInt(str[j]);
                if(map[i][j] == -1 && x1 == -1) { // 공기청정기
                	x1 = i;
                	x2 = i+1;
                	y1 = 0;
                	y2 = 0;
                }
            }
        }
        
        
        for(int i =0; i<T; i++) {
        	dust();
        }
        
        
        int result = 0;
        for(int i  =0; i<R; i++) {
        	for(int j =0; j<C; j++) {
        		if(map[i][j] != -1)
        			result += map[i][j];
        	}
        }
        System.out.println(result);
       // visited = new boolean[R][C];

    }
    
    public static void airfresh() {
    	
    	/*
    	 System.out.println("BEFORE -> ");
    	for(int i =0; i<R; i++)
    		System.out.println(Arrays.toString(copyMap[i]));
    	System.out.println(); 
    	 */
    	

    	int before = copyMap[x1][1];
    	int temp;
    	copyMap[x1][1] = 0;
    	for(int j = 2; j<C; j++) {

    		temp = copyMap[x1][j];
    		copyMap[x1][j] = before;
    		before = temp;
    	}
    	
    	if(x1 != 0) {
    		for(int i = x1-1; i>=0; i--) {

        		temp = copyMap[i][C-1];
        		copyMap[i][C-1] = before;
        		before = temp;
        	}
    		
    		for(int j = C-2; j>=0; j--) {

        		temp = copyMap[0][j];
        		copyMap[0][j] = before;
        		before = temp;
        	}
        	
    		for(int i = 1; i<x1; i++) {

    			temp = copyMap[i][0];
    			copyMap[i][0] = before;
    			before = temp;
    		}
    	}
    	
    	before = copyMap[x2][1];
    	copyMap[x2][1] = 0;
    	for(int j = 2; j<C; j++) {

    		temp = copyMap[x2][j];
    		copyMap[x2][j] = before;
    		before = temp;
    	}
    	
    	if(x2 != R-1) {
    		for(int i = x2 + 1; i < R ;i++) {

    			temp = copyMap[i][C-1];
    			copyMap[i][C-1] = before;
    			before = temp;
    		}

    		for(int j = C-2; j>=0; j--) {
    
    			temp = copyMap[R-1][j];
    			copyMap[R-1][j] = before;
    			before = temp;
    		}

    		for(int i = R-2; i>x2; i--) {

    			temp = copyMap[i][0];
    			copyMap[i][0] = before;
    			before = temp;
    		}
    		
    		
    	}
    	
    	/*
    	 System.out.println("AFTER -> ");
    	for(int i =0; i<R; i++)
    		System.out.println(Arrays.toString(copyMap[i]));
    	 */


    }
    
    public static void dust() {
    	
    	ArrayList<Dot> dotList;
    	
    	for(int i =0; i<R; i++) {
    		for(int j =0; j<C; j++) {
    			if(map[i][j] != -1 && map[i][j] != 0) {
    				dotList = new ArrayList<>();
    				int nx = 0,ny = 0;
    				
    				int v = 0, e = 0;
    				
    				for(int k =0; k<4; k++) {
    					nx = i + dx[k];
    					ny = j + dy[k];
    					
    					if(rangeCheck(nx, ny)) {
    						if(map[nx][ny] != -1)
    							dotList.add(new Dot(nx,ny));
    					}
    				}
    				
    				e = map[i][j] / 5;
	        		v = map[i][j] - (e*dotList.size());
	        		
	        		if(e != 0) {
	        			for(Dot d : dotList) {
	            			copyMap[d.x][d.y] += e;
	            		}
	        		}
	        		
	        		copyMap[i][j] += v;
    			}
    		}
    	}
    	
    	airfresh();

		// map에 깊은 복사
		for(int i =0; i<R; i++)
	   		map[i] = Arrays.copyOf(copyMap[i], C+1);
		
		for(int i =0; i<R; i++) 
			Arrays.fill(copyMap[i], 0);
		
		map[x1][y1] = -1;
		map[x2][y2] = -1;

    	
    //	for(int i =0; i<R; i++)
    	//	System.out.println(Arrays.toString(copyMap[i]));
    	//System.out.println("-------------------");
    }
    
    public static boolean rangeCheck(int x,int y) {
        if(0 <= x && x <R && 0<=y && y <C)
            return true;
        return false;
    }

}