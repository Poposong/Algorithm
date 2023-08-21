import java.util.*;

class Solution {
    public int[] solution(String[][] places) {
    	
    	int idx_ans = 0;
        int[] answer = new int[5];
        
        int[] dx = {-1,0,1,0};
        int[] dy = {0,-1,0,1};
        
        
        
        for(int i =0; i<5; i++) {
       	
        	char arr[][] = new char[5][5];
        	
        	for(int j =0; j<5; j++) {
        		for(int k =0; k<5; k++)
        			arr[j][k] = places[i][j].charAt(k);
        	}
        	
        	boolean ischeck = true; // 거리두기를 지키지 않은 학생이 존재하면 false 이다.
        	
        	for(int a =0; a<5; a++) {
        		for(int b=0; b<5; b++) {
        			if(arr[a][b] == 'O') { // 테이블이 나온 경우 동서남북 방향으로 모두 확인해서 P가 2이상이면 ischeck = false
        				int cnt = 0;
        				for(int c = 0; c<4; c++) {
        					int next_x = a + dx[c];
        					int next_y = b + dy[c];
        					
        					if(0<=next_x && next_x <5 && 0<=next_y && next_y<5) {
        						if(arr[next_x][next_y] == 'P') {
        							if(cnt == 0) cnt++;
        							else {
        								ischeck = false;
        								break;
        							}
        							
        						}
        					}
        					
        				}
        			}else if(arr[a][b] == 'P') { // 사람이 나온 경우 동서남북 방향으로 모두 확인해서 P가 1이상이
        				for(int c = 0; c<4; c++) {
        					int next_x = a + dx[c];
        					int next_y = b + dy[c];
        					
        					if(0<=next_x && next_x <5 && 0<=next_y && next_y<5) {
        						if(arr[next_x][next_y] == 'P') {
        							ischeck = false;
        							break;
        						}
        					}
        					
        				}
        			}
        		}
        		if(ischeck == false)
        			break;
        	}
        	if(!ischeck)
        		answer[idx_ans++] = 0;
        	else
        		answer[idx_ans++] = 1;
        	
        }
        return answer;
    }
}