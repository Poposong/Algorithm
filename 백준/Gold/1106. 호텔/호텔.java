import java.io.*;
import java.util.*;
/**
 * C, N(1<=C<=1,000, 1<=N<=20)
 * N개의 줄에는 각 도시에서 홍보할 때 대는 비용과 그 비용으로 얻을 수 있는 고객의 수 (1<= <=100) cost, person
 * */
public class Main {
    static int result = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int C = Integer.parseInt(str[0]);
        int N = Integer.parseInt(str[1]);

        int[] dp = new int[C*2+101]; // C*2의 값보다 N의 값이 큰 경우가 발생하므로 N의 크기만큼 크기를 확장함
        Arrays.fill(dp, Integer.MAX_VALUE);

        // 비용, 고객 수
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                if(a[0] == b[0]){
                    return a[1]-b[1]; // 고객의 수는 오름차순
                }
                return b[0]-a[0]; // 비용 내림차순
            }
        });

        int min = Integer.MAX_VALUE;
        for(int i = 0; i<N; i++){
            str = br.readLine().split(" ");
            pq.offer(new int[]{
                    Integer.parseInt(str[0]), Integer.parseInt(str[1])
            });
        }

        int c,p;
        while(!pq.isEmpty()){
            c = pq.peek()[0];
            p = pq.peek()[1];
            pq.poll();

            dp[p] = Math.min(dp[p], c);
            for(int i = p; i<dp.length; i++){
                if(i-p<1) continue;
                if(dp[i-p]!=Integer.MAX_VALUE && dp[i-p]+c < dp[i]) {
                    dp[i] = dp[i-p]+c;
                }
            }

        }
        for(int i = C; i<dp.length; i++){
            result = Math.min(result, dp[i]);
        }

        System.out.println(result);
    }
}
