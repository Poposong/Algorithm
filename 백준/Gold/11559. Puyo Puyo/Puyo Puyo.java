import java.io.*;
import java.util.*;

// 자신을 기점으로 상하좌우 bfs 탐색해서 4개 이상이면 true로 해둔다.
// 다 탐색하고 true인 것들만 .으로 변경해준다.

public class Main {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[][] chart = new char[12][6];
        for(int i = 0; i<12; i++){
            String[] str = br.readLine().split("");
            for(int j = 0; j<6; j++){
                chart[i][j] = str[j].charAt(0);
            }
        }

        int ans = 0;
        while(true){
            // 방문하려는 배열
            boolean[][] visited = new boolean[12][6];
            for(int i = 0; i<12; i++){
                Arrays.fill(visited[i], false);
            }

            boolean isPossible = false;
            // puyo를 찾는다.
            for(int i = 0; i<6; i++){
                for(int j = 11; j>=0; j--){
                    if(!visited[j][i] && chart[j][i] != '.'){
                        if(bfs(j, i, visited, chart)){
                            isPossible = true;
                        }
                    }
                    if(chart[j][i] == '.') break;
                }
            }

            if(!isPossible){
                break;
            }

            // puyo를 다 찾았으므로 실제 chart에서 제거해준다.
            for(int i = 0; i<6; i++){
                ArrayList<Character> notPuyo = new ArrayList<>();
                for(int j = 11; j>=0; j--){ 
                    if(!visited[j][i]){ // puyo가 아닌 데이터만 temp에 저장한다.
                        notPuyo.add(chart[j][i]);
                    }
                }
                char c;
                for(int j = 11; j>=0; j--){ // 아래에서 부터 temp에 있으면 넣고 없으며 .으로 다시 채워준다.
                    if(!notPuyo.isEmpty()){
                        c = notPuyo.remove(0);
                        chart[j][i] = c;
                    }else{
                        chart[j][i] = '.';
                    }
                }
            }
            
            ans++;

        }

        System.out.println(ans);
    }

    static boolean bfs(int x, int y, boolean[][] visited, char[][] chart){
        ArrayList<int[]> temp = new ArrayList<>();
        temp.add(new int[]{x, y});

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});

        visited[x][y] = true;
        while(!queue.isEmpty()){
            int[] data = queue.poll();
            for(int i = 0; i<4; i++){
                int nx = data[0] + dx[i];
                int ny = data[1] + dy[i];

                if(0<=nx && nx<12 && 0<=ny && ny<6 && !visited[nx][ny] && chart[nx][ny] == chart[x][y]){
                    visited[nx][ny] = true;
                    temp.add(new int[]{nx, ny});
                    queue.offer(new int[]{nx, ny});
                }
            }
        }

        if(temp.size()>=4){
            return true;
        }else{
            while(!temp.isEmpty()){ // 방문했던 좌표를 다시 원래대로 복구시키기
                int[] data = temp.remove(0);
                visited[data[0]][data[1]] = false;
            }
            return false;
        }
    }
}
