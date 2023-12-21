import java.io.*;
import java.util.*;
public class Main {
    static int N, M, K;
    static int[][] graph;
    static int[][] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        K = Integer.parseInt(str[2]);


        graph = new int[N+1][N+1];
        dp = new int[N+1][M+1]; // 행은 1~N까지(정점), 열은 1~M까지(해당 정점까지 몇 개의 간선을 사용했는지)
        for(int i = 0; i<N+1; i++){
            Arrays.fill(graph[i], -1);
            Arrays.fill(dp[i], -1);
        }

        int a, b;
        for(int i = 0; i<K; i++){
            str = br.readLine().split(" ");
            a = Integer.parseInt(str[0]);
            b = Integer.parseInt(str[1]);
            if(a<b){
                graph[a][b] = Math.max(graph[a][b], Integer.parseInt(str[2]));
            }
        }
        dp[1][1] = 0;
        for(int i = 1; i<N; i++){
            for(int j = 1; j<M; j++){
                if(dp[i][j] == -1) continue;

                for(int k = i+1; k<N+1; k++){
                    if(graph[i][k] == -1) continue;

                    dp[k][j+1] = Math.max(dp[k][j+1], dp[i][j] + graph[i][k]);
                }
            }
        }
        int result = -1;
        for(int i = 1; i<M+1; i++){
            result = Math.max(result, dp[N][i]);
        }
        System.out.println(result);




    }

}