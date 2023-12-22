import java.io.*;
import java.util.*;

/*
- 크루스칼 알고리즘
1. 간선을 모두 오름차순으로 배열에 정렬한다.
2. 간선을 하나씩 뽑으면서 사이클이 생기는지 확인한다(union-find를 사용하여 부모를 확인한다)
3. 뽑은 간선의 수가 정점의 수보다 하나 작을 때까지 반복한다.
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
