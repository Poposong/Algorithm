import java.io.*;
import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        Arrays.sort(book_time, new Comparator<String[]>(){
            public int compare(String[] table1, String[] table2){
                if(table1[0].equals(table2[0])){
                    return table1[1].compareTo(table2[1]);
                }
                return table1[0].compareTo(table2[0]);
            }
        });
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        
        
        int start, end;
        for(String[] table : book_time){
            start = Integer.parseInt(table[0].replace(":",""));
            end = Integer.parseInt(table[1].replace(":",""));
            
            end += 10;
            
            if(end%100 >= 60){
                end += 40;
            }
      
           // System.out.println(start+","+end);
            if(pq.isEmpty()){
                pq.offer(end);
                answer++;
            }else{
                if(pq.peek() <= start){
                    pq.poll();
                }else{
                    answer++;
                }
                pq.offer(end);
            }
            
        }
        
        return answer;
    }

}