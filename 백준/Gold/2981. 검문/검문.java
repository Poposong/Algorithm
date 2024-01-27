import java.io.*;
import java.math.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for(int i = 0; i<N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);
        BigInteger result = BigInteger.valueOf(arr[N-1]-arr[N-2]);
        for(int i  = N-2; i>=1; i--){
            int num = arr[i] - arr[i-1];
            result = result.gcd(BigInteger.valueOf(num));
        }

        //  System.out.println(result);

        // 약수구하기
        StringBuilder sb = new StringBuilder();
        int number = Integer.valueOf(String.valueOf(result));
        for(int i = 2; i<number+1; i++){
            if(number%i == 0){
                sb.append(i).append(" ");
            }
        }
        System.out.println(sb.toString());

    }
}