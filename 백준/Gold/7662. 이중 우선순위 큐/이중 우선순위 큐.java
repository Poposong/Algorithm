import java.io.*;
import java.util.*;

/**
TreeMap
 * */

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        int k, value;

        TreeMap<Integer, Integer> pq = new TreeMap<>();

        String[] str;
        while(t-- != 0){
            pq.clear();
            k = Integer.parseInt(br.readLine());
            for(int i = 0; i<k; i++){
                str = br.readLine().split(" ");
                value = Integer.parseInt(str[1]);
                switch(str[0].charAt(0)){
                    case 'I':
                        pq.put(value, pq.getOrDefault(value, 0)+1);
                        break;
                    case 'D':
                        if(pq.size()==0) continue;

                        int key;
                        if(value == -1) {
                            key = pq.firstKey();
                        }else{
                            key = pq.lastKey();
                        }

                        int cnt = pq.get(key);

                        if(cnt != 1){
                            pq.put(key, cnt-1);
                        }else{
                            pq.remove(key);
                        }

                        break;
                }
            }

            if(pq.size()==0){
                sb.append("EMPTY\n");
            }else{
                sb.append(pq.lastKey()).append(" ").append(pq.firstKey()).append("\n");
            }
        }
        System.out.println(sb.toString());
    }

}