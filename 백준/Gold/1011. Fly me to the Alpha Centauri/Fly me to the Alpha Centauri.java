import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] str;
        int T = Integer.parseInt(br.readLine());

        int x, y;
        while(T-- != 0){
            str = br.readLine().split(" ");
            x = Integer.parseInt(str[0]);
            y = Integer.parseInt(str[1]);

            int distance = y-x;
            int max = (int)Math.sqrt(distance);

            if(max == Math.sqrt(distance)){
                sb.append(max*2 - 1);
            }else if(distance <= max * max + max){
                sb.append(max*2);
            }else{
                sb.append(max*2 + 1);
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());

    }
}