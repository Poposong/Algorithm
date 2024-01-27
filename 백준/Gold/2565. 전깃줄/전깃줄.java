import java.io.*;
import java.util.*;
// 가장 많이 전깃줄을 설치하는 경우의 수를 찾는다.
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] wire = new int[n][2];

        for(int i = 0; i<n; i++){
            String[] str = br.readLine().split(" ");
            wire[i][0] = Integer.parseInt(str[0]);
            wire[i][1] = Integer.parseInt(str[1]);
        }

        Arrays.sort(wire, new Comparator<int[]>(){
            public int compare(int[] arr1, int[] arr2){
                return arr1[0]-arr2[0]; // A를 기점으로 오름차순으로 정렬한다.
            }
        });


        int[] dp = new int[n];
        // n은 wire의 수이다. 각 시점에서 wire를 두었을 때, 최대 몇 개를 놓을 수 있는지 확인한다.
        for(int i = 0; i<n; i++){
            dp[i] = 1;

            // 아래의 값들을 dp로 메모제이션
            for(int j = 0; j<i; j++){ // 이전에 설치한 와이어 중에서 가능한 수에 +1한 값이 더 크면 값을 변경한다.
                if(wire[i][1] > wire[j][1]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }

        int res = dp[0];
        for(int i = 1; i<n; i++){
            res = Math.max(res, dp[i]);
        }

        System.out.println(n - res);


    }
}