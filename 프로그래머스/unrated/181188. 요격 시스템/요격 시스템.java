import java.util.*;
import java.io.*;

class Solution {
  
    
    public int solution(int[][] targets) {
        
        int answer = 0;
        
        Arrays.sort(targets, new Comparator<int[]>(){
           @Override
            public int compare(int[] t1, int[] t2){
                return t1[1] - t2[1];
            }
        });
        
       int last = -1;
       
       for(int[] target : targets){
           if(last == -1){
               answer++;
               last = target[1]-1;
               continue;
           }
           
           if(target[0] <= last && last < target[1]) continue;
           
           answer++;
           last = target[1] - 1;
       }
        
        return answer;
     
    }
}