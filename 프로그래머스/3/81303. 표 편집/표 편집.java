import java.io.*;
import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        Stack<Integer> stack = new Stack();
  
        int size = n;
        String[] str;
        for(String c : cmd){
            str = c.split(" ");
            if(str[0].equals("U")){ // 위로 move
                k -= Integer.parseInt(str[1]);
            }else if(str[0].equals("D")){ // 아래로 move
                k += Integer.parseInt(str[1]);
            }else if(str[0].equals("C")){ // 삭제
                stack.push(k);
                size--;
                if(k == size){
                    k--;
                }
            }else if(str[0].equals("Z")){ // 복구
                if(stack.pop() <= k){
                    k++;
                }
                size++;
            }
        }
    
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<size; i++){
            sb.append('O');
        }
        while(!stack.isEmpty()){
            sb.insert(stack.pop().intValue(), 'X');
        }
        
        return sb.toString();
    }
}