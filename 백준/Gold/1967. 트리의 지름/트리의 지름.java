import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

class Node{
    int vertex;
    int weight;
    
    
    public Node(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }
}

public class Main {
    
    public static int n;
    
    public static List<Node>[] graph;
    
    public static boolean[] visited;
    
    public static int result;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringBuilder sb = new StringBuilder();
        
        n = Integer.parseInt(br.readLine());
        
        
        graph = new ArrayList[n+1];
        
        for(int i =0; i<n+1; i++) // 인덱스 : 1~n
            graph[i] = new ArrayList<>();
        
        String[] str;
        int v1,v2,w;
        for(int i =0; i<n-1; i++) {
            str = br.readLine().split(" ");
            v1 = Integer.parseInt(str[0]);
            v2 = Integer.parseInt(str[1]);
            w = Integer.parseInt(str[2]);
            graph[v1].add(new Node(v2,w));
            graph[v2].add(new Node(v1,w));
        }
        
        result = 0;
        
	
		for(int i =1; i<=n; i++) {
			visited = new boolean[n+1]; // 인덱스 : 1~n
			visited[i] = true;
			DFS(i, 0);
		}
		
        
        System.out.println(result);
        
        
    }
    
    static void DFS(int idx, int sum) {
    	
        for(Node node : graph[idx]) {
        	if(!visited[node.vertex]) {
        		visited[node.vertex] = true;
        		DFS(node.vertex, sum + node.weight);
        	}
        }
        
        result = Math.max(result, sum);
       
    }
    
}