import java.io.*;
import java.util.*;

/*
[조건]
1. 길의 유지비를 최소로 하고 싶다(이때, 임의의 두 집 A와 B를 연결하는 경로는 항상 존재해야한다. → 각 마을의 집들은 연결 그래프이다)

[정리]
마을을 두 개로 분리한다(이때, 각 마을에서의 유지비는 최소가 되어야 한다)
**/
public class Main {
    static int N, M;
    static class Edge{
        int a;
        int b;
        int c;

        public Edge(int a, int b, int c){
            this.a=a;
            this.b=b;
            this.c=c;
        }
    }

    static Edge[] edgeList;
    static int[] parent;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);

        edgeList = new Edge[M];
        for(int i =0; i<M; i++){
            str = br.readLine().split(" ");
            edgeList[i] = new Edge(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]));
        }

        Arrays.sort(edgeList, new Comparator<Edge>(){
           public int compare(Edge e1, Edge e2){
               return e1.c-e2.c;
           }
        });

        parent=new int[N+1];
        for(int i = 0; i<N+1; i++){
            parent[i]=i;
        }

        int p1, p2, sum = 0, max = 0, cnt = 0;
        for(Edge edge:edgeList){
            p1 = findParent(edge.a);
            p2 = findParent(edge.b);
            if(p1!=p2){
                if(p1<p2){
                    parent[p2] = p1;
                }else{
                    parent[p1] = p2;
                }
                cnt++;
                sum += edge.c;
                max = Math.max(max, edge.c);
            }
            if(cnt == N-1){
                break;
            }
        }
        System.out.println(sum-max);
    }

    static int findParent(int idx){
        if(idx == parent[idx]){
            return parent[idx];
        }
        return findParent(parent[idx]);
    }


}