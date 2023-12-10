import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

// 시간복잡도 : O(n)
// 1<= k <= 100
// n=4
// 따라서, O(n * k) = O(4n) => O(n) 이다.


public class Main {
    static HashMap<Integer, LinkedList<Integer>> roar;
    static boolean[] visitedRoar;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        roar = new HashMap<>();
        visitedRoar = new boolean[5];

        String[] str;
        for(int i = 1; i < 5; i++){
            str = br.readLine().split("");
            roar.put(i, new LinkedList<>());
            for(int j = 0; j<str.length; j++){
                roar.get(i).add(Integer.parseInt(str[j]));
            }
        }

        int k = Integer.parseInt(br.readLine());
        int num, dir, result = 0;
        while(k-- != 0) {
            str = br.readLine().split(" ");
            num = Integer.parseInt(str[0]); // 몇 번째 톱니바퀴인지를 고른다.
            dir = Integer.parseInt(str[1]); // 방향을 고른다.

            Arrays.fill(visitedRoar, false);
            visitedRoar[num] = true;

            compareSizeRoar(num, dir);
            
        }

        int score = 1;
        for(int i =1; i<5; i++, score*=2){
            if(roar.get(i).get(0) == 1) {
                result += score;
            }
        }

        System.out.println(result);
    }

    // 자신의 톱니바퀴를 비교한다.
    static void compareSizeRoar(int num, int dir){
        // 오른쪽 톱니바퀴 비교하기
        if(num+1 <= 4 && !visitedRoar[num+1]){
            if(roar.get(num).get(2) != roar.get(num+1).get(6)){ // N극과 S극이 만남(현재 톱니바퀴의 2번과 오른쪽 톱니바퀴의 6번이 일치하는지 비교한다.)

                // 오른쪽 전염시키기
                visitedRoar[num+1] = true;
                compareSizeRoar(num+1, dir*(-1));
            }
        }

        // 왼쪽 톱니바퀴 비교하기
        if(num-1 >= 1 && !visitedRoar[num-1]){
            if(roar.get(num).get(6) != roar.get(num-1).get(2)){ // N극과 S극이 만남(현재 톱니바퀴의 6번과 왼쪽 톱니바퀴의 2번이 일치하는지 비교한다.)

                // 왼쪽 전염시키기
                visitedRoar[num-1] = true;
                compareSizeRoar(num-1, dir*(-1));
            }
        }

        // 자신의 방향 돌리기
        if(dir == -1){ // 반시계 방향으로 돌리는 경우
            roar.get(num).add(roar.get(num).remove(0));
        }else{ // 시계 방향으로 돌리는 경우
            roar.get(num).add(0, roar.get(num).remove(7)); // 마지막 원소를 제일 앞에 넣는다.
        }
    }
}
