import java.io.*;
import java.util.*;


class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        
        int i = 1, j;
        int left, right;
        int range = w*2+1;
        for(int station : stations){
            left = station - w - 1;
            right = station + w + 1;
            
            if(left < i){
                i = right;
            }else{
                int term = left - i + 1;
                int sum = 0;
                if(term <= range){
                    sum = range;
                    answer++;
                }else{
                    sum = (term/range)*range;
                    answer += (term/range);
                    if(term%range!=0){
                        sum += range;
                        answer++;
                    }
                }
                
                if(i+sum-1 >= right){
                    i = i+sum-1;
                }else{
                    i = right;
                }
                
            }
        }
        
        if(i<n+1){
            int term = n - i + 1;
            
            if(term <= range){
                answer++;
            }else{
                answer += term/range;
                if(term%range != 0){
                    answer += 1;
                }
            }
            
        }

        return answer;
    }
}