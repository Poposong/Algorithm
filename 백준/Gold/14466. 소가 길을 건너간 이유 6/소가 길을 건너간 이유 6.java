import java.io.*;
import java.util.*;

// NxN : 목초지의 크기
// K : 소의 수
// R : 길의 수
public class Main {
    static int N, K, R;
    static boolean[][][] roadGraph;

    static boolean[][][][] roadVisible;

    static int[][] cow;

    static boolean[][] visited;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        K = Integer.parseInt(str[1]);
        R = Integer.parseInt(str[2]);

        roadGraph = new boolean[N+1][N+1][R+1];

        visited = new boolean[N+1][N+1];

        roadVisible = new boolean[N+1][N+1][N+1][N+1];


        // R줄에는 길이 주어진다.
        int r1, c1, r2, c2;
        for(int i = 0; i<R; i++){
            str = br.readLine().split(" ");
            r1 = Integer.parseInt(str[0]);
            c1 = Integer.parseInt(str[1]);
            r2 = Integer.parseInt(str[2]);
            c2 = Integer.parseInt(str[3]);

            roadGraph[r1][c1][i+1] = true;
            roadGraph[r2][c2][i+1] = true;

            roadVisible[r1][c1][r2][c2] = true;
            roadVisible[r2][c2][r1][c1] = true;

        }

        cow = new int[K][2];
        // K줄에는 소의 위치가 주어진다.
        for(int i = 0; i<K; i++){
            str = br.readLine().split(" ");
            cow[i][0] = Integer.parseInt(str[0]);
            cow[i][1] = Integer.parseInt(str[1]);
        }

        int result = 0;
        for(int i = 0; i<K; i++){
            bfs(cow[i][0], cow[i][1]);
            for(int j = i+1; j<K; j++){
                if(!visited[cow[j][0]][cow[j][1]]){ // 다리를 건너야만 갈 수가 있는 경우
                    result++;
                }
            }
        }

        System.out.println(result);
    }

    static void bfs(int r1, int c1){
        Queue<int[]> queue = new ArrayDeque<>();
        for(int i = 0; i<N+1; i++){
            Arrays.fill(visited[i], false);
        }

        queue.add(new int[]{r1, c1});
        visited[r1][c1] = true;

        int x, y;
        int nx, ny;
        int[] value = new int[2];
        while(!queue.isEmpty()){
            value = queue.poll();
            x = value[0];
            y = value[1];

            for(int i = 0; i<4; i++){
                nx = x + dx[i];
                ny = y + dy[i];

                if(nx<1 || ny < 1 || nx > N || ny > N) continue; // 범위를 벗어난 경우

                if(visited[nx][ny]) continue; // 이미 방문을 한 경우

                if(roadVisible[x][y][nx][ny]) continue; // 다리가 연결된 경우

                visited[nx][ny] = true;
                queue.offer(new int[]{nx, ny});
            }
        }
    }
}