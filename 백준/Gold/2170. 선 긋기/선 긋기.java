import java.io.*;
import java.util.*;

/*
* 1. 받은 선을 (오름차순, 오름차순) 으로 정렬한다.
* 2. 우선순위 큐에 가능한 선이 있다면 확장한다. or 우선순위 큐에 가능한 선이 없다면 새로운 선을 큐에 추가한다.
* */
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<int[]> lines = new ArrayList<>();
        for(int i = 0; i<N; i++){
            String[] str = br.readLine().split(" ");
            int x = Integer.parseInt(str[0]);
            int y = Integer.parseInt(str[1]);

            if(y<x){
                int temp = x;
                x = y;
                y = temp;
            }

            lines.add(new int[]{
               x, y
            });
        }
        // 1. 받은 선을 (오름차순, 오름차순) 으로 정렬한다.
        Collections.sort(lines, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                if(a[0] == b[0]){
                    return a[1]-b[1];
                }
                return a[0]-b[0];
            }
        });

//        PriorityQueue<int[]> exLine = new PriorityQueue<>(new Comparator<int[]>(){
//            public int compare(int[] a, int[] b){
//                if(a[0] == b[0]){
//                    return a[1]-b[1];
//                }
//                return a[0]-b[0];
//            }
//        });

        ArrayList<int[]> exLine = new ArrayList<>();

        for(int[] line : lines){
            boolean possible = false;
            for(int i = 0; i<exLine.size(); i++){
                int[] newLine = exLine.get(i);
                if(newLine[0] <= line[0] && line[0] <= newLine[1]){
                    if(newLine[1] < line[1]){
                        exLine.get(i)[1] = line[1];
                    }
                    possible = true;
                    break;
                }
            }
            if(!possible){
                exLine.add(new int[]{line[0], line[1]});
            }
        }

        int ans = 0;
        for(int[] line : exLine){
            ans += line[1]-line[0];
        }

        System.out.println(ans);


    }
}