import java.io.*;
import java.util.*;
public class Main {
    static boolean[][] visited;
    static boolean find;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int A = Integer.parseInt(str[0]);
        int B = Integer.parseInt(str[1]);
        int C = Integer.parseInt(str[2]);

        visited = new boolean[1501][1501];
        if((A+B+C)%3 != 0){
            System.out.println(0);
            return;
        }

        setState(A, B, true);

        calculator(A,B,C);

        System.out.println(find? 1 : 0);

        return;


    }

    static void calculator(int A, int B, int C){
        if(find){
            return;
        }

        if(A==B && B==C){
            find=true;
            return;
        }

        int idx;
        if(A!=B){
            if(A<B){
                if(!visited[A+A][B-A] && !visited[B-A][A+A]){
                    setState(A+A, B-A, true);
                    calculator(A+A, B-A, C);
                }
            }else{
                if(!visited[A-B][B+B] && !visited[B+B][A-B]){
                    setState(A-B, B+B, true);
                    calculator(A-B, B+B, C);
                }
            }
        }

        if(A!=C){
            if(A<C){
                if(!visited[A+A][B] && !visited[B][A+A]){
                    setState(A+A, B, true);
                    calculator(A+A, B, C-A);
                }
            }else{
                if(!visited[A-C][B] && !visited[B][A-C]){
                    setState(A-C, B, true);
                    calculator(A-C, B, C+C);
                }
            }
        }

        if(B!=C){
            if(B<C){
                if(!visited[A][B+B] && !visited[B+B][A]){
                    setState(A, B+B, true);
                    calculator(A, B+B, C-B);
                }
            }else{
                if(!visited[A][B-C] && !visited[B-C][A]){
                    setState(A, B-C, true);
                    calculator(A, B-C, C+C);
                }
            }
        }



    }

    static void setState(int A, int B, boolean state){
        visited[A][B] = state;
        visited[B][A] = state;
    }

    static boolean getState(int A, int B){
        if(visited[A][B] || visited[B][A]){
            return false;
        }
        return true;
    }


}