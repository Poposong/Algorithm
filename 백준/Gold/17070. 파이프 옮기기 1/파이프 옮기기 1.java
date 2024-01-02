import java.io.*;
import java.util.*;

/**
 * BFS (dir, r, c) 현재 어떤 방향에 좌표인지 저장한ㄷ.
 * dir의 값 : 0=>가로, 1=>세로, 2=>대각선
 * */
public class Main {
    static int[][][] visited;
    static int[] dx = {0, 1, 1};
    static int[] dy = {1, 0, 1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        visited = new int[3][N][N]; // 0:가로, 1:세로, 2:대각선

        String[] str;
        for(int i = 0; i<N; i++){
            str = br.readLine().split(" ");
            for(int j = 0; j<N; j++){
                map[i][j] = Integer.parseInt(str[j]);
            }
        }

        visited[0][0][1] = 1;

        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                if(visited[0][i][j] != 0){ // 가로가 가능한 경우
                    if(j+1 < N && map[i][j+1] ==0){ // 가로→가로
                        visited[0][i][j+1] += visited[0][i][j];
                        if(i+1 < N && map[i+1][j] == 0 && map[i+1][j+1] == 0){ // 가로→대각선
                            visited[2][i+1][j+1] += visited[0][i][j];
                        }
                    }
                }

                if(visited[1][i][j] != 0){ // 세로가 가능한 경우
                    if(i+1 < N && map[i+1][j] == 0){ // 세로→세로
                        visited[1][i+1][j] += visited[1][i][j];
                        if(j+1 < N && map[i][j+1] == 0 && map[i+1][j+1] == 0){ // 세로→대각선
                            visited[2][i+1][j+1] += visited[1][i][j];
                        }
                    }
                }

                if(visited[2][i][j] != 0){  // 대각선이 가능한 경우
                    if(j+1 < N && map[i][j+1] == 0){ // 대각선→가로
                        visited[0][i][j+1] += visited[2][i][j];
                    }

                    if(i+1 < N && map[i+1][j] == 0){ // 대각선→세로
                        visited[1][i+1][j] += visited[2][i][j];
                    }

                    if(i+1 < N && j+1 < N && map[i][j+1] == 0 && map[i+1][j] == 0 && map[i+1][j+1] == 0){ // 대각선→대각선
                        visited[2][i+1][j+1] += visited[2][i][j];
                    }
                }
            }
        }

        System.out.println(visited[0][N-1][N-1]+visited[1][N-1][N-1]+visited[2][N-1][N-1]);



    }

}