import java.io.*;
import java.util.*;
// 1. arrayA에서 최소공배수, arrayB에서 최소공배수를 각각 구한다.
// 2. 
class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        Arrays.sort(arrayA);
        Arrays.sort(arrayB);
        
        int gcdA = arrayA[0];
        int gcdB = arrayB[0];
        
        for(int i = 1; i<arrayA.length; i++){
            gcdA = gcd(gcdA, arrayA[i]);
            gcdB = gcd(gcdB, arrayB[i]);
        }
        
        boolean check = true;
        for(int value : arrayB){
            if(value%gcdA == 0){
                check = false;
                break;
            }
        }
        if(check){
            answer = gcdA;
        }
        
        check = true;
        for(int value : arrayA){
            if(value%gcdB == 0){
                check = false;
                break;
            }
        }
        if(check){
            answer = Math.max(answer, gcdB);
        }
        
        
        
        
        return answer;
    }
    
    public int gcd(int a, int b){
        if(a%b == 0) return b;
        return gcd(b, a%b);
    }
}