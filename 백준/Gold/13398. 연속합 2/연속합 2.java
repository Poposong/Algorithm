import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] number = new int[n];

        String[] str = br.readLine().split(" ");
        for(int i = 0; i<n; i++){
            number[i] = Integer.parseInt(str[i]);
        }

        int[] dp1 = new int[n]; // 오른쪽으로 가는 방향
        int[] dp2 = new int[n]; // 왼쪽으로 가는 방향

        // 수를 제거하는 않으면서 연속합의 최대를 저장한다.
        int ans = number[0];
        dp1[0] = number[0];
        for(int i = 1; i<n; i++){
            dp1[i] = Math.max(number[i], dp1[i-1]+number[i]);
            ans = Math.max(ans, dp1[i]);
        }

        dp2[n-1] = number[n-1];
        for(int i = n-2; i>=0; i--){
            dp2[i] = Math.max(number[i], dp2[i+1]+number[i]);
            ans = Math.max(ans, dp2[i]);
        }

        // System.out.println(Arrays.toString(dp1));
        // System.out.println(Arrays.toString(dp2));

        // 수를 한 개 제거했을 때의 연속합의 최대를 저장한다.
        for(int i = 1; i<n-1; i++){
            int sum = dp1[i-1] + dp2[i+1];
            ans = Math.max(ans, sum);
        }

        System.out.println(ans);
    }
}