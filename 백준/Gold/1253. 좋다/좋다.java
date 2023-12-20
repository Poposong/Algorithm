// 1. 투포인터를 사용함. O(n^2)의 시간복잡도를 가진다.
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

// 2. map으로 풀기(시간복잡도 : O(N^2)
// get()와 containsKey()의 시간복잡도는 O(1)으로 배열이 아닌 map으로 풀었다.
import java.io.*;
import java.util.*;

import java.io.*;
import java.util.*;

public class Main {
    static int[] number;
    static int result;
    static Map<Integer, Integer> map;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        number = new int[N];
        map = new HashMap<>();
        String[] str = br.readLine().split(" ");
        for(int i = 0; i < N; i++){
            number[i] = Integer.parseInt(str[i]);
            map.put(number[i], map.getOrDefault(number[i], 0) + 1);
        }

        int cnt, sum;
        for(int i = 0; i<N-1; i++){
            for(int j = i+1; j<N; j++){
                sum = number[i]+number[j];

                if(map.containsKey(sum)){
                    cnt = map.get(sum);

                    if(number[i] == 0 && number[j] == 0){ // 0 + 0 = 0(이때, 0이 3개 이상인 경우에만 가능함)
                        if(cnt >= 3){
                            result += map.remove(sum);
                        }
                    }else if(number[i] == 0 || number[j] == 0){ // 0 + 1 = 1과 같이 자신과 같은 수를 더하는 경우에는 cnt가 2개 이상인 경우에만 가능하다.
                        if(cnt >= 2){
                            result += map.remove(sum);
                        }
                    }else{
                        result += map.remove(sum);
                    }
                }

                if(result == N){
                    System.out.println(N);
                    return;
                }
            }
        }

        System.out.println(result);



    }


}
