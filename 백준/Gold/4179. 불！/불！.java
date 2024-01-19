import java.io.*;
import java.util.*;

/**
 * 1. 불이 상하좌우로 이동한다.
 * 2. 지훈이는 자신의 위치에서 갈 수 있는 곳으로 모두 간다.(BFS사용하고 큐에 있는 데이터를 모두 꺼내서 경우의 수를 다 체크해야함)
 * 주의!!
 * - 지훈이는 자신이 방문했던 곳을 또 방문하지 않음(그럼 최단 거리가 아님)
 * 또한, 
 * 4 4
 * ###F
 * #J#.
 * #.#F
 * ###.
 * 이러한 에러를 막을 수 없음.
 * 
 * - 불은 자신을 기준으로 상하좌우로 퍼진 후 큐에서 자신을 제거해야함. → 다음에는 불이 더 퍼질 수 없음. 
 * */
public class BJ_4179_불 {
    static int r, c;
    static char[][] graph;
    static Queue<int[]> fireQ;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        r = Integer.parseInt(str[0]);
        c = Integer.parseInt(str[1]);

        int[] jihun = new int[2];// 지훈이의 초기 위치
        fireQ = new ArrayDeque<>(); // 불이 난 곳의 위치

        graph = new char[r][c];
        for(int i = 0; i<r; i++){
            String s = br.readLine();
            for(int j = 0; j<c; j++){
                graph[i][j] = s.charAt(j);
                if(graph[i][j] == 'J'){ // 지훈이의 초기 위치
                    jihun = new int[]{i, j};
                    graph[i][j] = '.';
                }
                if(graph[i][j] == 'F'){ // 불이 난 곳의 위치
                    fireQ.offer(new int[]{i, j});
                }
            }
        }
        int answer = bfs(jihun);
        System.out.println(answer == -1 ? "IMPOSSIBLE" : answer);


    }

    static int bfs(int[] start){
        boolean[][] visited = new boolean[r][c];
        for(int i = 0; i<r; i++){
            Arrays.fill(visited[i], false);
        }
        visited[start[0]][start[1]] = true;

        Queue<int[]> juhunQ = new ArrayDeque<>();
        juhunQ.offer(start);

        int size, timer = 0;
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        while(!juhunQ.isEmpty()){
            timer++;

            // 불의 이동
            size = fireQ.size();
            for(int i = 0; i<size; i++){
                int[] fire = fireQ.poll();
                for(int j = 0; j<4; j++){
                    int x = fire[0] + dx[j];
                    int y = fire[1] + dy[j];

                    if(rangeCheck(x,y) && graph[x][y] == '.'){ // 불은 빈 공간만 이동할 수 있다.
                        graph[x][y] = 'F';
                        fireQ.add(new int[]{x, y});
                    }
                }
            }

            // 지훈이가 정확히 timer 만큼의 거리로 이동하는지 확인한다.
            size = juhunQ.size();
            for(int i = 0; i<size; i++){
                int[] jihun = juhunQ.poll();
                for(int j = 0; j<4; j++){
                    int x = jihun[0] + dx[j];
                    int y = jihun[1] + dy[j];

                    if(!rangeCheck(x, y)) return timer;

                    if(graph[x][y] == '.' && !visited[x][y]){ // 지훈이는 빈 공간만 이동할 수 있다.
                        visited[x][y] = true;
                        juhunQ.offer(new int[]{x, y});
                    }

                }
            }
        }

        return -1;
    }

    static boolean rangeCheck(int x, int y){
        if(0<=x && x<r && 0<=y && y<c) return true;
        return false;
    }
}
