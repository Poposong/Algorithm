import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int answer = -1;
        
        int maxNum = -1;
        
        for(int num : topping){
            maxNum = Math.max(maxNum, num);
        }
        
        int[] current = new int[maxNum+1];
        
        int[] count = new int[topping.length+1];
        
        
        int totalCount = 0;
        
        int num;
        
        for(int i =0; i<topping.length; i++){
            num = topping[i];
            
            if(current[num] == 0)
                totalCount++;
            
            count[i] = totalCount;
            
            current[num]++;
            
            maxNum = Math.max(maxNum, num);
        }
        
        // totalCount
        int currentCount = 0;
        boolean ischeck = false;
        for(int i = 0; i<topping.length; i++){
            num = topping[i];
            
            if(current[num]!=0){
                current[num]--;
                if(current[num]==0){
                    totalCount--;
                }
            }
         //   System.out.println(i+"->");
            if(count[i] == totalCount){
                if(answer == -1){
                    answer = 1;
                    ischeck = true;
                }else{
                    answer++;
                }
            }else{
                if(ischeck)
                    break;
            }
        }
  
        if(ischeck)
            return answer;
        else
            return 0;
        
     
    }
}