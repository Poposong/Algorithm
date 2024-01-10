import java.io.*;
import java.util.*;

/**
 [1 5 4 6 2 1 3 7]

 1) left = 0, right = 7 => mid = 7/2 = 3
 (1) (5, 4, 6) (2, 1, 3) (7)
 결과) 4 ---> m보다 크기 때문에 mid를 더 크게 설정해서 가능한 경우의 수를 줄인다. 따라서, left = mid + 1을 한다.

 2) left = 4, right = 7 => mid = 11/2 = 5
 (1, 5, 4, 6, 2, 1, 3) (7)
 결과) 2 ---> m보다 작기 때문에 mid을 더 작게 설정해서 가능한 경우의 수를 늘린다. 따라서, right = mid를 한다.

 3) left = 4, right = 5 => mid = 9/2 = 4
 (1, 5, 4) (6, 2) (1, 3) (7)
 결과) 4 ---> m보다 크기 때문에 mid를 더 크게 설정해서 가능한 경우의 수를 줄인다. 따라서, left = mid +1을 한다.

 4) left = 5, right = 5 (종료)
 * */
public class Main {
    static int N, M;
    static int[] arr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);

        str = br.readLine().split(" ");

        int max = Integer.MIN_VALUE;
        arr = new int[N];
        for(int i = 0; i<N; i++){
            arr[i] = Integer.parseInt(str[i]);
            max = Math.max(max, arr[i]);
        }

        int left = 0, right = max;

        while(left < right){
            int mid = (left+right)/2;

            if(possibleCount(mid) <= M){
                // M보다 작거나 같으므로 가능한 경우의 수를 키우기 위해서는 mid가 작아야 한다.
                right = mid;
            }else {
                // M보다 크기 때문에 가능한 경우의 수를 줄이기 위해서는 mid가 커야 한다.
                left = mid + 1;
            }
        }

        System.out.println(right);

    }

    static int possibleCount(int mid){
        int count = 1; // 제일 마지막에 남는 부분 배열인 경우가 있으므로 1로 설정한다.
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for(int i = 0; i<N; i++){
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);

            if(max-min > mid){
                count++;
                min = Integer.MAX_VALUE;
                max = Integer.MIN_VALUE;
                i--;
            }
        }
        return count;
    }
}