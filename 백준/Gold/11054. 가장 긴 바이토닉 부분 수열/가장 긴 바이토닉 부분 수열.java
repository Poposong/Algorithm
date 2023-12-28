import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] numberArray = new int[N];
        String[] str = br.readLine().split(" ");
        for(int i = 0; i<N; i++){
            numberArray[i] = Integer.parseInt(str[i]);
        }
        int[] left = new int[N];
        // 왼쪽 검사
        left[0] = 1;
        int max;
        for(int i = 1; i<N; i++){
            max = 1;
            boolean check = false;
            for(int j = i-1; j>=0; j--){
                if(numberArray[j] < numberArray[i]){
                    max = Math.max(max, left[j]);
                    check = true;
                }
            }
            if(!check){
                left[i] = 1;
            }else{
                left[i] = max+1;
            }
        }
        int[] right = new int[N];
        right[N-1] = 1;
        for(int i = N-2; i>=0; i--){
            max = 1;
            boolean check = false;
            for(int j = i+1; j<N; j++){
                if(numberArray[j] < numberArray[i]){
                    max = Math.max(max, right[j]);
                    check = true;
                }
            }
            if(!check){
                right[i] = 1;
            }else{
                right[i] = max+1;
            }
        }

        int answer = left[0]+right[0];
        for(int i = 1; i<N; i++){
            answer = Math.max(answer, left[i]+right[i]);
        }
        System.out.println(answer-1);

        //System.out.println(Arrays.toString(left));
        //System.out.println(Arrays.toString(right));

        // 오른쪽 검사
    }
}