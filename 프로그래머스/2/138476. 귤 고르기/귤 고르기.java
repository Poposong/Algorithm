import java.io.*;
import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        if(k == 1){
            return 1;
        }
        
        Arrays.sort(tangerine);
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                return b[1] - a[1];
            }
        });

        int cnt = 0, before = 0;
        for(int i = 0; i<tangerine.length; i++){
            if(i == 0){
                cnt = 1;
                before = tangerine[i];
                continue;
            }
            
            if(before == tangerine[i]){
                cnt++;

                if(i == tangerine.length - 1){
                    pq.offer(new int[]{before, cnt});
                }
            }else{
                pq.offer(new int[]{before, cnt});
                
                cnt = 1;
                before = tangerine[i];

                if(i == tangerine.length - 1){
                    pq.offer(new int[]{before, cnt});
                }
            }   
        }
        
            
        cnt = 0;
        while(!pq.isEmpty()){
            int[] value = pq.poll();
            
            answer++;
            
            if(value[1]+cnt >= k){
                break;
            }else{
                cnt += value[1];
            }
        }
    
        
        // int before = -1;
        // for(int i = tangerine.length-1; i>=tangerine.length-k; i--){
        //     System.out.println(tangerine[i]);
        //     if(before != tangerine[i]){
        //         before = tangerine[i];
        //         answer++;
        //     }
        // }
        return answer;
    }
}