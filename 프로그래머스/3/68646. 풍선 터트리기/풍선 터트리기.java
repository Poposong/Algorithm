import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] a) {
        int answer = 0;
        int len = a.length;
        int[] left = new int[len];
        int[] right = new int[len];
        
        left[0] = a[0];
        right[len-1] = a[len-1];
        
        for(int i = 1, j = len-2; i<len && j>=0 ; i++, j--){
            left[i] = Math.min(left[i-1], a[i]);
            right[j] = Math.min(right[j+1], a[j]);
        }
        
    
        
        for(int i = 0; i<len; i++){
            if(!(left[i] < a[i] && a[i] > right[i])){
                answer++;
            }
        }
        
        return answer;
    }
}