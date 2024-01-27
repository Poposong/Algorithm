import java.io.*;
import java.util.*;

public class Main {
    static boolean isCheck;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        List<Integer>[] graph = new ArrayList[n];
        for(int i = 0; i<n; i++){
            graph[i] = new ArrayList<Integer>();
        }

        int v1,v2;
        for(int i = 0; i<m; i++){
            str = br.readLine().split(" ");
            v1 = Integer.parseInt(str[0]);
            v2 = Integer.parseInt(str[1]);

            graph[v1].add(v2);
            graph[v2].add(v1);
        }

        boolean[] visited = new boolean[n];
        for(int i = 0; i<n; i++){
            isCheck = false;
            Arrays.fill(visited, false);
            visited[i] = true;
            dfs(i, 1, graph, visited);
            if(isCheck){
                System.out.println(1);
                return;
            }
        }
        System.out.println(0);

    }

    static void dfs(int vertex, int cnt, List[] graph, boolean[] visited){
        if(isCheck){
            return;
        }
        if(cnt == 5){
            isCheck = true;
            return;
        }
        for(int i = 0; i<graph[vertex].size(); i++){
            int next = (int) graph[vertex].get(i);
            if(!visited[next]){
                visited[next] = true;
                dfs(next, cnt+1, graph, visited);
                visited[next] = false;
            }
        }
    }
}