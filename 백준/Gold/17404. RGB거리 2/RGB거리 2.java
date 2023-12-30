import java.io.*;
import java.util.*;
public class Main {
    static int result = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] rgb = new int[N][3];
        String[] str;
        for(int i = 0; i<N; i++){
            str = br.readLine().split(" ");
            for(int j = 0; j<3; j++){
                rgb[i][j] = Integer.parseInt(str[j]);
            }
        }
        for(int i = 0; i<3; i++){
            minPath(N, i, rgb);
        }

        System.out.println(result);


    }

    static void minPath(int N, int start, int[][] rgb){
        int[][] path = new int[N][3];
        for(int i = 0; i<N; i++){
            Arrays.fill(path[i], Integer.MAX_VALUE);
        }
        path[0][start] = rgb[0][start];
        for(int i = 0; i<N-1; i++){
            for(int j = 0; j<3; j++){
                if(path[i][j] != Integer.MAX_VALUE){
                    for(int k = 0; k<3; k++){
                        if(k!=j){
                            path[i+1][k] = Math.min(path[i+1][k], path[i][j] + rgb[i+1][k]);
                        }
                    }
                }
            }
        }

        for(int i = 0; i<3; i++){
            if(i!=start){
                result = Math.min(result, path[N-1][i]);
            }
        }
    }
}