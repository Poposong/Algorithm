import java.io.*;
import java.util.*;

class Nodes{
    int number;
    long weight;

    public Nodes(int number, long weight){
        this.number=number;
        this.weight=weight;
    }
}
public class Main {
    static int v, e;
    static long result;
    static long INF;
    static long[][] minPath;
    static Map<Integer, ArrayList<int[]>> edge;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        v = Integer.parseInt(str[0]);
        e = Integer.parseInt(str[1]);
        INF = Long.MAX_VALUE;
        result = Long.MAX_VALUE;
        minPath = new long[v+1][v+1];
        for(int i = 0; i<v+1; i++) {
            Arrays.fill(minPath[i], INF);
        }

        for(int i =0 ; i<e; i++){
            str = br.readLine().split(" ");
            minPath[Integer.parseInt(str[0])][Integer.parseInt(str[1])] = Integer.parseInt(str[2]);
        }

        for(int k = 1; k<v+1; k++){
            for(int i =1; i<v+1; i++){
                for(int j = 1; j<v+1; j++){
                    if(i==j){
                        continue;
                    }

                    if(minPath[i][k] == INF || minPath[k][j] == INF) continue;

                    if(minPath[i][k] + minPath[k][j] < minPath[i][j]){
                        minPath[i][j] = minPath[i][k] + minPath[k][j];
                    }
                }
            }
        }

        for(int i = 1; i<v+1; i++){
            for(int j = 1; j<v+1; j++){
                if(i==j){
                    continue;
                }
                if(minPath[i][j] == INF || minPath[j][i] == INF) {
                    continue;
                }

                result = Math.min(result, minPath[i][j]+minPath[j][i]);
            }
        }

        System.out.println(result == INF ? -1 : result);


    }


}