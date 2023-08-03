
// cap : 트럭에 실을 수 있는 택배 상자의 최대 개수
// n : 배달할 집의 개수
// deliveries : 각 집에 배달할 택배 상자의 개수를 담은 1차원 정수 배열
// pickups : 각 집에서 수거할 빈 재활용 택배 상자의 개수를 담은 1차원 정수 배열
// Q. 트럭 하나로 모든 배달과 수거를 마치고 물류창고로 돌아올 수 있는 최소 이동 거리를 반환해라

import java.io.*;
import java.util.*;


class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        Stack<Integer> ds = new Stack<>();
        Stack<Integer> ps = new Stack<>();
        
        for(int i = 0; i<n ; i++){
            ds.add(deliveries[i]);
            ps.add(pickups[i]);
        }
        
        while(!ds.isEmpty() || !ps.isEmpty()){
            while(!ds.isEmpty() && ds.peek() == 0){
                ds.pop();
            }
            
            while(!ps.isEmpty() && ps.peek() == 0){
                ps.pop();
            }
            
            answer += Math.max(ds.size(), ps.size()) * 2;
            
            int sum = 0, num;
            while(!ds.isEmpty()){
                num = ds.pop();
                
                if(num + sum <= cap){
                    sum += num;
                }else{
                    ds.add(sum + num - cap);
                    break;
                }
            }
            
            sum = 0;
            while(!ps.isEmpty()){
                num = ps.pop();
                
                if(num + sum <= cap){
                    sum += num;
                }else{
                    ps.add(sum + num - cap);
                    break;
                }
            }
            
        }
        
        return answer;
    }
}