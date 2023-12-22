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
    static class Node{
        int v;
        int w;

        public Node(int v, int w){
            this.v = v;
            this.w = w;
        }
    }

    static boolean[] visited;
    static List[] edgeList;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);

        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>(){
            public int compare(Node n1, Node n2){
                return n1.w-n2.w;
            }
        });
        
        int a, b, c;
        edgeList = new ArrayList[N+1];
        for(int i = 0; i<N+1; i++){
            edgeList[i] = new ArrayList<Node>();
        }

        for(int i =0; i<M; i++){
            str = br.readLine().split(" ");
            a = Integer.parseInt(str[0]);
            b = Integer.parseInt(str[1]);
            c = Integer.parseInt(str[2]);
            edgeList[a].add(new Node(b, c));
            edgeList[b].add(new Node(a, c));
        }
        // 시작할 정점의 간선들을 우선순위 큐에 넣는다.
        for(int i = 0; i<edgeList[1].size(); i++){
            Node node = (Node)edgeList[1].get(i);
            pq.offer(node);
        }

        visited=new boolean[N+1];
        visited[1] = true;
        Node node;
        int sum = 0, max = 0, cnt = 0;
        while(!pq.isEmpty()){
            node = pq.poll();
            if(!visited[node.v]){
                for(int i = 0; i<edgeList[node.v].size(); i++){
                    Node tempNode = (Node)edgeList[node.v].get(i);
                    if(!visited[tempNode.v]){
                        pq.offer(tempNode);
                    }
                }
                sum += node.w;
                max = Math.max(max, node.w);
                cnt++;
                visited[node.v] = true;
            }

            if(cnt == N-1){
                break;
            }
        }


        System.out.println(sum-max);
    }


}