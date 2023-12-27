import java.io.*;
import java.util.*;
/**
다익스트라
*/
class Solution {
    static int N, M;
    static int[] moveX = {-1, 1, 0, 0};
    static int[] moveY = {0, 0, -1, 1};
    static int[][] dp;
    
    public int solution(String[] maps) {
        N = maps.length;
        M = maps[0].length();
        
        int[][] seat = new int[3][2]; // 시작, 레버, 도착
        char[][] graph = new char[N][M];
        dp = new int[N][M];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                graph[i][j] = maps[i].charAt(j);
                switch(graph[i][j]){
                    case 'S':
                        seat[0][0] = i;
                        seat[0][1] = j;
                        break;
                    case 'L':
                        seat[1][0] = i;
                        seat[1][1] = j;
                        break;
                    case 'E':
                        seat[2][0] = i;
                        seat[2][1] = j;
                        break;
                }
            }
        }
        int res1 = bfs(seat[0][0], seat[0][1], seat[1][0], seat[1][1], graph);
        int res2 = bfs(seat[1][0], seat[1][1], seat[2][0], seat[2][1], graph);
        if(res1 == -1 || res2==-1){
            return -1;
        }
        
        return res1+res2;
    }
    
    static int bfs(int sx, int sy, int dx, int dy, char[][] graph){
        for(int i = 0; i<N; i++){
            Arrays.fill(dp[i], 10001);
        }
        
        // x, y, sum
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                return a[2]-b[2]; // 오름차순
            }
        });

        dp[sx][sy] = 0;
        pq.offer(new int[]{sx, sy, 0});
        
        int x, y, sum;
        while(!pq.isEmpty()){
            x = pq.peek()[0];
            y = pq.peek()[1];
            sum = pq.peek()[2];
            pq.poll();
            
            if(x == dx && y == dy){
                return dp[x][y];
            }
            
            if(dp[x][y] < sum) continue;
            
            int nx, ny;
            for(int i = 0; i<4; i++){
                nx = x + moveX[i];
                ny = y + moveY[i];
                if(nx < 0 || ny < 0 || N<=nx || M<=ny) continue;
                
                if(graph[nx][ny] == 'X') continue;
                
                if(dp[x][y]+1 < dp[nx][ny]){
                    dp[nx][ny] = dp[x][y]+1;
                    pq.offer(new int[]{nx, ny, dp[nx][ny]});
                }
            }
        }
        return -1;
    }
}
