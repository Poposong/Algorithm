import java.io.*;
import java.util.*;
/**
*/
class Solution {
    public int solution(int[] order) {
        int answer = 0, idx=0;
        Stack<Integer> stack = new Stack<>();
        for(int i = 1; i<order.length+1; i++){
            if(i == order[idx]){
                answer++;
                idx++;
            }else if(order[idx] > i){
                stack.push(i);
            }else if(order[idx] < i){
                if(stack.isEmpty()) return answer;
                
                if(stack.peek() != order[idx]){
                    return answer;
                }else if(stack.peek() == order[idx]){
                    stack.pop();
                    i--;
                    answer++;
                    idx++;
                }
            }
        }
        for(; idx < order.length; idx++){
            
            if(stack.isEmpty()) return answer;
            
            if(stack.peek() == order[idx]){
                stack.pop();
                answer++;
            }else{
                return answer;
            }
        }
        
        return answer;
    }
}