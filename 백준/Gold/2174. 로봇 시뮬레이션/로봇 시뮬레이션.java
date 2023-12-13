import java.io.*;
import java.util.*;

// 시간복잡도 : O(N+M*repeatNumber) = O(100 + 100*100) ~ O(100,000)
public class Main {
    static int A,B,N,M;
    static Map<Integer, int[]> robots;

    static int[][] robotLocation;

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        robots = new HashMap<>();

        String[] str = br.readLine().split(" ");
        A = Integer.parseInt(str[0]);
        B = Integer.parseInt(str[1]);
        robotLocation = new int[B+1][A+1];

        str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);


        int x, y;
        for(int i = 1; i < N+1; i++){
            str = br.readLine().split(" ");
            int dir = 0;
            switch(str[2].charAt(0)){
                case 'N':
                    dir = 0;
                    break;
                case 'E':
                    dir = 1;
                    break;
                case 'S':
                    dir = 2;
                    break;
                case 'W':
                    dir = 3;
                    break;
            }
            x = Integer.parseInt(str[1]);
            y = Integer.parseInt(str[0]);
            robots.put(i, new int[]{x, y, dir});
            robotLocation[x][y] = i;
        }

        int robotNumber, repeatNumber;
        char order;
        for(int i = 0; i<M; i++){
            str = br.readLine().split(" ");
            robotNumber = Integer.parseInt(str[0]);
            order = str[1].charAt(0);
            repeatNumber = Integer.parseInt(str[2]);

            int[] current = robots.get(robotNumber);
            if(order == 'L'){
                int dir = current[2];
                dir = (dir+3*repeatNumber)%4;
                robots.get(robotNumber)[2] = dir;
            }else if(order == 'R'){
                int dir = current[2];
                dir = (dir+repeatNumber)%4;
                robots.get(robotNumber)[2] = dir;
            }else{ // F
                x = current[0];
                y = current[1];
                int dir = current[2];
                for(int j = 0; j<repeatNumber; j++){
                    x = x + dx[dir];
                    y = y + dy[dir];

                    if(x < 1 || y < 1 || x > B || y > A) {
                        System.out.printf("Robot %d crashes into the wall", robotNumber);
                        return;
                    }

                    if(robotLocation[x][y] != 0){
                        System.out.printf("Robot %d crashes into robot %d", robotNumber, robotLocation[x][y]);
                        return;
                    }
                }
                robotLocation[current[0]][current[1]] = 0;
                robotLocation[x][y] = robotNumber;

                robots.get(robotNumber)[0] = x;
                robots.get(robotNumber)[1] = y;
            }
        }

        System.out.println("OK");


    }
}