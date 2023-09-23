import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        Arrays.sort(A);
        Arrays.sort(B);
        
        int idx_A = A.length-1;
        int idx_B = B.length-1;
        
        for(; idx_A >=0; idx_A--){
            if(A[idx_A] < B[idx_B]){
                idx_B--;
                answer++;
            }
        }
        
        
        
        //System.out.println(Arrays.toString(A));
        return answer;
    }
}