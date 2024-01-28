import java.io.*;
import java.util.*;

// 간선을 오름차순으로 정렬한다.
// 간선을 하나씩 뽑아서 정점이랑 연결해본다. 간선의 수가 정점의 수 -1 이면 끝낸다.
public class Main {
    static class Node{
        int weight;
        int v1;
        int v2;

        public Node(int weight, int v1, int v2){
            this.weight = weight;
            this.v1 = v1;
            this.v2 = v2;
        }
    }

    static int[] parent;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>(){
            public int compare(Node node1, Node node2){
                return node1.weight-node2.weight;// 무게를 기준으로 오름차순
            }
        });
        for(int i = 0; i<m; i++){
            String[] str = br.readLine().split(" ");
            queue.add(new Node(Integer.parseInt(str[2]), Integer.parseInt(str[0]), Integer.parseInt(str[1])));
        }

        int ans = 0;
        int count = 0;
        parent = new int[n+1]; // 1~n까지
        for(int i = 0; i<n+1; i++){
            parent[i] = i;
        }

        while(!queue.isEmpty()){
            Node node = queue.poll();
            int p1 = findParent(node.v1);
            int p2 = findParent(node.v2);

            if(p1!=p2){
               // System.out.println(node.weight+","+node.v1+","+node.v2);
                if(p1 < p2){
                    parent[p2]=p1;
                }else{
                    parent[p1]=p2;
                }

                ans += node.weight;
                count++;
            }
            if(count == n){
                break;
            }
        }
       // System.out.println(Arrays.toString(parent));
        System.out.println(ans);



    }

    static int findParent(int v){
        if(parent[v] == v){
            return v;
        }
        return parent[v] = findParent(parent[v]);
    }
}