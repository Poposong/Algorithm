import java.io.*;
import java.util.*;


class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        
        if(routes.length == 1)
            return 1;
        
        // O(10,000log(10,000))        
        Arrays.sort(routes, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                if(a[0] == b[0]){
                    return a[1]-b[1]; // 오름차순
                }
                return a[0]-b[0]; // 오름차순
            }
        });
     
        // O(10,000)
        int r1 = routes[0][0], r2 = routes[0][1];
        int n1, n2;
        for(int i = 1; i<routes.length; i++){
            n1 = Math.max(r1, routes[i][0]);
            n2 = Math.min(r2, routes[i][1]);
            if(n1 <= n2){
                r1 = n1;
                r2 = n2;
            }else{
                answer++;
                r1 = routes[i][0];
                r2 = routes[i][1];
            }
          //  System.out.println(r1+","+r2+",");
        }
        
        return answer+1;
    }
}
