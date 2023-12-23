import java.io.*;
import java.util.*;

/**
 * 삽입할 때, O(logn) 의 시간복잡도를 가진다.
 * O(nlogn)의 시간복잡도
 * */
public class Main {


    static Map<Integer, Integer> count;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        int k, value;

        PriorityQueue<Integer> minOrder = new PriorityQueue<>();
        PriorityQueue<Integer> maxOrder = new PriorityQueue<>(Collections.reverseOrder());
        count = new HashMap<>();

        String[] str;
        while(t-- != 0){
            minOrder.clear();
            maxOrder.clear();
            count.clear();
            k = Integer.parseInt(br.readLine());
            for(int i = 0; i<k; i++){
                str = br.readLine().split(" ");
                value = Integer.parseInt(str[1]);
                switch(str[0].charAt(0)){
                    case 'I':
                        // 이분탐색으로 자리를 찾기 => O(logn)
                        minOrder.offer(value);
                        maxOrder.offer(value);
                        count.put(value, count.getOrDefault(value, 0)+1);
                        break;
                    case 'D': // 잘하면 O(1) 아니면 O(k)?
                        if(count.size()!=0){
                            if(value == -1) {
                                removeValue(minOrder);
                            }else{
                                removeValue(maxOrder);
                            }
                        }
                        break;
                }
            }

            if(count.size()==0){
                sb.append("EMPTY\n");
            }else{
                int max = removeValue(maxOrder);
                sb.append(max).append(" ").append(count.size()==0? max : removeValue(minOrder)).append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    static int removeValue(PriorityQueue<Integer> pq){
        int num;
        while(true){
           num = pq.poll();

            int cnt = count.getOrDefault(num, 0);
            if(cnt == 0) continue;

           if(cnt == 1){
               count.remove(num);
           }else{
               count.put(num, cnt-1);
           }
           break;
        }
        return num;
    }
}