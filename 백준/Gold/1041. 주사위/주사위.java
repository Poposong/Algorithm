import java.io.*;
import java.util.*;
/**
 * 1<=N<=1,000,000
 * 1<=A,B,C,D,E,F<=50
 * */
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] str = br.readLine().split(" ");
        int[] dice = new int[6]; // A,B,C,D,E,F 순서
        long[] diceSum = new long[4]; // 주사위는 최대 3개까지 보일 수 있다.
        Arrays.fill(diceSum, 151);

        int sum = 0, max = 0;
        for(int i = 0; i<6; i++){
            dice[i] = Integer.parseInt(str[i]);
            sum += dice[i];
            max = Math.max(max, dice[i]);
            diceSum[1] = Math.min(diceSum[1], dice[i]);
        }
        if(N == 1){
            System.out.println(sum-max);
            return;
        }

        // 2, 3개의 주사위가 보일 때 최솟값을 구한다.
        int[] temp = new int[]{
                dice[4]+dice[0],
                dice[0]+dice[1],
                dice[1]+dice[5],
                dice[5]+dice[4]
        };
        int[] idx = new int[]{
                4, 0, 1, 5
        };
        for(int i = 0; i<4; i++){
            diceSum[3] = Math.min(diceSum[3],
                    Math.min(temp[i] + dice[2], temp[i] + dice[3]));
            diceSum[2] = Math.min(diceSum[2], temp[i]);
            diceSum[2] = Math.min(diceSum[2], Math.min(dice[2] + dice[idx[i]], dice[3] + dice[idx[i]]));
        }

        //System.out.println(Arrays.toString(diceSum));
        long result = 0;

        // result += diceSum[1] * (4 * (N-1) * (N-2) + (long)Math.pow(N-2, 2));
        result += diceSum[1] * (5L * (N-2) * (N-2) + 4L * (N-2));

        result += diceSum[2] * (8L * (N-2) + 4);

        result += diceSum[3] * 4L;


        System.out.println(result);











    }
}