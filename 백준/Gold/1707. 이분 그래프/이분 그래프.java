import java.io.*;
import java.util.*;
public class Main {
    static int[] visited;

    static boolean result;
    static List<Integer>[] graph;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int k = Integer.parseInt(br.readLine());

        int v, e;
        while(k-- != 0){
            String[] str = br.readLine().split(" ");
            v = Integer.parseInt(str[0]);
            e = Integer.parseInt(str[1]);

            graph = new ArrayList[v+1];
            for(int i = 0; i<v+1; i++){
                graph[i] = new ArrayList<Integer>();
            }
            for(int i = 0; i<e; i++){
                str = br.readLine().split(" ");
                int v1 = Integer.parseInt(str[0]);
                int v2 = Integer.parseInt(str[1]);
                graph[v1].add(v2);
                graph[v2].add(v1);
            }

            visited = new int[v+1]; // 1~v까지
            Arrays.fill(visited, 0);

            result = true;
            for(int i = 1; i<v+1; i++){
                if(visited[i] == 0){
                    visited[i] = -1;
                    makeSetDfs(i, 1);
                    if(!result){
                        break;
                    }
                }
            }

            sb.append(result ? "YES" : "NO").append("\n");


        }

        System.out.println(sb.toString());
    }

    static void makeSetDfs(int vertex, int current){
        if(!result){
            return;
        }

        for(int nextVertex : graph[vertex]){
            if(visited[nextVertex] == 0){
                visited[nextVertex] = current;
                makeSetDfs(nextVertex, (-1) * current);
            }else{
                if(visited[vertex] == visited[nextVertex]){
                    result = false;
                    return;
                }
            }
        }
    }

}