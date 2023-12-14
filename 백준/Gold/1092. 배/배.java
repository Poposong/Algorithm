package Baekjoon;

import java.io.*;
import java.util.*;

// 시간복잡도
// while문에서 arraylist로 구현하면 10,000 * 10,000 * 1 이고
// linkedlist로 구현하면 10,000 * 10,000 * (10,000*50) 이다.
// 따라서, arrayList로 구현해야한다.
public class BJ_1092_배 {
    static int N,M;
    static ArrayList<Integer> crain, box;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String[] str = br.readLine().split(" ");
        crain = new ArrayList<>();
        for(int i = 0; i<N; i++){
            crain.add(Integer.parseInt(str[i]));
        }

        M = Integer.parseInt(br.readLine());
        str = br.readLine().split(" ");
        box = new ArrayList<>();
        for(int i = 0; i<M; i++){
            box.add(Integer.parseInt(str[i]));
        }

        crain.sort(Collections.reverseOrder());
        box.sort(Collections.reverseOrder());

        if(crain.get(0) < box.get(0)){
            System.out.println(-1);
            return;
        }

        int time = 0;
        int boxIdx, crainIdx;
        while(!box.isEmpty()){ // M번
            boxIdx = 0;
            crainIdx = 0;

            while(crainIdx < N){ // M번
                if(boxIdx == box.size())
                    break;

                if(crain.get(crainIdx) >= box.get(boxIdx)){ // arrayList : O(1) , arrayList :  O(n*m)
                    box.remove(boxIdx); // arrayList : O(n), arrayList : O(n)
                    crainIdx++;
                }else{
                    boxIdx++;
                }
            }

            time++;
        }

        System.out.println(time);


    }
}
