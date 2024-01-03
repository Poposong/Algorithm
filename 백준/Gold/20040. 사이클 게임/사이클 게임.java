import java.io.*;
import java.util.*;
public class Main {
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

        int v1,v2,p1,p2;
        for(int i = 1; i<=M; i++){
            str = br.readLine().split(" ");
            v1 = Integer.parseInt(str[0]);
            v2 = Integer.parseInt(str[1]);

            p1 = findParent(v1);
            p2 = findParent(v2);

            if(p1 == p2){
                System.out.println(i);
                return;
            }

            if(p1 < p2){
                number[p2] = p1;
            }else{
                number[p1] = p2;
            }
        }
        // System.out.println(Arrays.toString(number));
        System.out.println(0);
    }

    // 부모를 찾음
    public static int findParent(int vertex){
        if(number[vertex] == vertex){
            return vertex;
        }
        return number[vertex] = findParent(number[vertex]);
    }

}