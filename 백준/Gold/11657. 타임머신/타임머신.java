import java.io.*;
import java.util.*;

// 정점의 수만큼 edge를 다 계산한다 O(nm) → 정점의 마지막을 도는 경우에 최단 거리가 바뀌는 경우에는 음수 사이클이 존재한다고 볼 수 있다.
public class Main {
    static long INF;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n, m, a, b, c;
        String[] str = br.readLine().split(" ");
        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);

        INF = Long.MAX_VALUE; // 10000 * 6000 * 500은 정수의 범위를 벗어남

        long[] vertex = new long[n+1];
        List<int[]> edge = new ArrayList<>();

        Arrays.fill(vertex, INF);
        for(int i =0 ;i<m; i++){
            str = br.readLine().split(" ");
            edge.add(new int[]{
                    Integer.parseInt(str[0]),
                    Integer.parseInt(str[1]),
                    Integer.parseInt(str[2])
            });
        }

        vertex[1] = 0;

        // O(nm)
        // 이때, n번째 즉, 마지막에도 간선의 최단거리가 변경될 때에는 사이클이 존재하는 것이다.
        for(int i =1; i<n+1; i++){
            for(int j = 0; j<m; j++){
                a = edge.get(j)[0];
                b = edge.get(j)[1];
                c = edge.get(j)[2];

                if(vertex[a] == INF) continue;

                if(vertex[a]+c < vertex[b]){
                    vertex[b] = vertex[a]+c;
                    if(i == n){ // 음수 사이클이 발생함
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 2; i<n+1; i++){
            sb.append(vertex[i] == INF? -1 : vertex[i]).append("\n");
        }
        System.out.println(sb.toString());


    }
}
