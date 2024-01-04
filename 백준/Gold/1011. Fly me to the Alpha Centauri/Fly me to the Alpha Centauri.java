package Baekjoon;
import java.io.*;
import java.util.*;
// 대부분의 경우에는 규칙이 있기 때문에 모르겠으면 표나 그림으로 나열해보기!!!!!!
public class BJ_1011_Fly_me_to_the_Alpha_Centauri {
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

// 참고 : https://st-lab.tistory.com/79
