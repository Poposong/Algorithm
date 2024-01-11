import java.io.*;
import java.util.*;

// 누적합... 너무 어렵당 ㅠㅠ

class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        int len = Integer.MAX_VALUE;
        int sum = sequence[0];
        for(int left = 0, right = 0; right < sequence.length; ){
           // System.out.println(left+","+right+"=>"+sum);
            if(sum < k){
                right++;
                if(right >= sequence.length) break;
                sum += sequence[right];
            }else if(sum > k){
                sum -= sequence[left];
                left++;
            }else{
                if(right-left+1 < len){
                    len = right-left+1;
                    answer[0] = left;
                    answer[1] = right;
                }
                right++;
                if(right >= sequence.length) break;
                sum += sequence[right];
            }
        }
        return answer;
    }
}