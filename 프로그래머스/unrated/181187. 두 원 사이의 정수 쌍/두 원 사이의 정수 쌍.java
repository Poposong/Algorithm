import java.io.*;
import java.util.*;
class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;

        long num1, num2;
        
        answer += r2-r1+1;

        for(int x = 1; x < r2; x++){
            if(x < r1){
                num1 = (int)Math.ceil(Math.sqrt(1.0*r1*r1 - 1.0*x*x));
                num2 = (int)Math.floor(Math.sqrt(1.0*r2*r2 - 1.0*x*x));
                answer += (num2-num1+1);
            }else{
                answer += Math.floor(Math.sqrt(1.0*r2*r2 - 1.0*x*x));
            }
        }
        
        return answer*4;
    }
}
