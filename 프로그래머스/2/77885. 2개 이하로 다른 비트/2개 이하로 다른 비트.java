import java.io.*;
import java.util.*;

class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        // for(int i = 1; i<100; i++){
        //     System.out.println(i+"=>"+Long.toBinaryString((long)i));
        // }
        
        // 1. 짝수인 경우에는 숫자 + 1
        // 2. 홀수인데 1만 있는 경우
        //    => 
        
        String newNumber;
        for(int i = 0; i<numbers.length; i++){
            if(numbers[i]%2 == 0){
                answer[i] = numbers[i]+1;
            }else{
                String binaryNumber = Long.toString(numbers[i], 2);
                int zeroIdx = binaryNumber.lastIndexOf("0");
                if(zeroIdx != -1){
                    // 0과 1이 섞인 바이너리 숫자
                    newNumber = binaryNumber.substring(0, zeroIdx) + "10" + binaryNumber.substring(zeroIdx + 2, binaryNumber.length());
                }else{ // 전부 1로 이루어진 바이너리 숫자
                    newNumber = "10" + binaryNumber.substring(1, binaryNumber.length());
                }
                
                answer[i] = Long.parseLong(newNumber, 2);
            }
        }
        
    
        return answer;
    }
}