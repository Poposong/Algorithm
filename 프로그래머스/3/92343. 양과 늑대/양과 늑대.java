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
        }else{ // 늑대
            wolf++;
        }
        
        if(wolf >= sheep) return;
        
        maxSheep = Math.max(maxSheep, sheep);
        
        ArrayList<Integer> copyPath = new ArrayList<>(path);
        for(int vertex : graph.get(curNode)){ // 현재 노드가 갈 수 있는 자식 노드들을 copyPath 리스트에 넣어준다.
            copyPath.add(vertex);
        }
        copyPath.remove(Integer.valueOf(curNode)); // 부모 노드에서 자식 노드로 가는 경우는 체크했으므로 제외시킨다.
        for(int nextNode : copyPath){ // 자식 노드와 형제 노드 또는 상속 받은 노드들로부터 갈 수 있는 경우를 모두 체크한다.
            dfs(sheep, wolf, nextNode, info, copyPath);
        }
    }
}
