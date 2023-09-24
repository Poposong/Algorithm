import java.io.*;
import java.util.*;

class Node{
    int vertex;
    int weight;
    
    public Node(int vertex, int weight){
        this.vertex= vertex;
        this.weight= weight;
    }
}
class Solution {
    static Map<Integer, ArrayList<Integer>> graph;
    static int[] minPath;
    static int minMaxPath = 0;
    public int solution(int n, int[][] edge) {
        int answer = 0;
        graph = new HashMap<>();
        for(int i = 0; i<n+1; i++){
            graph.put(i, new ArrayList<Integer>());
        }
        
        minPath = new int[n+1];
        Arrays.fill(minPath, Integer.MAX_VALUE);
        
        for(int[] e : edge){
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }
        
        findMinPath();
        
        for(int i = 2; i<n+1; i++){
            if(minMaxPath == minPath[i])
                answer++;
        }
        
        return answer;
    }
    
    static void findMinPath(){
        Queue<Node> queue = new ArrayDeque<>();
        
        minPath[1] = 0;
        queue.add(new Node(1, 0));
        
        Node node;
        while(!queue.isEmpty()){
            node = queue.poll();
            
            if(minPath[node.vertex] < node.weight)
                continue;
            
            
            for(int tempV : graph.get(node.vertex)){
                if(minPath[node.vertex]+1 < minPath[tempV]){
                    minPath[tempV] = minPath[node.vertex]+1;
                    minMaxPath = Math.max(minMaxPath, minPath[tempV]);
                    queue.add(new Node(tempV, minPath[tempV]));
                }
            }
        }
    }
}