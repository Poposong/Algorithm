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
            if(i%3 == P[i]){ // 카드를 처음부터 섞지 않아도 돌아가는 경우
                cnt++;
            }
        }

        if(cnt==N){
            System.out.println(0);
            return;
        }

        int[] temp = new int[N];
        int sameCnt, recurCnt;
        cnt=0;
        while(true){
            sameCnt = 0;
            recurCnt = 0;
            for(int i = 0; i<N; i++){
                temp[S[i]] = cards[i];
                if(S[i] == temp[S[i]]){ // 사이클을 체크한다.
                    recurCnt++;
                }
                if(P[cards[i]] == S[i]%3){ // 카드를 올바르게 섞었는지 체크한다.
                    sameCnt++;
                }
            }

            cnt++;

            if(sameCnt == N){
                System.out.println(cnt);
                return;
            }

            if(recurCnt == N){
                System.out.println(-1);
                return;
            }


            for(int i = 0; i<N; i++){ // 섞은 카드를 복사한다.
                cards[i] = temp[i];
            }
        }

    }
}
