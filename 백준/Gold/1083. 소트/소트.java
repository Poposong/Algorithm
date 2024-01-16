package Baekjoon;
import java.io.*;
import java.util.*;
public class BJ_1083_소트 {
    static int N, S;
    static int[] number;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String[] str = br.readLine().split(" ");
        S = Integer.parseInt(br.readLine());
        number = new int[N];
        for(int i = 0; i<N; i++){
            number[i] = Integer.parseInt(str[i]);
        }

        for(int i = 0; i<N; i++){
            int findIdx = findChangeIdx(i, i+S); // S번째로 바꿀 수 있는 거리까지 탐색에서 가장 큰 숫자가 있는 인덱스를 찾는다.
            if(findIdx!=i){
                serialRevSwap(i, findIdx);
                S -= (findIdx - i);
            }
            if(S <= 0){
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int num : number){
            sb.append(num+" ");
        }
        System.out.println(sb.toString());

    }
    static void serialRevSwap(int start, int end){
        for(int i = end; i>start; i--){
            swap(i, i-1);
        }
    }

    static void swap(int a, int b){
        int temp = number[a];
        number[a] = number[b];
        number[b] = temp;
    }

    static int findChangeIdx(int start, int end){
        int maxValue = number[start];
        int maxIdx = start;
        if(end >= N){
            end = N-1;
        }
        for(int i = start+1; i<=end; i++){
            if(number[i] > maxValue){
                maxValue = number[i];
                maxIdx = i;
            }
        }
        return maxIdx;
    }
}
