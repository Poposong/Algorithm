package Baekjoon;
import java.io.*;
import java.util.*;

class Nodes{
    int number;
    long weight;

    public Nodes(int number, long weight){
        this.number=number;
        this.weight=weight;
    }
}
public class Main {
    static int v, e;
    static long result;
    static long INF;
    static long[][] minPath;
    static Map<Integer, ArrayList<int[]>> edge;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        v = Integer.parseInt(str[0]);
        e = Integer.parseInt(str[1]);
        INF = Long.MAX_VALUE;
        result = Long.MAX_VALUE;
        edge = new HashMap<>();
        for(int i =0; i<v+1; i++){
            edge.put(i, new ArrayList<>());
        }

        for(int i =0 ; i<e; i++){
            str = br.readLine().split(" ");
            edge.get(Integer.parseInt(str[0])).add(new int[]{
                    Integer.parseInt(str[1]),
                    Integer.parseInt(str[2])
            });
        }

        minPath = new long[v+1][v+1];
        for(int i = 1; i<v+1; i++){
            Arrays.fill(minPath[i], INF);
            minPath[i][i] = 0;
            findMinPath(i);
        }

        for(int i = 1; i<v+1; i++){
            for(int j = 1; j<v+1; j++){
                if(i==j){
                    continue;
                }
                if(minPath[i][j] == INF || minPath[j][i] == INF) {
                    continue;
                }

                result = Math.min(result, minPath[i][j]+minPath[j][i]);
            }
        }

        System.out.println(result == INF ? -1 : result);


    }

    static void findMinPath(int start){
        PriorityQueue<Nodes> queue = new PriorityQueue<>(new Comparator<Nodes>(){
            public int compare(Nodes n1, Nodes n2){
                return (int) (n1.weight - n2.weight);
            }
        });

        queue.offer(new Nodes(start, 0));

        int v1, v2;
        long w1, w2;
        Nodes node;
        while(!queue.isEmpty()){
            node = queue.poll();
            v1 = node.number;
            w1 = node.weight;

            if(w1 > minPath[start][v1]) continue;

            for(int[] value : edge.get(v1)){
                v2 = value[0];
                w2 = value[1];

                if(minPath[start][v2] > minPath[start][v1] + w2){
                    minPath[start][v2] = minPath[start][v1] + w2;
                    queue.offer(new Nodes(v2, minPath[start][v2]));
                }
            }
        }
    }
}
