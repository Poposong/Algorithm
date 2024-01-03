package Baekjoon;
import java.io.*;
import java.util.*;
public class BJ_20040_사이클게임 {
    static int[] number;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);

        number = new int[N];
        for(int i = 0; i<N; i++){
            number[i] = i;
        }
        
        // 시간복잡도 : O(M)
        int v1,v2,p1,p2;
        for(int i = 1; i<=M; i++){
            str = br.readLine().split(" ");
            v1 = Integer.parseInt(str[0]);
            v2 = Integer.parseInt(str[1]);
            
            
            if(!union(v1, v2)){
                System.out.println(i);
                return;
            }
        }

        System.out.println(0);
    }
    // 합치기
    public static boolean union(int v1, int v2){
        int p1 = findParent(v1);
        int p2 = findParent(v2);

        if(p1 == p2){ // 사이클이 발생함
            return false;
        }

        if(p1 < p2){
            number[p2] = p1;
        }else{
            number[p1] = p2;
        }

        return true;
    }

    // 부모를 찾음
    public static int findParent(int vertex){
        if(number[vertex] == vertex){
            return vertex;
        }
        return number[vertex] = findParent(number[vertex]); // 경로압축(트리의 높이를 낮춘다)
    }

}
