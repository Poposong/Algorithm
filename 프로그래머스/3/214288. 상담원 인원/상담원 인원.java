import java.io.*;
import java.util.*;
 

class Solution {
    static int[] room;
    static int target, answer;
    public int solution(int k, int n, int[][] reqs) {
        answer = Integer.MAX_VALUE;
        
        room = new int[k+1]; // 1~k까지의 방
        Arrays.fill(room, 1);
        
        target = n-k; 
        makeRoom(0, k, n, 1, reqs);
        
        return answer;
    }
    
    // 효율성을 체크한다.
    static void minRoomCheck(int k, int n, int[][] reqs){
        ArrayList[] waitRoom = new ArrayList[k+1]; // 1~k번까지의 방에 선생님이 들어간다.
        for(int i = 1; i<k+1; i++){
            waitRoom[i] = new ArrayList<Integer>();    
            for(int j = 0; j<room[i]; j++){
                waitRoom[i].add(0); // 대기하는 선생님들을 추가한다.
            }
        }
        int waitTime = 0;
        int time = 0, hours = 0, type = 0;
        for(int[] req : reqs){
            time = req[0]; // 시각
            hours = req[1]; // 상담 시간
            type = req[2]; // 상담 유형
            
            // 선생님의 상담 가능 시간보다 현재 시각이 크다(선생님이 나를 기다림)
            Collections.sort(waitRoom[type]); // 오름차순 정렬
            if((int)waitRoom[type].get(0) <= time){
                waitRoom[type].remove(0);
                waitRoom[type].add(time+hours);
            }else{ // 대기하는 상태
                waitTime += (int)waitRoom[type].get(0) - time;
                waitRoom[type].add((int)waitRoom[type].remove(0)+hours);
            }
            if(waitTime >= answer){
                return;
            }
        }

        answer = Math.min(answer, waitTime);
        
        return;
    }
    
    static void makeRoom(int count, int k, int n, int idx, int[][] reqs){
        if(target == count){ 
            // 효율성 체크  
            minRoomCheck(k, n, reqs);
            return;            
        }

        for(int i = idx; i<k+1; i++){
            room[i]++;
            makeRoom(count+1, k, n, i, reqs);
            room[i]--;
        }
    }
}
