import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        
        List<Integer> list = new ArrayList<>();
        long f = 1L;
        for(int i = 1; i<n+1; i++){
            list.add(i);
            f *= i;
        }
        
        k--;
        int idx = 0;
        while(idx < n){
            f /= n-idx;
            answer[idx++] = list.remove((int)(k/f));
            k%=f;
        }
        
        
        return answer;
      
    }
}