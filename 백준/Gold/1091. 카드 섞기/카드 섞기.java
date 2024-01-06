import java.io.*;
import java.util.*;
public class Main {
    static int N;
    static int[] cards, P, S;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cards = new int[N];
        P = new int[N];
        S = new int[N];
        String[] str1 = br.readLine().split(" ");
        String[] str2 = br.readLine().split(" ");

        int cnt = 0;
        for(int i = 0; i<N; i++){
            P[i] = Integer.parseInt(str1[i]);
            S[i] = Integer.parseInt(str2[i]);
            cards[i] = i;
            if(i%3 == P[i]){
                cnt++;
            }
        }

        if(cnt==N){
            System.out.println(0);
            return;
        }



        int[] temp = new int[N];
        cnt=0;
        while(true){

            int sameCheck = 0;
            int recursive = 0;
            for(int i = 0; i<N; i++){

                int idx = S[i];
                int value = cards[i];
                temp[S[i]] = cards[i];
                if(S[i] == temp[S[i]]){
                    recursive++;
                }

                if(P[cards[i]] == S[i]%3){
                    sameCheck++;
                }
            }

            cnt++;

            if(sameCheck == N){
                System.out.println(cnt);
                return;
            }

            if(recursive == N){
                System.out.println(-1);
                return;
            }


            for(int i = 0; i<N; i++){
                cards[i] = temp[i];
            }

//            System.out.println(Arrays.toString(cards));
        }

    }
}