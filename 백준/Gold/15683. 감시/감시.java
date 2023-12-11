package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

// CCTV의 좌표와 어떤 CCTV인지 모두 구한다. ex) (2,4)에 있는 3번 CCTV
// 조합으로 각 자리에서 해당 방향으로 모두 X하기
// 시간복잡도
// O(N*M) + O(4^8 * (4*8)) => CCTV는 최대 8개이고 1<= N, M <=8이고 각 CCTV에서 최대 방향의 수는 4이다.
// 따라서, O(4^8 * (4*8)) 이다.
class Node{
    int cctvNumber;
    int x;
    int y;

    public Node(int cctvNumber, int x, int y){
        this.cctvNumber = cctvNumber;
        this.x = x;
        this.y = y;
    }
}

public class BJ_15683_감시 {
    static int N, M, zero;
    static int[][] map;
    static boolean[][] visited;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static ArrayList<Node> nodeList;

    static int[] choiceNumber;
    static HashMap<Integer, HashMap<Integer, ArrayList<Integer>>> cctvList;

    static int count;

    static int[] cctvSize = {-1, 4, 2, 4, 4, 1}; // 해당 값 만큼의 경우의 수가 존재한다.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        count = Integer.MIN_VALUE;

        nodeList = new ArrayList<>();

        cctvList = new HashMap<>();
        cctvList.put(1, new HashMap<>());
        cctvList.get(1).put(0, new ArrayList<>(Arrays.asList(0)));
        cctvList.get(1).put(1, new ArrayList<>(Arrays.asList(1)));
        cctvList.get(1).put(2, new ArrayList<>(Arrays.asList(2)));
        cctvList.get(1).put(3, new ArrayList<>(Arrays.asList(3)));


        cctvList.put(2, new HashMap<>());
        cctvList.get(2).put(0, new ArrayList<>(Arrays.asList(0, 2))); // 2번 CCTV는 0, 2번 방향으로 가는 경우가 있다.
        cctvList.get(2).put(1, new ArrayList<>(Arrays.asList(1, 3)));

        cctvList.put(3, new HashMap<>());
        cctvList.get(3).put(0, new ArrayList<>(Arrays.asList(0, 1)));
        cctvList.get(3).put(1, new ArrayList<>(Arrays.asList(1, 2)));
        cctvList.get(3).put(2, new ArrayList<>(Arrays.asList(2, 3)));
        cctvList.get(3).put(3, new ArrayList<>(Arrays.asList(3, 0)));// 3번 CCTV는 3, 0번 방향으로 가는 경우가 있다.

        cctvList.put(4, new HashMap<>());
        cctvList.get(4).put(0, new ArrayList<>(Arrays.asList(1, 2, 3)));
        cctvList.get(4).put(1, new ArrayList<>(Arrays.asList(0, 2, 3)));
        cctvList.get(4).put(2, new ArrayList<>(Arrays.asList(0, 1, 3)));
        cctvList.get(4).put(3, new ArrayList<>(Arrays.asList(0, 1, 2)));

        cctvList.put(5, new HashMap<>());
        cctvList.get(5).put(0, new ArrayList<>(Arrays.asList(0, 1, 2, 3)));

        String[] str;

        str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);

        map = new int[N][M];
        visited = new boolean[N][M];
        for(int i = 0; i<N; i++){
            str = br.readLine().split(" ");
            for(int j = 0; j<M; j++){
                map[i][j] = Integer.parseInt(str[j]);
                if(map[i][j] == 0){
                    zero++; // 0의 수
                }else if(map[i][j] != 6){ // 1~5
                    nodeList.add(new Node(map[i][j], i, j));
                }
            }
        }

        choiceNumber = new int[nodeList.size()];

        choiceNumberArray(0);

        System.out.println(zero-count);

    }
    // 한 쪽으로 거리를 넓히는 경우

    static void choiceNumberArray(int idx){
        if(idx == nodeList.size()){
            // 방문 배열 리셋
            for(int i = 0; i<N; i++){
                Arrays.fill(visited[i], false);
            }

            int sum = 0;
            for(int i =0; i<nodeList.size(); i++){
                Node node = nodeList.get(i);
                for(int dir : cctvList.get(node.cctvNumber).get(choiceNumber[i])){
                    sum += goStreet(node.x, node.y, dir);
                }
            }

            count = Math.max(count, sum);

            return;
        }

        Node node = nodeList.get(idx);
        for(int i =0; i<cctvSize[node.cctvNumber]; i++){
            choiceNumber[idx] = i;
            choiceNumberArray(idx+1);
        }
    }

    static int goStreet(int x, int y, int dir){
        int num = 0;

        int nx = x + dx[dir], ny = y + dy[dir];
        while(0<= nx && nx < N && 0 <= ny && ny < M){
            if(map[nx][ny] == 6){ // 벽
                return num;
            }

            if(map[nx][ny] == 0 && !visited[nx][ny]){
                visited[nx][ny] = true;
                num++;
            }

            nx += dx[dir];
            ny += dy[dir];
        }

        return num;
    }
}
