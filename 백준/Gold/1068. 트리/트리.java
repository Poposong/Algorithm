package Baekjoon;
import java.io.*;
import java.util.*;

/**
 * 1. 루트 노드는 0번째만 될 수 있는 건 아니다.
 * 2. 리프 노드를 만들었을 때 상위 노드가 리프 노드가 될 수 있는지 확인해야 한다.
 * 3. 리프 노드로 하나만 지워졌을 경우도 생각해야 한다.
 * */
public class BJ_1068_트리 {
    static int N, answer;
    static Map<Integer, ArrayList<Integer>> tree;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        answer = N; // 리프노드로 가능한 정점의 수

        tree = new HashMap<>(); // / 부모노드와 자식노드들을 저장할 맵
        for(int i = 0; i<N; i++){
            tree.put(i, new ArrayList<Integer>());
        }

        boolean[] leafNodeCheck = new boolean[N]; // 리프노드를 체크하기 위한 배열
        Arrays.fill(leafNodeCheck, false);

        int rootNode = 0;
        String[] str = br.readLine().split(" ");
        for(int child = 0; child<N; child++){
            int parent = Integer.parseInt(str[child]);
            if(parent == -1){ // 루트 노드를 발견한 경우
                rootNode = child;
                continue;
            }else{ // 부모와 자식을 연결한다.
                tree.get(parent).add(child);
            }

            if(!leafNodeCheck[parent]){ // 부모 노드가 이전에 방문하지 않았다면 리프 노드에서 제외해준다.
                leafNodeCheck[parent] = true;
                answer--;
            }
        }
        // answer의 수가 현재까지 존재하는 리프노드의 수이다.

        int removeNode = Integer.parseInt(br.readLine());
        if(removeNode == rootNode){ // 루트노드가 지우고 싶은 노드이면 리프노드는 0이다.
            System.out.println(0);
            return;
        }

        removeNode(removeNode);

        int num = Integer.parseInt(str[removeNode]); // 지웠을 때 removeNode의 상위 노드가 리프 노드가 되었을 경우를 체크한다.
        if(num != -1 && tree.get(num).size()== 1){
            answer++;
        }

        System.out.println(answer);

    }

    static void removeNode(int vertex){
        if(tree.get(vertex).size() == 0){ // 리프노드가 하나만 있었던 경우에는 지워야 하기 때문에 for문 밖에서 처리한다.
            answer--;
            return;
        }
        for(int data : tree.get(vertex)){
            removeNode(data);
        }
    }
}
