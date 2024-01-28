import java.io.*;
import java.util.*;
public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);

        int[][] graph = new int[n][m];

        for(int i = 0; i<n; i++){
            str = br.readLine().split("");
            for(int j = 0; j<m; j++){
                graph[i][j] = Integer.parseInt(str[j]);
            }
        }

        int[][] dp = new int[n][m];
        dp[0][0] = graph[0][0];
        for(int i = 1; i<n; i++){
            dp[i][0] = dp[i-1][0] + graph[i][0];
        }
        for(int i = 1; i<m; i++){
            dp[0][i] = dp[0][i-1] + graph[0][i];
        }

        for(int i = 1; i<n; i++){
            for(int j = 1; j<m; j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + graph[i][j];
            }
        }

        int ans = 0;
        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                if(graph[i][j] == 1){
                    int term = 0;
                    int result = 1;
                    while(i+term < n && j+term < m){
                        int sum = dp[i+term][j+term];
                        if(i-1 >= 0){
                            sum -= dp[i-1][j+term];
                        }

                        if(j-1 >= 0){
                            sum -= dp[i+term][j-1];
                        }

                        if(i-1>=0 && j-1>=0){
                            sum += dp[i-1][j-1];
                        }


                        if(sum == (term+1) * (term+1)){
//                            System.out.println(dp[i+term][j+term]);
//                            System.out.println(i+","+j+":"+sum);
                            ans = Math.max(ans, sum);
                        }

                        term++;
                    }
                }
            }
        }

//        for(int i = 0; i<n; i++){
//            System.out.println(Arrays.toString(dp[i]));
//        }
        System.out.println(ans);





    }

}