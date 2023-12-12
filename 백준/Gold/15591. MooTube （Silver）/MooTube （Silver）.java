package Baekjoon;

import java.io.*;
import java.util.*;

// [시간복잡도 풀이]
// DFS는 그래프를 인접행렬로 구현하는지? 인접리스트로 구현하는지? 에 따라서 시간복잡도가 다르다.
// 인접행렬로 구현하는 경우 => O(n^2)
// 인접리스트로 구현하는 경우 => 만약, 모든 정점과 간선을 다 방문한 경우에는 O(정점+간선) = O(n+e)가 된다.

// 아래 문제에서 정점인 N은 최악의 경우 5000개가 생성될 수 있고 간선은 N-1개인 4999개가 생성될 수 있다.
// 이때, 인접행렬로 구현하면 O(5000*5000)이고 인접리스트는 O(5000+4999)이다.
// 따라서, 인접리스트로 구현했다. O(25,000,000) > O(9999)

// DFS가 최대 Q번(5,000)번 돌려질 수 있으므로
// 최종 시간복잡도는 O(Q * (n+e)) = O(5000 * 9999)이다.
// 49,995,000<200,000,000 이므로 해당 코드가 시간 안에 들어올 수 있다.

public class BJ_15591_MooTube {
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
