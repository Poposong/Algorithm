import java.io.*;
import java.util.*;


public class Main {
    static int N, Q;
    static Map<Integer, LinkedList<int[]>> graph;
    static boolean[] visited;

    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        Q = Integer.parseInt(str[1]);

        graph = new HashMap<>();
        for(int i = 0; i<N+1; i++){
            graph.put(i, new LinkedList<int[]>());
        }

        visited = new boolean[N+1];

        // 사이클이 생기지 않음
        int v1,v2,w;
        for(int i = 0; i<N-1; i++){
            str = br.readLine().split(" ");
            v1 = Integer.parseInt(str[0]);
            v2 = Integer.parseInt(str[1]);
            w = Integer.parseInt(str[2]);

            graph.get(v1).add(new int[]{v2, w});
            graph.get(v2).add(new int[]{v1, w});
        }

        int k,v;
        while(Q-- != 0){
            str = br.readLine().split(" ");
            k = Integer.parseInt(str[0]);
            v = Integer.parseInt(str[1]);
            
            result = 0;
            Arrays.fill(visited, false);
            visited[v] = true;
            
            moveVertex(k, v);
            sb.append(result).append("\n");
        }

        System.out.println(sb.toString());


    }

    static void moveVertex(int k, int v){
        int v1, w1;
        for(int[] value : graph.get(v)){
            v1 = value[0];
            w1 = value[1];

            if(!visited[v1] && w1 >= k){
                visited[v1] = true;
                result++;
                moveVertex(k, v1);
            }
        }
    }
}