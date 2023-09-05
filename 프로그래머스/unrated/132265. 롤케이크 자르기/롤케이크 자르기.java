import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        
        // 최대 원소를 찾는다.
        int maxNum = -1;
        for(int num : topping){
            maxNum = Math.max(maxNum, num);
        }
        
        int[] lastBread = new int[maxNum+1]; // 제일 마지막까지 갔을 때 각 인덱스마다의 빵 개수
        
        int[] breadType = new int[topping.length+1]; // 각 인덱스까지 빵의 종류 수
        
        
        int num;
        int totalCount = 0;
        for(int i =0; i<topping.length; i++){
            num = topping[i];
            
            if(lastBread[num] == 0)
                totalCount++;
            
            breadType[i] = totalCount;
            
            lastBread[num]++;
            
            maxNum = Math.max(maxNum, num);
        }
        
        int currentCount = 0;
        boolean ischeck = false;
        for(int i = 0; i<topping.length; i++){
            num = topping[i];
            
            if(lastBread[num]!=0){
                lastBread[num]--;
                if(lastBread[num]==0){
                    totalCount--;
                }
            }
   
            if(breadType[i] == totalCount){
                if(answer == 0){
                    ischeck = true;
                }
                answer++;
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
