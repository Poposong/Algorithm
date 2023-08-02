import java.io.*;
import java.util.*;

// paths : {i, j, w} => i부터 j까지 w시간이 걸린다.
// gates => gates의 원소는 출입구
// summits => summits의 원소는 산봉우리
// 출입구이면서 산봉우리인 지점은 없다.
// gates와 summits에 등장하지 않은 지점은 모두 쉼터이다.
// 임의의 두 지점 사이에 이동 가능한 경로가 항상 존재한다.
// return [산봉우리의 번호, intensity의 최솟값]
class Node{
    int vertex;
    int weight;
    
    public Node(int vertex, int weight){
        this.vertex = vertex;
        this.weight = weight;
    }
}

class Solution {
    public static boolean[] visited;// 해당 정점을 방문했는지 확인한다.
    
    public static boolean[] start, end;
    
    public static Map<Integer, ArrayList<Node>> graph;
    
    public static int result;
        
    public static int[] answer = {Integer.MAX_VALUE, Integer.MAX_VALUE}; 
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
       // 산봉우리의 번호, intensity의 최솟값
        
        visited = new boolean[n+1];
        
        // 출발지 & 산봉우리 세팅
        start = new boolean[n+1]; // 출발지 정점을 체크하기 위한 배열
        
        end = new boolean[n+1]; // 산봉우리 정점을 체크하기 위한 배열
        
        Arrays.fill(start, false);
        for(int num : gates){
            start[num] = true;
        }
        
        Arrays.fill(end, false);
        for(int num : summits){
            end[num] = true;
        }
        
        // 그래프 생성
        graph = new HashMap<>();
        for(int i = 0; i<n+1; i++){
            graph.put(i, new ArrayList<Node>());
        }
        int v1, v2, w;
        for(int i = 0; i<paths.length; i++){
            v1 = paths[i][0];
            v2 = paths[i][1];
            w = paths[i][2];
            
            if(start[v1] || end[v2]){
                graph.get(v1).add(new Node(v2, w));    
            }else if(start[v2] || end[v1]){
                graph.get(v2).add(new Node(v1, w));    
            }else{
                graph.get(v1).add(new Node(v2, w)); 
                graph.get(v2).add(new Node(v1, w));  
            }
        }
        
        return dijikstra(n, gates, summits);

     
    }
    
    // 다익스트라 - 큐
    
    static int[] dijikstra(int n, int[] gates, int[] summits){
        Queue<Node> queue = new LinkedList<>();
        
        int[] intensity = new int[n+1];
        
        Arrays.fill(intensity, Integer.MAX_VALUE);
        
        for(int gate : gates){
            intensity[gate] = 0;
            queue.add(new Node(gate, 0));
        }
        
        Node node1;
        while(!queue.isEmpty()){
            node1 = queue.poll();
            
            if(node1.weight > intensity[node1.vertex]) continue;
            
            for(Node node2 : graph.get(node1.vertex)){
                int maxDistance = Math.max(intensity[node1.vertex], node2.weight);
                if(maxDistance < intensity[node2.vertex]){
                     intensity[node2.vertex] = maxDistance;
                     queue.offer(new Node(node2.vertex, maxDistance));   
                }
            }
        }

        
        Arrays.sort(summits);
        
        for(int summit : summits){
            if(intensity[summit] < answer[1]){
                answer[1] = intensity[summit];
                answer[0] = summit;
            }
        }
        
        return answer;
    }
    

}