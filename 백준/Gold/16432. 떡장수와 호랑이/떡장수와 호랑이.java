import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static ArrayList<Integer>[] numberList;
    static int[] result;

    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        numberList = new ArrayList[N+1];

        result = new int[N+1];
        visited = new boolean[N+2][10]; // 떡 : 1~9

        String[] str;
        for(int i = 1; i<N+1; i++){
            str = br.readLine().split(" ");
            numberList[i] = new ArrayList<Integer>();
            for(int j = 1; j<Integer.parseInt(str[0])+1; j++){
                numberList[i].add(Integer.parseInt(str[j]));
            }
        }

        dfs(1, 0);
        System.out.println(-1);

    }

    static void dfs(int idx, int prev){ // 현재 idx와 이전의 숫자
        if(idx == N+1){
            // 결과출력
            StringBuilder sb = new StringBuilder();
            for(int i = 1; i<N+1; i++){
                sb.append(result[i]).append("\n");
            }
            System.out.println(sb.toString());
            System.exit(0);
        }

        for(int i = 1; i<10; i++){
            if(prev != i && !visited[idx+1][i] && numberList[idx].contains((Integer)i)){
                result[idx] = i;
                visited[idx+1][i] = true;
                dfs(idx+1, i);
            }
        }

    }

}