import java.io.*;
import java.util.*;

class Solution {
    static Map<String, Integer> fruits;
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        int len = want.length;
        
        fruits = new HashMap<>();
        for(int i = 0; i<len; i++){
            fruits.put(want[i], i); // Ex) 해당 과일은 i번 인덱스에 존재한다.
        }
        int[] bucket = new int[len]; // 현재까지 담은 과일 바구니
        Arrays.fill(bucket, 0);
        int visibleCnt = 0; // 현재까지 가능한 과일의 종류
        
        for(int i = 0; i<10; i++){
            if(fruits.containsKey(discount[i])){
                int idx = fruits.get(discount[i]);
                bucket[idx]++;
                if(bucket[idx] == number[idx]) visibleCnt++;
            }
        }
        
        if(discount.length == 10){ // 10개의 상품만 있는 경우
            if(visibleCnt == len){
                return 1;
            }else{
                return 0;
            }
        }
        
        if(visibleCnt == len){
            answer++;
        }
       // System.out.println(Arrays.toString(bucket)+","+visibleCnt);
        
        int p1 = 0;
        int p2 = 9;
        
        
        for(int i = 0; i<discount.length - 10; i++){
            // p1번째에 있는 상품을 제외하고 p2+1번째 상품을 추가한다.
            if(fruits.containsKey(discount[p1])){
                int idx = fruits.get(discount[p1]);
                if(bucket[idx]==number[idx]){
                    visibleCnt--;
                }
                bucket[idx]--;
            }
            p1++;
            p2++;
            if(fruits.containsKey(discount[p2])){
                int idx = fruits.get(discount[p2]);
                bucket[idx]++;
                if(bucket[idx]==number[idx]){
                    visibleCnt++;
                }
            }
           // System.out.println(Arrays.toString(bucket)+","+visibleCnt);
            if(visibleCnt == len){
                answer++;
            }
        }
        
        return answer;
    }
}