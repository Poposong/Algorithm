import java.util.*;
import java.io.*;

class Solution {
    
    HashMap<String, String> parent;
    
    HashMap<String, Integer> money;

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        parent = new HashMap<>();
        money = new HashMap<>();
        
        for(int i =0; i<enroll.length; i++){
            parent.put(enroll[i], referral[i]);
            money.put(enroll[i], 0);
        }
        
        
        int m1, m2;
        for(int i = 0; i<seller.length; i++){
            int cash = amount[i]*100;
            m1 = (int)(cash*0.1);
            m2 = cash - m1;
            
            if(m1 >= 1){
                money.put(seller[i], money.getOrDefault(seller[i], 0) + m2);
                calculator(parent.get(seller[i]), m1);
            }else{
                money.put(seller[i], money.getOrDefault(seller[i], 0) + m1 + m2);
            }
        }
        
        for(int i =0; i<enroll.length; i++){
            answer[i] = money.get(enroll[i]);
        }
     
        return answer;
    }
    
    void calculator(String name, int cash){
         if(name.equals("-"))
             return;
        
         int m1 = (int)(cash*0.1);
         int m2 = cash - m1;
        
         if(m1 >= 1){
            money.put(name, money.getOrDefault(name, 0) + m2);
            calculator(parent.get(name), m1);
         }else{
            money.put(name, money.getOrDefault(name, 0) + m1 + m2);
         }
        
         return;
    }
}