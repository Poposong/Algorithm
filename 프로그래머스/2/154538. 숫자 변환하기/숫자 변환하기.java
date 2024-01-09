import java.io.*;
import java.util.*;
/**
1. dp로 x부터 y까지의 최소 연산 횟수를 저장한다.
2. PriorityQueue를 이용해서 최소 연산인 경우에 이전까지의 최소 얀산 횟수를 비교해서 같거나 작으면 진행한다.
*/
class Solution {
    public int solution(int x, int y, int n) {
        int[] dp = new int[y+1]; // x부터 y까지 사용함
        int inf = Integer.MAX_VALUE;
        Arrays.fill(dp, inf);
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            public int compare(int[] a, int[] b){ // (숫자,횟수)
                return a[1]-b[1]; // 횟수를 기준으로 오름차순
            }
        });
    
        
        dp[x] = 0;
        pq.offer(new int[]{x, 0});
        while(!pq.isEmpty()){
            int num = pq.peek()[0];
            int cnt = pq.peek()[1];
            pq.poll();
            if(cnt > dp[num]) continue;
            
            if(movePossible(dp, num+n, cnt)){
                pq.offer(new int[]{num+n, dp[num+n]});
            }
            
            if(movePossible(dp, num*2, cnt)){
                pq.offer(new int[]{num*2, dp[num*2]});
            }
            
            if(movePossible(dp, num*3, cnt)){
                pq.offer(new int[]{num*3, dp[num*3]});
            }    
        }
        
        
        return dp[y] == inf ? -1 : dp[y];
    }
    
    static boolean movePossible(int[] dp, int num, int cnt){
        if(num >= dp.length) return false;
        if(cnt+1 < dp[num]){
            dp[num] = cnt + 1;
            return true;
        }
        return false;
    }
}