package Baekjoon;

import java.io.*;
import java.util.*;

// [시간복잡도]
// 분할정복으로 푸는 문제
// 2<=N<=5, B는 최대 1000억
// 분할정복의 시간복잡도는 log(B) = log(1000억)은 대략 25.xx
// 두 행렬을 곱하는 시간복잡도는 log(N^3) = log(125)
// 따라서, 125*25.xx 이다.

public class BJ_10830_행렬제곱 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        long B = Long.parseLong(str[1]);

        long[][] array = new long[N][N];
        for(int i = 0; i<N; i++){
            str = br.readLine().split(" ");
            for(int j = 0; j<N; j++){
                array[i][j] = Long.parseLong(str[j]);
            }
        }

        long[][] result = divideCalculator(array, B);

        StringBuilder sb = new StringBuilder();
        for(int i =0; i<N; i++){
            for(int j = 0; j<N; j++){
                sb.append(result[i][j]+" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    static long[][] divideCalculator(long[][] m1, long b){
        if(b == 1){
            for(int i = 0; i<N; i++){
                for(int j = 0; j<N; j++){
                    m1[i][j] = m1[i][j]%1000;
                }
            }
            return m1;
        }

        long[][] m2 = divideCalculator(m1, b/2);
        return (b%2==0) ? productCalculator(m2,m2):productCalculator(productCalculator(m2,m2), m1);
    }

    static long[][] productCalculator(long[][] m1, long[][] m2){
        long[][] result = new long[N][N];
        long sum;
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                sum = 0;
                for(int k = 0; k<N; k++){
                    sum += m1[i][k] * m2[k][j];
                    sum = sum%1000;
                }
                result[i][j] = sum;
            }
        }
        return result;
    }
}
