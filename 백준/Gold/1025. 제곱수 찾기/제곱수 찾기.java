import java.io.*;
import java.util.*;

/**
 * 등차는 행 또는 열 모두 만족하기 때문에 기준(i,j)로 부터 -N~N까지 등차가 만들어질 수 있다.
 * 하지만, 행이나 열의 범위를 넘어가는 경우에는 skip 한다.
 * Math.sqrt()함수를 이용해서 제곱근을 구하고 제곱근을 제곱했을 때 내가 찾은 수와 동일한지 체크한다.
 * */
public class Main {
    static int N, M;
    static char[][] graph;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);

        graph = new char[N][M];
        for(int i = 0; i<N; i++){
            graph[i] = br.readLine().toCharArray();
        }

        int result = -1;

        for(int i = 0; i<N; i++){ // 행
            for(int j = 0; j<M; j++){ // 열
                for(int di = -N; di<N+1; di++){ // 행의 등차(-N부터 N까지)
                    for(int dj = -M; dj<M+1; dj++){ // 열의 등차(-M부터 M까지)
                        if(di == 0 && dj == 0) continue; // 둘 다 움직이지 않을 때

                        int curX = i;
                        int curY = j;
                        int now = 0;
                        while(curX >= 0 && curX < N && curY >= 0 && curY < M){
                            now *= 10;
                            now += Integer.parseInt(String.valueOf(graph[curX][curY])); // 뒤로 수를 붙여나가는 형태

                            int sqrtNum = (int)Math.sqrt((double)now);
                            if(sqrtNum * sqrtNum == now){
                                result = Math.max(result, now);
                            }

                            curX += di;
                            curY += dj;
                        }

                    }
                }
            }
        }

        System.out.println(result);



    }

}