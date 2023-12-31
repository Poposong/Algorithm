import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        String[] str = br.readLine().split(" ");
        int[] number = new int[N+1]; // 1~N까지
        for(int i = 1; i<N+1; i++){
            number[i] = Integer.parseInt(str[i-1]);
        }

        int M = Integer.parseInt(br.readLine());

        while(M-- != 0){
            str = br.readLine().split(" ");

            int start = Integer.parseInt(str[0]);
            int end = Integer.parseInt(str[1]);

            sb.append(isPalindrom(number, start, end) ? 1 : 0).append("\n");
        }

        System.out.println(sb.toString());



    }

    static boolean isPalindrom(int[] number, int start, int end){
        while(start <= end){
            if(number[start] != number[end]){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
