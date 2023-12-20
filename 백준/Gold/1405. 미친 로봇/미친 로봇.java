import java.io.*;
import java.util.*;

public class Main {
    static int N;
    // 동, 서, 남, 북
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static double[] dir;
    static boolean[][] visited;

    static double result;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // N, 동, 서, 남, 북
        dir = new double[4];

        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        for(int i = 1; i<5; i++){
            dir[i-1] = Integer.parseInt(str[i])/100.0;
        }

        visited = new boolean[30][30];

        visited[15][15] = true;
        dfs(0, 15, 15, 1);

        System.out.println(result);


    }

    static void dfs(int cnt, int x, int y, double value){
        if(cnt == N){
            result += value;
            return;
        }
        int nx, ny;
        for(int i = 0; i<4; i++){
            if(dir[i] == 0) continue;

            nx = x + dx[i];
            ny = y + dy[i];

            if(!visited[nx][ny]){
                visited[nx][ny] = true;
                dfs(cnt+1, nx, ny, value*dir[i]);
                visited[nx][ny] = false;
            }
        }
    }
}