import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
    public static int N,M; // 사다리의 수, 뱀의 수

    public static int[] count, move; // 그래프

    public static boolean[] visited;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] str = br.readLine().split(" ");

        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);

        count = new int[101];
        move = new int[101];

        visited = new boolean[101];

        Arrays.fill(count, 0);
        Arrays.fill(move, 0);

        Arrays.fill(visited, false);

        for(int i = 0; i < N+M; i++){
            str = br.readLine().split(" ");
            move[Integer.parseInt(str[0])] = Integer.parseInt(str[1]);
        }

        // bfs

        bfs();
    }

    public static void bfs(){
            Queue<Integer> pq = new ArrayDeque<>();

            pq.offer(1);
            visited[1] = true;
            count[1] = 0;

            int current;
            while(!pq.isEmpty()){
                current = pq.poll();

                if(current == 100){
                    System.out.println(count[100]);
                    return;
                }

                // 주사위 1~6까지 던지는 경우의 수
                for(int i = 1; i<7; i++){
                    int next = current + i;

                    if(next > 100) continue;
                    if(visited[next]) continue;


                    visited[next] = true;

                    if(move[next] != 0){ // 뱀이나 사다리가 있는 경우
                        if(!visited[move[next]]){
                            visited[move[next]] = true;
                            count[move[next]] = count[current] + 1;
                            pq.offer(move[next]);
                        }
                    }else{ // 뱀이나 사다리가 없는 경우
                        count[next] = count[current] + 1;
                        pq.offer(next);
                    }
                }

            }
        }


}