import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;



public class Main {
    
    // 세로선의 개수 N, 가로선의 개수 M, 세로선마다 가로선을 놓을 수 있는 위치의 개수 H
    public static int N,M,H; 
    
    public static int[][] visited;
    
    public static int target = 1;
    
    public static int bridge = 1;
    
    public static int answer = -1;
    
    
    public static boolean ischeck = false;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        H = Integer.parseInt(str[2]);
        
        visited = new int[H+1][N+1]; // 사다리의 방문을 체크할 배열
        
        int v1,v2;
        for(int i =0; i<M; i++) {
            str = br.readLine().split(" ");
            v1 = Integer.parseInt(str[0]);
            v2 = Integer.parseInt(str[1]);
            visited[v1][v2] = bridge;
            visited[v1][v2+1] = bridge;
            bridge++;
        }
        
   
        
        for(int i =0; i<=3; i++) {
        	target = i;
        	subSetPath(1,0);
        	if(ischeck)
        		break;
        }
        
        System.out.println(answer);
    }
    
    static void subSetPath(int s1, int count) {
    	if(count == target) {
            
    		if(findRightPath()) {
    			ischeck = true;
    			answer = count;
    		}
    		
    		return;
    	}
    	
    	
    	for(int i =s1; i<H+1; i++) {
    		for(int j =1; j<N+1; j++) {
    			if(visited[i][j] == 0) {
    				// 왼쪽으로 사다리가 접하는지 확인한다.
    				//System.out.println(i+","+j);
    				if(0<j-1) {
    					if(visited[i][j-1] == 0) {
    						visited[i][j] = bridge;
    						visited[i][j-1] = bridge;
    						bridge++;
    						subSetPath(i,count+1);
    						visited[i][j] = 0;
    						visited[i][j-1] = 0;
    						bridge--;
    					}
    				}
    				
    				// 오른쪽으로 사다리가 접하는지 확인한다.
    				if(j+1 < N) {
    					if(visited[i][j+1] == 0) {
    						visited[i][j] = bridge;
    						visited[i][j+1] = bridge;
    						bridge++;
    						subSetPath(i,count+1);
    						visited[i][j] = 0;
    						visited[i][j+1] = 0;
    						bridge--;
    					}
    				}
    				
    			}
    		}
    	}
    }
    // 각 자리가 제자리를 찾아간 경우 true를 반환한다.
    static boolean findRightPath() {
    	int current;
    	for(int col = 1; col<N+1; col++) {
    		current = col;
    		for(int row = 1; row < H+1; row++) {
    			
    			if(visited[row][current] == 0)
    				continue;

    			// 왼쪽 -> 오른쪽 검사한다.
    			if(current-1 != 0) {
    				if(visited[row][current] == visited[row][current-1]) {
    					current--;
    					continue;
    				}
    			}
    			
    			if(current + 1 != N+1) {
    				if(visited[row][current] == visited[row][current+1]) {
    					current++;
    					continue;
    				}
    			}
    		}
    		//System.out.println(col +"/" +current);
    		if(current != col)
    			return false;
    	}
    	
    	//System.out.println("-----");
    	return true;
    
    }
    


    
}