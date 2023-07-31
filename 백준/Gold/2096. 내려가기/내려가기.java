import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node{
    int x;
    int y;

    int sum;

    public Node(int x, int y, int sum){
        this.x = x;
        this.y = y;
        this.sum = sum;
    }
}
public class Main {

    public static int[][] map, minScore, maxScore;

    public static int N;

    public static PriorityQueue<Node> queue;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        N = Integer.parseInt(br.readLine());

        map = new int[N][3];

        minScore = new int[N][3];
        maxScore = new int[N][3];

        String[] str;
        for(int i = 0; i<N; i++){
            str = br.readLine().split(" ");

            for(int j = 0; j<3; j++){
                map[i][j] = Integer.parseInt(str[j]);
            }

            Arrays.fill(minScore[i], Integer.MAX_VALUE);
            Arrays.fill(maxScore[i], Integer.MIN_VALUE);
        }

        for(int i = 0; i<3; i++){
            minScore[0][i] = map[0][i];
            maxScore[0][i] = map[0][i];
        }

        int  minNum;
        for(int i = 0; i<N-1; i++){
            minNum = Math.min(minScore[i][0], minScore[i][1]);
            minScore[i+1][0] = minNum + map[i+1][0];

            minScore[i+1][1] = Math.min(minScore[i][0], Math.min(minScore[i][1], minScore[i][2])) + map[i+1][1];

            minNum = Math.min(minScore[i][1], minScore[i][2]);
            minScore[i+1][2] = minNum + map[i+1][2];
        }

        int maxNum;
        for(int i = 0; i<N-1; i++){
            maxNum = Math.max(maxScore[i][0], maxScore[i][1]);
            maxScore[i+1][0] = maxNum + map[i+1][0];

            maxScore[i+1][1] = Math.max(maxScore[i][0], Math.max(maxScore[i][1], maxScore[i][2])) + map[i+1][1];

            maxNum = Math.max(maxScore[i][1], maxScore[i][2]);
            maxScore[i+1][2] = maxNum + map[i+1][2];
        }

        StringBuilder sb = new StringBuilder();

        sb.append(Math.max(maxScore[N-1][0], Math.max(maxScore[N-1][1], maxScore[N-1][2])));
        sb.append(" ").append(Math.min(minScore[N-1][0], Math.min(minScore[N-1][1], minScore[N-1][2])));

        System.out.println(sb.toString());

    }



}