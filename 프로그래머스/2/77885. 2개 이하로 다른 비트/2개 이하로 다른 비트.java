import java.io.*;
import java.util.*;
// 정수 -> 바이너리 문자열 : Integer.toString(num, 2)
// 바이너리 문자열 -> 정수 : Integer.parseInt(str, 2);

class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        
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
