import java.io.*;
import java.util.*;

// 정점의 수만큼 edge를 다 계산한다 (nm)
// 간선
public class Main {
    static long INF;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n, m, a, b, c;
        String[] str = br.readLine().split(" ");
        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);

        INF = Long.MAX_VALUE;

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

        for(int i =1; i<n+1; i++){
            for(int j = 0; j<m; j++){
                a = edge.get(j)[0];
                b = edge.get(j)[1];
                c = edge.get(j)[2];

                if(vertex[a] == INF) continue;

                if(vertex[a]+c < vertex[b]){
                    vertex[b] = vertex[a]+c;
                    if(i == n){
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