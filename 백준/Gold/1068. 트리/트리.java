import java.io.*;
import java.util.*;
public class Main {
    static int N, answer;

    static boolean[] leafNodeCheck;
    static Map<Integer, ArrayList<Integer>> tree;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        answer = N;
        tree = new HashMap<>();
        for(int i = 0; i<N; i++){ // 부모노드와 자식노드들을 저장할 맵
            tree.put(i, new ArrayList<Integer>());
        }
        leafNodeCheck = new boolean[N]; // 리프노드를 체크하기 위한 배열
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

       // System.out.println(answer);

     //   System.out.println(Arrays.toString(leafNodeCheck));

        int removeNode = Integer.parseInt(br.readLine());
      //  System.out.println(removeNode+","+rootNode);
        if(removeNode == rootNode){
            System.out.println(0);
            return;
        }

        removeNode(removeNode);

        int num = Integer.parseInt(str[removeNode]);
        if(num != -1 && tree.get(num).size()== 1){
            answer++;
        }



        System.out.println(answer == 0 ? 1 : answer);

    }

    static void removeNode(int vertex){
        if(tree.get(vertex).size() == 0){
            answer--;
            return;
        }
        for(int data : tree.get(vertex)){
            removeNode(data);
        }
    }
}