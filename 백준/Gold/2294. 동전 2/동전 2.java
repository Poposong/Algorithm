import java.io.*;
import java.util.*;

// 가장 큰 동전부터 갈 수 있도록 한다.
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int k = Integer.parseInt(str[1]);

        List<Integer> coinList = new ArrayList<Integer>();
        for(int i = 0; i<n; i++){
            coinList.add(Integer.parseInt(br.readLine()));
        }

        int inf = Integer.MAX_VALUE-1;
        int[] route = new int[k+1];
        Arrays.fill(route, inf);

        for(int coin : coinList){
            if(coin > k) continue;
            route[coin] = 1;
            for(int i = coin; i<k+1; i++){
                if(i-coin > k) continue;
                if(route[i-coin]+1 < route[i]){
                    route[i] = route[i-coin]+1;
                }
            }
        }

        System.out.println(route[k] == inf? -1 : route[k]);
        // System.out.println(Arrays.toString(route));


    }
}