package Baekjoon;
import java.io.*;
import java.util.*;

// 시간복잡도 : O( N * (M+N*M)) => O(N * (N*M)) = O(N * N * M) = O(50*50%50) = 125,000
public class BJ_1034_램프 {
    static int N, M, K, answer;
    static int[][] graph;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        graph = new int[N][M];
        for(int i = 0; i<N; i++){
            String s = br.readLine();
            for(int j = 0; j<M; j++){
                graph[i][j] = Integer.parseInt(String.valueOf(s.charAt(j)));
            }
        }
        K = Integer.parseInt(br.readLine());
        
        // 같은 모양이면 같이 켜지고 꺼진다.
        for(int i = 0; i<N; i++){
            int zeroCnt = 0;
            for(int j = 0; j<M; j++){
                if(graph[i][j] == 0) zeroCnt++;
            }

            int sameCnt = 0;
            if(zeroCnt <= K && zeroCnt%2 == K%2){ // K보다 작거나 같으면서 짝수↔짝수 or 홀수↔홀수이면 가능하다.
                for(int j = 0; j<N; j++){
                    if(isSame(graph[i], graph[j], zeroCnt)){ // 두 개의 모양이 같은지 확인한다.
                        sameCnt++;
                    }
                }
            }
            answer = Math.max(answer, sameCnt);

        }


        System.out.println(answer);
    }


    // O(M)
    static boolean isSame(int[] arr1, int[] arr2, int i){
        for(int j = 0; j<M; j++){
            if(arr1[j] != arr2[j]){
                return false;
            }
        }
        return true;
    }


}
