import java.io.*;
import java.util.*;

class Solution {
    static Map<Integer, ArrayList<Integer>> graph;
    static int maxSheep;
    public int solution(int[] info, int[][] edges) {
        int answer = 0;
        graph = new HashMap<>();
        for(int i = 0; i<info.length; i++){
            graph.put(i, new ArrayList<>());
        }
        for(int[] edge : edges){
            graph.get(edge[0]).add(edge[1]);
        }
        
        maxSheep = 0;
        
        dfs(0, 0, 0, info, new ArrayList<>(Arrays.asList(0)));

        return maxSheep;
    }
    
    static void dfs(int sheep, int wolf, int curNode, int[] info, ArrayList<Integer> path){
        if(info[curNode] == 0){ // 양
            sheep++;
        }else{
            wolf++;
        }
        
        if(wolf >= sheep) return;
        
        maxSheep = Math.max(maxSheep, sheep);
        
        ArrayList<Integer> copyPath = new ArrayList<>(path);
        for(int vertex : graph.get(curNode)){
            copyPath.add(vertex);
        }
        copyPath.remove(Integer.valueOf(curNode));
        for(int nextNode : copyPath){
            dfs(sheep, wolf, nextNode, info, copyPath);
        }
    }
}