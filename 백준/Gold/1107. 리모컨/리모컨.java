package Baekjoon;

import java.io.*;
import java.util.*;
// 완전탐색
// O(999999*6) < 2억
public class BJ_1107_리모컨 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean[] remocon = new boolean[10]; // 0~9까지 리모컨의 숫자

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        if(M==0){
            System.out.println(Math.min(Math.abs(N-100), String.valueOf(N).length()));
            return;
        }

        String[] str = br.readLine().split(" ");
        Arrays.fill(remocon, true);
        for(int i = 0; i<M; i++){
            remocon[Integer.parseInt(str[i])] = false; // 사용할 수 없다.
        }

        char c;
        String strNumber;
        int result = Math.abs(N-100);
        for(int i = 0; i<=999999; i++){
            strNumber = String.valueOf(i);

            boolean check = true;
            for(int j = 0; j<strNumber.length(); j++){
               c = strNumber.charAt(j);
               if(!remocon[Integer.valueOf(String.valueOf(c))]){
                   check = false;
                   break;
               }
            }

            if(check){
                result = Math.min(result, strNumber.length()+Math.abs(N-i));
            }
        }
        System.out.println(result);
    }


}
