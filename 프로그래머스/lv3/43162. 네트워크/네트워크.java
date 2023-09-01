import java.io.*;
import java.util.*;

class Solution {
    
    public static Map<Integer, ArrayList<Integer>> graph;
    
    public static boolean[] visited;
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        graph = new HashMap<>();
        for(int i = 0; i<n; i++){
            graph.put(i, new ArrayList<Integer>());
        }
        
        visited = new boolean[n];
        Arrays.fill(visited, false);
        
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                if(i == j)
                    continue;
                if(computers[i][j] == 1){
                    graph.get(i).add(j);
                }
            }
        }
        
        for(int i = 0; i<n; i++){
            if(!visited[i]){
                answer++;
                dfs(i);
            }
        }
        
        
        return answer;
    }
    
    static void dfs(int v1){
        for(int v2 : graph.get(v1)){
            if(!visited[v2]){
                visited[v2] = true;
                dfs(v2);
            }
        }
    }
}