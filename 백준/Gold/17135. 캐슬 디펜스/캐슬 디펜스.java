import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class Dot{
	int x;
	int y;
	
	public Dot(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
    public static int N, M, D;

    public static int[][] map;

    public static int[][] copy_map;
    
    public static int archer_x1;
    
    public static List<Dot> enemy;
    
    public static List<Dot> copy_enemy;
    
    public static int[] archer; // 궁수
    
    public static boolean[] visited;
    
    public static int sum = 0, answer = Integer.MIN_VALUE;
    
    public static void main(String[] args) throws IOException {     
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        D = Integer.parseInt(str[2]);
        
       
        map = new int[N][M];
        copy_map = new int[N][M];
        
        archer_x1 = N;
        archer = new int[3];
        
        visited = new boolean[M];
        
        enemy = new ArrayList<>();
        
        for(int i =0; i<N; i++) {
        	str = br.readLine().split(" ");
        	for(int j =0; j<M; j++) {
        		map[i][j] = Integer.parseInt(str[j]);
        		if(map[i][j] == 1)
        			enemy.add(new Dot(i, j));
        	}
        }
        
        
        archerChoice(0,0);
        
        System.out.println(answer);
        

    }
    
    static void moveEnemy() {
    	//System.out.println("1-MOVE_ENEMY_SIZE : "+copy_enemy.size());
    	
    	for(int i =0; i<copy_enemy.size(); i++) {
    		Dot dot = copy_enemy.get(i);
    		if(archer_x1 <= dot.x)
    			copy_enemy.remove(i--);
    	}
    	
    	//System.out.println("2-MOVE_ENEMY_SIZE : "+copy_enemy.size());
    	
    }
    
    // 3명의 궁수가 적을 공격하는 경우
    static void attach() {
    	//System.out.println("1-ATTACK_ENEMY_SIZE : "+copy_enemy.size());
    	
    	List<Dot> tempList = new ArrayList<>();
    	
    	for(int i =0; i<3; i++) {
    		int archer_y1 = archer[i];
    		
    		Dot temp = new Dot(-1,-1);
    		
    		int deeper = Integer.MAX_VALUE;
    		
    		for(int j =0; j<copy_enemy.size(); j++) {
    			Dot dot2 = copy_enemy.get(j);
    			int differ = Math.abs(archer_x1 - dot2.x) + Math.abs(archer_y1 - dot2.y);
    			if(differ <= D) {
    				if(differ < deeper) {
    					deeper = differ;
    					temp.x = dot2.x;
    					temp.y = dot2.y;
    				}else if(differ == deeper) {
    					if(dot2.y < temp.y){ // 현재의 y값보다 작은 y값이 있다면?(왼쪽)
    						temp.x = dot2.x;
    						temp.y = dot2.y;
    					}
    				}
    			}
    		}
    		
    		// 가장 가까운 적을 찾은 경우(적을 삭제한다)
    		if(deeper != Integer.MAX_VALUE) {
    			if(copy_map[temp.x][temp.y] == 1) {
  
    				tempList.add(new Dot(temp.x,temp.y));
    				copy_map[temp.x][temp.y] = 0;
    				sum++;
    			}
    		}
    		
    	}
    	
    	
    	boolean ischeck = true;
    	for(int i =0; i<copy_enemy.size(); i++) {
    		Dot d1 = copy_enemy.get(i);
    		ischeck = true;
    		for(int j =0; j<tempList.size(); j++) {
    			Dot d2 = tempList.get(j);
    			if(d1.x == d2.x && d1.y == d2.y) {
    				ischeck = false;
    				break;
    			}
    		}
    		if(!ischeck) {
    			copy_enemy.remove(i--);
    		}
    	}
    	
    	//System.out.println("2-ATTACK_ENEMY_SIZE : "+copy_enemy.size());
    	
    }
    
    static void copyData() {
    	// copy_map에 map 배열의 데이터를 복사하고
    	// copy_enemy에 enemy 리스트의 데이터를 복사한다.
    	
    	copy_map = new int[N][M];
    	copy_enemy = new LinkedList<>();
    	
    	for(int i =0; i<N; i++) {
    		for(int j =0; j<M; j++)
    			copy_map[i][j] = map[i][j];
    	}
    	
    	for(Dot dot : enemy) 
    		copy_enemy.add(dot);
    }
    
    static void archerChoice(int count,int start) {

    	if(count == 3) {
    		//System.out.println(Arrays.toString(visited));
    		
    		// 3명의 궁수를 뽑았다.
    		sum = 0;
    		
    		copyData();
    		
    		archer_x1 = N;
    		while(!copy_enemy.isEmpty()) {
    			//System.out.println(archer_x1);
    			attach();
    			
    			archer_x1--;

        		moveEnemy();
    		}
    		//System.out.println(archer[0]+","+archer[1]+","+archer[2]);
    		///System.out.println("sum : "+ sum);
    		//System.out.println("---------------");
    		
    		answer = Math.max(answer, sum);
    		
    		//System.out.println("RESULT :" + sum+"/"+answer);
    		
    		return;
    	}
    	for(int i = start; i<M; i++) {
    		if(!visited[i]) {
    			visited[i] = true;
    			archer[count] = i;
    			archerChoice(count+1, i);
    			visited[i] = false;
    		}
    	}
    }

}