import java.io.*;
import java.util.*;

// 홀-홀, 짝-짝으로 차이가 2의 배수임
public class Main {
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
            if(zeroCnt <= K && zeroCnt%2 == K%2){
                for(int j = 0; j<N; j++){
                    if(isSame(graph[i], graph[j], zeroCnt)){ // 두 개의 모양이 같으면?
                        sameCnt++;
                    }
                }
            }
            answer = Math.max(answer, sameCnt);

        }


        System.out.println(answer);
    }

    static boolean isSame(int[] arr1, int[] arr2, int i){
        for(int j = 0; j<M; j++){
            if(arr1[j] != arr2[j]){
                return false;
            }
        }
        return true;
    }


}