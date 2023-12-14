import java.io.*;
import java.util.*;

public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] ban = new int[N][2];

        int s,t;
        String[] str;
        for(int i = 0; i<N; i++){
            str = br.readLine().split(" ");
            ban[i][0] = Integer.parseInt(str[0]);
            ban[i][1] = Integer.parseInt(str[1]);
        }

        Arrays.sort(ban, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                if(a[0] == b[0]){
                    return a[1]-b[1];
                }
                return a[0]-b[0];
            }
        });

        System.out.println(findClass(ban));

    }

    static int findClass(int[][] ban){
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>(){
            public int compare(Integer a, Integer b ){
                return a-b; // 오름차순으로 정렬(마지막 수가 가장 작은 순서로)
            }
        });
        pq.offer(ban[0][1]);

        int end;
        for(int i = 1; i<ban.length; i++){
            end = pq.peek();
            if(end <= ban[i][0]){
                pq.poll();
            }
            pq.offer(ban[i][1]);
        }

        return pq.size();
    }
}