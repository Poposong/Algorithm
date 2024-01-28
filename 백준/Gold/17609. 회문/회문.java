import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        while(t-- != 0){
            String str = br.readLine();
            int res = frontAndBackIsSame(str);
            // System.out.println(res);
            sb.append(res).append("\n");
            // 회문 : 0, 유사회문 : 1, 둘 다 아님 : 2
        }

        System.out.println(sb.toString());
    }

    public static int frontAndBackIsSame(String str){
        int ans = 0; // 회문이다.
        int front = 0, back = str.length()-1;
        while(front <= back){
            if(str.charAt(front) != str.charAt(back)){
                if(ans == 0){ // 회문에서 유사회문으로
                    // 왼쪽에서 하나 제거했을 때 or 오른쪽에서 하나 제거했을 때
                    boolean res1 = isPossible(str, front, back-1);
                    boolean res2 = isPossible(str, front+1, back);
                    if(res1 || res2){ // 하나라도 유사회문이 가능한 경우
                        return 1;
                    }else{
                        return 2;
                    }
                }else if(ans == 1){
                    return 2; // 회문X, 유사회문X
                }
            }
            front++;
            back--;
        }

        return 0;
    }

    //
    public static boolean isPossible(String str, int front, int back){
        while(front<=back){
            if(str.charAt(front) != str.charAt(back)){
                return false;
            }
            front++;
            back--;
        }
        return true;
    }
}