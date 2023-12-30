import java.io.*;
import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>(){
            public int compare(Integer a, Integer b){
                return b-a;
            }
        });
        
        for(int w : works){
            pq.offer(w);
        }
        
        for(int i = 0; i<n; i++){
            
            if(pq.isEmpty()) return 0;
            
            int num = pq.poll()-1;
            if(num != 0){
                pq.offer(num);
            }
        }
        
        while(!pq.isEmpty()){
            int num = pq.poll();
            answer += Math.pow(num, 2);
        }
        return answer;
    }
}