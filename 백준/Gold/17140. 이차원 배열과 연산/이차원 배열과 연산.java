import java.io.*;
import java.util.*;

/**
 크기가 3×3인 배열 A가 있다. 배열의 인덱스는 1부터 시작한다. 1초가 지날때마다 배열에 연산이 적용된다.

 R 연산: 배열 A의 모든 행에 대해서 정렬을 수행한다. 행의 개수 ≥ 열의 개수인 경우에 적용된다.
 C 연산: 배열 A의 모든 열에 대해서 정렬을 수행한다. 행의 개수 < 열의 개수인 경우에 적용된다.

 행 또는 열의 크기가 100을 넘어가는 경우에는 처음 100개를 제외한 나머지는 버린다.

 배열 A에 들어있는 수와 r, c, k가 주어졌을 때, A[r][c]에 들어있는 값이 k가 되기 위한 최소 시간을 구해보자.

 입력
 첫째 줄에 r, c, k가 주어진다. (1 ≤ r, c, k ≤ 100)
 둘째 줄부터 3개의 줄에 배열 A에 들어있는 수가 주어진다. 배열 A에 들어있는 수는 100보다 작거나 같은 자연수이다.

 출력
 A[r][c]에 들어있는 값이 k가 되기 위한 연산의 최소 시간을 출력한다. 100초가 지나도 A[r][c] = k가 되지 않으면 -1을 출력한다.
 * */
public class Main {
    static int[][] map;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int r = Integer.parseInt(str[0])-1;
        int c = Integer.parseInt(str[1])-1;
        int k = Integer.parseInt(str[2]);

        map = new int[3][3];
        for(int i = 0; i<3; i++){
            str = br.readLine().split(" ");
            for(int j = 0; j<3; j++){
                map[i][j] = Integer.parseInt(str[j]);
            }
        }



        int timer = 0;
        while(true){
            if(timer > 100){
                break;
            }

            if(r<map.length && c<map[0].length){
                if(map[r][c] == k){
                    System.out.println(timer);
                    return;
                }
            }

            if(map.length >= map[0].length){
                rowCalculator();
            }else{
                colCalculator();
            }

            timer++;
        }

        System.out.println(-1);


    }

    static void rowCalculator(){
        int size = Integer.MIN_VALUE;
        int[][] temp = new int[101][101];

        for(int i = 0; i<map.length; i++){
            int[] value = new int[101];
            ArrayList<int[]> list = new ArrayList<>();

            for(int j = 0; j<map[0].length; j++){
                if(map[i][j] == 0) continue;
                value[map[i][j]]++;
            }

            for(int j = 1; j<value.length; j++){
                if(value[j] == 0) continue;
                list.add(new int[]{j, value[j]}); // (숫자, 횟수)
            }

            Collections.sort(list, new Comparator<int[]>(){
                public int compare(int[] a, int[] b){ // (숫자, 횟수)
                    if(a[1] == b[1]){
                        return a[0]-b[0];
                    }
                    return a[1]-b[1];
                }
            });

            int idx = 0;
            for(int j = 0; j<list.size(); j++){
                if(idx == 100) break;
                temp[i][idx] = list.get(j)[0];
                temp[i][idx+1] = list.get(j)[1];
                idx+=2;
            }

            size = Math.max(size, idx);
        }

        if(size>100) size=100;
        // 재정렬
        map = new int[map.length][size];
        for(int i = 0; i<map.length; i++){
            for(int j = 0; j<size; j++){
                map[i][j] = temp[i][j];
            }
        }
    }

    static void colCalculator(){
        int size = Integer.MIN_VALUE;
        int[][] temp = new int[101][101];

        for(int i = 0; i<map[0].length; i++){
            int[] value = new int[101];
            ArrayList<int[]> list = new ArrayList<>();

            for(int j = 0; j<map.length; j++){
                if(map[j][i] == 0) continue;
                value[map[j][i]]++;
            }

            for(int j = 1; j<value.length; j++){
                if(value[j] == 0) continue;
                list.add(new int[]{j, value[j]}); // (숫자, 횟수)
            }

            Collections.sort(list, new Comparator<int[]>(){
                public int compare(int[] a, int[] b){ // (숫자, 횟수)
                    if(a[1] == b[1]){
                        return a[0]-b[0];
                    }
                    return a[1]-b[1];
                }
            });

            int idx = 0;
            for(int j = 0; j<list.size(); j++){
                if(idx == 100) break;
                temp[idx][i] = list.get(j)[0];
                temp[idx+1][i] = list.get(j)[1];
                idx+=2;
            }

            size = Math.max(size, idx);
        }

        if(size>100) size=100;

        // 재정렬
        map = new int[size][map[0].length];
        for(int i = 0; i<size; i++){
            for(int j = 0; j<map[0].length; j++){
                map[i][j] = temp[i][j];
            }
        }
    }

}