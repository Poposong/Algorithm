import java.io.*;
import java.util.*;
class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        
        ArrayList<Integer> pA = possibleValue(arrayA);
        ArrayList<Integer> pB = possibleValue(arrayB);
        
        int num;
        boolean check;
        for(int i = pA.size()-1; i>=0; i--){
            num = pA.get(i);
            check = true;
            for(int valueB : arrayB){
                if(valueB%num == 0){
                    check = false;
                    break;
                }
            }
            
            if(check && answer < num){
                answer = num;
                break;
            }
        }

        for(int i = pB.size()-1; i>=0; i--){
            num = pB.get(i);
            check = true;
            for(int valueA : arrayA){
                if(valueA%num == 0){
                    check = false;
                    break;
                }
            }

            if(check && answer < num){
                answer = num;
                break;
            }
        }
        
        
        return answer;
    }
    
    ArrayList<Integer> possibleValue(int[] array){
        ArrayList<Integer> result = new ArrayList<>();
        Arrays.sort(array);
        int bound = array[0];
        boolean check;
        for(int i = 2; i<=bound; i++){
            check = true;
            for(int number : array){
                if(number%i != 0){
                    check = false;
                    break;
                }
            }
            if(check){
                result.add(i);
            }
        }
        return result;
    }
}