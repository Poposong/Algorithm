import java.io.*;
import java.util.*;

public class Main {

    static int[][] graph;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        graph = new int[N+1][N+1];

        int n1, n2;
        String[] str;
        while(M-- != 0){
            str = br.readLine().split(" ");
            n1 = Integer.parseInt(str[0]);
            n2 = Integer.parseInt(str[1]);

            graph[n1][n2] = 1; // n1>n2
            graph[n2][n1] = -1; // n2<n1
        }

        for(int i = 1; i<N+1; i++){
            graph[i][i] = 0;
            for(int j = 1; j<N+1; j++){
                for(int k = 1; k<N+1; k++){
                    if(graph[j][i] == 1 && graph[i][k] == 1){
                        graph[j][k] = 1;
                    }
                    if(graph[j][i] == -1 && graph[i][k] == -1){
                        graph[j][k] = -1;
                    }
                }
            }
        }

        int cnt;
        for(int i = 1; i<N+1; i++){
            cnt = -1;
            for(int j = 1; j<N+1; j++){
                if(graph[i][j]==0){
                    cnt++;
                }
            }
            sb.append(cnt).append("\n");
        }

        System.out.println(sb.toString());

    }
}