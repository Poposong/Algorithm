import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n];
        String[] str = br.readLine().split(" ");
        for(int i = 0; i<n; i++){
            A[i] = Integer.parseInt(str[i]);
        }
        List<Integer>[] list = new ArrayList[n];
        for(int i = 0; i<n; i++){
            list[i] = new ArrayList<Integer>();
        }

        int[] dp = new int[n];
        Arrays.fill(dp, 0);

        int[] seat = new int[n];
        for(int i = 0; i<n; i++){
            seat[i] = i;
        }

        int max = 0;
        for(int i = 0; i<n; i++){
            int n1 = A[i];
            for(int j = i+1; j<n; j++){
                int n2 = A[j];
                if(n1 < n2 && dp[j] < dp[i]+1){
                    dp[j] = dp[i]+1;
                    seat[j] = i;
                }
            }
            max = Math.max(max, dp[i]);
        }

       // System.out.println(max);

        StringBuilder sb = new StringBuilder();


        for(int i = n-1; i>=0; i--){
            if(dp[i] == max){
                int idx = i;
                boolean check = true;
                while(check){
                    sb.insert(0, A[idx]+" ");
                    if(idx == seat[idx]){
                        check = false;
                        break;
                    }
                    idx = seat[idx];
                }
                if(!check){
                    break;
                }
            }
        }
        sb.insert(0, (max+1)+"\n");
        System.out.println(sb.toString());
        //System.out.println(Arrays.toString(dp));
        //System.out.println(Arrays.toString(seat));

    }
}