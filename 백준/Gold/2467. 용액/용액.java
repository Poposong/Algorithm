import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] temp = br.readLine().split(" ");
        long[] solution = new long[N];
        for(int i = 0; i<N; i++){
            solution[i] = Long.parseLong(temp[i]);
        }

        int left = 0,  right = N-1;
        int resultLeft = 0, resultRight = 0;
        long min = Long.MAX_VALUE;
        while(left<right) {
            long sum = solution[left] + solution[right];

            if (Math.abs(sum) < min) {
                min = Math.abs(sum);
                resultLeft = left;
                resultRight = right;
            }

            if (sum >= 0) {
                right--;
            } else {
                left++;
            }
        }

        System.out.println(solution[resultLeft] + " " + solution[resultRight]);
    }
}