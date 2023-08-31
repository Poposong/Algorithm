import java.io.*;
import java.util.*;

class Solution {
    public static List<Integer> data;
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        
        // 오름차순 정렬
        data = new LinkedList<>();
        
        String[] str;
        int num;
        for(String operation : operations){
            str = operation.split(" ");
            
            num = Integer.parseInt(str[1]);
            
            if(str[0].equals("I")){
                data.add(num);
            }else{ // D
                if(data.isEmpty())
                    continue;
                reOrder();
                if(num == -1){ // 최소를 구하기
                    data.remove(0);
                }else{ // 최대를 구하기
                    data.remove(data.size()-1);
                }
            }
        }
        
        if(data.isEmpty()){
            answer[0] = 0;
            answer[1] = 0;
        }else if(data.size() == 1){
            answer[0] = answer[1] = data.get(0);
        }else{
            reOrder();
            answer[0] = data.get(data.size()-1);
            answer[1] = data.get(0);
        }

        return answer;
    }
    
    static void reOrder(){
        Collections.sort(data, new Comparator<Integer>(){
            @Override
            public int compare(Integer n1, Integer n2){
                return n1-n2;
            }
        });
    }
}
