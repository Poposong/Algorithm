package Algorithm;
import java.lang.reflect.Array;
import java.util.*;
import java.io.*;



public class Main {

    static int N, M, K;

    static int dx[] = {-1, 0, 1, 0, -1, 1, 1, -1}; // 직선, 대각선
    static int dy[] = {0, -1, 0, 1, -1, -1, 1, 1};
    static char map[][];
    static char[] current;
    static int maxLen;
    static String[] order;
    static HashMap<String, Integer> resultCount;

    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

       String[] str = br.readLine().split(" ");
       N = Integer.parseInt(str[0]);
       M = Integer.parseInt(str[1]);
       K = Integer.parseInt(str[2]);

       map = new char[N][M];

       String s;
       for(int i = 0; i<N; i++){
            s = br.readLine();
            for(int j = 0; j<M; j++){
                map[i][j] = s.charAt(j);
            }
       }

       resultCount = new HashMap<>();
       order = new String[K];

       maxLen = -1;
       for(int i =0; i<K; i++){
           s = br.readLine();
           order[i] = s;
           resultCount.put(s, 0);
           maxLen = Math.max(maxLen, s.length());
       }

        current = new char[5];

        for(int i = 0; i<N; i++){
           for(int j = 0; j<M; j++){
               current[0] = map[i][j];
               dfs(1, i, j);
           }
        }

        StringBuilder sb = new StringBuilder();
        for(String key : order){
            sb.append(resultCount.get(key)).append("\n");
        }
        System.out.println(sb.toString());
    }

    static void dfs(int len, int x, int y){
        if(len >= maxLen){
            return;
        }

        int nx, ny;
        for(int i = 0; i<8; i++){
            nx = x + dx[i];
            ny = y + dy[i];

            if(rangeCheck(nx,ny)){
                checkWord(len, nx, ny);
            }else{
                if(nx < 0){
                    nx = N-1;
                }
                if(N <= nx){
                    nx = 0;
                }

                if(ny < 0){
                    ny = M-1;
                }
                if(M <= ny){
                    ny = 0;
                }
                checkWord(len, nx, ny);
            }
            dfs(len+1, nx, ny);
        }
    }

    static void checkWord(int len, int nx, int ny){
        current[len] = map[nx][ny];
        String str = transferStr(len+1);
        if(resultCount.containsKey(str)){
            resultCount.put(str, resultCount.getOrDefault(str, 0) + 1);
        }
    }

    static String transferStr(int len){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<len; i++){
            sb.append(current[i]);
        }
        return sb.toString();
    }

    static boolean rangeCheck(int x, int y){
        if(0 <= x && x < N && 0 <= y && y < M)
            return true;
        return false;
    }

}
