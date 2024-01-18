import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int k = Integer.parseInt(str[1]);

        int[] coinList = new int[n];
        for(int i = 0; i<n; i++){
            coinList[i] = Integer.parseInt(br.readLine());
        }

        int[] count = new int[k+1]; // 1~k까지
        Arrays.fill(count, 0);
        count[0] = 1;

        for(int coin : coinList){
            for(int i = coin; i<k+1; i++){
                count[i] += count[i-coin];
            }
        }

        // System.out.println(Arrays.toString(count));
        System.out.println(count[k]);


    }
}