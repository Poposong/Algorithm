import java.io.*;
import java.util.*;

class Solution {
    static int[] parents;
    public int solution(int n, int[][] costs) {
        int answer = 0;

        Arrays.sort(costs, new Comparator<int[]>(){
            public int compare(int[] cost1, int[] cost2){
                return cost1[2] - cost2[2];
            }
        });

        parents = new int[n];
        for(int i = 0; i<n; i++)
            parents[i] = i;
        
        
        for(int[] cost : costs){
            if(findParent(cost[0]) != findParent(cost[1])){
                union(cost[0], cost[1]);
                answer += cost[2];
            }
        }
        
       // System.out.println(Arrays.toString(parents));
        

        return answer;
    }
    
    static void union(int a, int b){
        int pA = findParent(a);
        int pB = findParent(b);
        
        if(pA < pB){
            parents[pB] = pA;
        }else{
            parents[pA] = pB;
        }
    }
    
    static int findParent(int i){
        if(parents[i] == i)
            return i;
        return findParent(parents[i]);
    }
}