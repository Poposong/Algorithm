package Algorithm;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 도시는 1부터 N까지 존재한다.

public class Main {
    static int N, M; // 도시의 수, 여행 계획에 속한 도시들의 수
    static HashMap<Integer, ArrayList<Integer>> graph;
    static boolean[] seat;
    static boolean[] visited;
    static int count;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        seat = new boolean[N+1];
        visited = new boolean[N+1];

        graph = new HashMap<>();
        for(int i = 0; i<N+1; i++){
            graph.put(i, new ArrayList<Integer>());
        }

        String[] str;
        for(int i = 1; i<N+1; i++){
            str = br.readLine().split(" ");
            for(int j = 0; j<N; j++){
                if(Integer.parseInt(str[j]) == 1){
                    graph.get(i).add(j+1);
                }
            }
        }

        str = br.readLine().split(" ");
        int num = 0;
        for(String s : str){
            num = Integer.parseInt(s);
            if(!seat[num]){
                seat[num] = true;
                count++;
            }
        }

        bfs(num);

        if(count == 0){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }
    }

    static void bfs(int start){
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start] = true;
        count--;

        int vertex;
        while(!queue.isEmpty()){
            vertex = queue.poll();

            for(int value : graph.get(vertex)){
                if(!visited[value]){
                    if(seat[value]){
                        seat[value] = false;
                        count--;
                    }
                    visited[value] = true;
                    queue.offer(value);
                }
            }
        }
    }

}
