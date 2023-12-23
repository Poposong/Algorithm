package Baekjoon;
import java.io.*;
import java.util.*;

/**
 * 처음에는 리스트로 구현해서 삽입하는 경우에만 이분탐색을 하려고 했지만 이분탐색은 최악인 경우 O(N)의 시간복잡도를 가지기 때문에 다른 방법으로 접근했다.
 * 최소 우선순위 큐와 최대 우선순위 큐로 구현했을 때 삭제하는 경우는 O(logN)이다
 * */

public class BJ_7662_이중우선순위큐 {

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
                        minOrder.offer(value); // O(logN)
                        maxOrder.offer(value); // O(logN)
                        count.put(value, count.getOrDefault(value, 0)+1);
                        break;
                    case 'D':
                        if(count.size()!=0){
                            if(value == -1) {
                                removeValue(minOrder); // O(logN)
                            }else{
                                removeValue(maxOrder); // O(logN)
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
        while(true){ // O(logN) + O(log(N-1)) + O(log(N-2)) + ... + O(log(1)) === O(log(N))
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
