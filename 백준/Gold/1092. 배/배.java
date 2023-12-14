import java.io.*;
import java.util.*;

public class Main {
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
        while(!box.isEmpty()){
            boxIdx = 0;
            crainIdx = 0;

            while(crainIdx < N){
                if(boxIdx == box.size())
                    break;

                if(crain.get(crainIdx) >= box.get(boxIdx)){
                    box.remove(boxIdx);
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