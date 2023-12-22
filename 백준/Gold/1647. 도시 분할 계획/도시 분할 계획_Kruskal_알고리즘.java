package Baekjoon;
import java.io.*;
import java.util.*;

/*
- 크루스칼 알고리즘
1. 간선을 모두 오름차순으로 배열에 정렬한다.
2. 간선을 하나씩 뽑으면서 사이클이 생기는지 확인한다(union-find를 사용하여 부모를 확인한다)
3. 뽑은 간선의 수가 정점의 수보다 하나 작을 때까지 반복한다.
**/
public class BJ_1647_도시분할계획_kruskal알고리즘 {
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
