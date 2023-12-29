import java.io.*;
import java.util.*;

class Solution {
    public int solution(int storey) {
        int answer = 0;
        
        int one, ten;
        while(storey != 0){
            one = storey % 10; // 일의 자리
            ten = (storey / 10) % 10; // 십의 자리
            
            if(one > 5){
                answer += (10-one);
                storey += 10;
            }else if(one == 5){
                answer += one;
                storey += (ten < 5 ? 0 : 10);
            }else{
                answer += one;
            }
            
            storey /= 10;
        }
        
        return answer;
    }
}