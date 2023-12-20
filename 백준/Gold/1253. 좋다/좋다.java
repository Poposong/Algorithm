import java.io.*;
import java.util.*;

public class Main {
    static int[] number;
    static int result;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        number = new int[N];
        String[] str = br.readLine().split(" ");
        for(int i = 0; i < N; i++){
            number[i] = Integer.parseInt(str[i]);
        }
        Arrays.sort(number);

        for(int mid = 0; mid<N; mid++){
            int left = 0;
            int right = N-1;

            while(true){
                if(left == mid) left++;
                else if(right == mid) right--;

                if(left >= right) break;

                if(number[left]+number[right] > number[mid]) right--;
                else if(number[left]+number[right] < number[mid]) left++;
                else{
                    result++;
                    break;
                }
            }
        }

        System.out.println(result);





    }


}