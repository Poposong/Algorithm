import java.io.*;
import java.util.*;
/**
시작 -> 레버 (DFS)
레버 -> 끝(DFS)
*/
class Solution {
    static int N, M;
    static int[] moveX = {-1, 1, 0, 0};
    static int[] moveY = {0, 0, -1, 1};
    static boolean[][] visited;
    
    public int solution(String[] maps) {
        N = maps.length;
        M = maps[0].length();
        
        int[][] seat = new int[3][2]; // 시작, 레버, 도착
        char[][] graph = new char[N][M];
        visited = new boolean[N][M];
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
            Arrays.fill(visited[i], false);
        }
        
        // x, y, sum
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                return a[2]-b[2]; // 오름차순
            }
        });

        visited[sx][sy] = true;
        pq.offer(new int[]{sx, sy, 0});
        
        int x, y, sum;
        while(!pq.isEmpty()){
            x = pq.peek()[0];
            y = pq.peek()[1];
            sum = pq.peek()[2];
            pq.poll();
            
            if(x == dx && y == dy){
                return sum;
            }
            
            int nx, ny;
            for(int i = 0; i<4; i++){
                nx = x + moveX[i];
                ny = y + moveY[i];
                if(nx < 0 || ny < 0 || N<=nx || M<=ny) continue;
                
                if(visited[nx][ny] || graph[nx][ny] == 'X') continue;
                
                visited[nx][ny] = true;
                pq.offer(new int[]{nx, ny, sum+1});
            }
        }
        return -1;
    }
}