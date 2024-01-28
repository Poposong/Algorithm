import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.util.*;
public class Main {
    static class Dot{
        int x;
        int y;

        public Dot(int x, int y){
            this.x=x;
            this.y=y;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int k = Integer.parseInt(str[1]);

        List<Dot>[] virus = new ArrayList[k+1]; // 1~k까지의 바이러스가 존재한다.
        for(int i = 0; i<k+1; i++){
            virus[i] = new ArrayList<Dot>();
        }

        TreeSet<Integer> type = new TreeSet<>(); // 존재하는 바이러스를 오름차순으로 정렬함.
        int[][] graph = new int[n][n];
        for(int i =0; i<n; i++){
            str = br.readLine().split(" ");
            for(int j = 0; j<n; j++){
                graph[i][j] = Integer.parseInt(str[j]);
                if(graph[i][j]!=0){
                    type.add(graph[i][j]);
                    virus[graph[i][j]].add(new Dot(i, j));
                }
            }
        }

        int s, x, y;
        str = br.readLine().split(" ");
        s = Integer.parseInt(str[0]);
        x = Integer.parseInt(str[1])-1;
        y = Integer.parseInt(str[2])-1;

        if(graph[x][y] !=0){
            System.out.println(graph[x][y]);
            return;
        }

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while(s-- != 0){
            Iterator<Integer> iter = type.iterator();
            while(iter.hasNext()){
                int virusName = iter.next();
                // 번지기
                int len = virus[virusName].size();
                for(int i = 0; i<len; i++){
                    Dot dot = virus[virusName].remove(0);

                    for(int j = 0; j<4; j++){
                        int nx = dot.x+dx[j];
                        int ny = dot.y+dy[j];

                        if(0<= nx && nx<n && 0<=ny && ny<n && graph[nx][ny] == 0){
                            graph[nx][ny] = virusName;
                            if(nx == x && ny == y){
                                System.out.println(virusName);
                                return;
                            }
                            virus[virusName].add(new Dot(nx, ny));
                        }
                    }
                }
            }
        }

        System.out.println(graph[x][y]);



    }
}