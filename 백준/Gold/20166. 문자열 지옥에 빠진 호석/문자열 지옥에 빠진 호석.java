/*
수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤ 100,000)에 있다
 */
import java.lang.reflect.Array;
import java.util.*;
import java.io.*;



public class Main {

    static int N, M, K;

    static int dx[] = {-1, 0, 1, 0, -1, 1, 1, -1}; // 직선, 대각선
    static int dy[] = {0, -1, 0, 1, -1, -1, 1, 1};

    static char map[][];
 //   static Map<Integer, ArrayList<String>> findWords;

    static Map<String, Integer> findCount;

    static char[] current;
    static int maxLen;

    static String[] order;
    static HashMap<String, Integer> resultList;

    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

       String[] str = br.readLine().split(" ");
       N = Integer.parseInt(str[0]);
       M = Integer.parseInt(str[1]);
       K = Integer.parseInt(str[2]);

       map = new char[N][M];
//       findWords = new HashMap<>();
//       for(int i = 1; i<=5; i++){
//           findWords.put(i, new ArrayList<String>());
//       }

        findCount = new HashMap<>();


       String s;
       for(int i = 0; i<N; i++){
            s = br.readLine();
            for(int j = 0; j<M; j++){
                map[i][j] = s.charAt(j);
            }
       }

       resultList = new HashMap<>();
       order = new String[K];

       maxLen = -1;
       for(int i =0; i<K; i++){
           s = br.readLine();

           resultList.put(s, 0);
           order[i] = s;
           maxLen = Math.max(maxLen, s.length());

           if(!findCount.containsKey(s)){
               findCount.put(s, 1);
           }else{
              // findCount.getOrDefault(s, findCount.get(s) + 1);
               int t = findCount.get(s);
               findCount.put(s, t + 1);
           }
       }



        current = new char[5];

        //System.out.println("maxLen : "+maxLen);

        for(int i = 0; i<N; i++){
           for(int j = 0; j<M; j++){

               current[0] = map[i][j];
               checkWord(0, i, j);

               dfs(1, i, j);
             //  System.out.println("-----------");
           }
        }

        StringBuilder sb = new StringBuilder();
        for(String key : order){
            sb.append(resultList.get(key)).append("\n");
        }
        System.out.println(sb.toString());

    }

    static void dfs(int len, int x, int y){

        if(len >= maxLen){
            return;
        }

        String str;
        int nx, ny;
        for(int i = 0; i<8; i++){
            nx = x + dx[i];
            ny = y + dy[i];

          // System.out.println(nx+","+ny);
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

          //  System.out.println("len : "+ len);
            dfs(len+1, nx, ny);
        }
    }

    static void checkWord(int len, int nx, int ny){
        current[len] = map[nx][ny];
        String str = transferStr(len+1);
        int cnt = 0;
        if(resultList.containsKey(str)){
            cnt = findCount.get(str);
            int t = resultList.get(str);
            resultList.put(str, t + 1);
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