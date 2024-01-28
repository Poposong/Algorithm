import java.io.*;
import java.util.*;

// (이모티콘의 수, 클립보드에 복사된 수)
// 해당 좌표까지 도착한 최소시간을 저장한 후, 해당 좌표에 더 작은 경우만 갈 수 있도록 한다.

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int s = Integer.parseInt(br.readLine());
        int[][] minPath = new int[s+1][s+1]; // [이모티콘의 수][클립보드에 복사된 수]
        
        for(int i = 0; i<s+1; i++){
            Arrays.fill(minPath[i], Integer.MAX_VALUE);
        }

        minPath[1][0] = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{1, 0});
        
        // 이전에 방문한 적이 없는 경우에만 체크한다.
        // BFS의 시간복잡도는 O(v+e)인데, e는 하나의 정점마다 3가지가 나오기 때문에 3v이다. 따라서, O(4v)가 시간복잡도이므로 O(v)라고 할 수 있다.
        while(!queue.isEmpty()){
            int[] data = queue.poll();
            // 1. 화면에 있는 임티 모두 복사해서 클립보드에 넣기
            int idx = data[0];
            if(idx < s+1 && minPath[data[0]][data[1]]+1 < minPath[idx][idx]){
                minPath[idx][idx] = minPath[data[0]][data[1]]+1;
                queue.offer(new int[]{idx, idx});
            }

            // 2. 클립보드에 있는 임티를 화면에 붙여넣기 한다.
            if(data[1] > 0){
               idx = data[0] + data[1];
               if(idx < s+1 && minPath[data[0]][data[1]]+1 < minPath[idx][data[1]]){
                   minPath[idx][data[1]] = minPath[data[0]][data[1]]+1;
                   queue.offer(new int[]{idx, data[1]});
               }
            }

            // 3. 화면에 있는 이모티콘 중 하나를 삭제한다.
            idx = data[0]-1;
            if(idx >= 0){
                if(minPath[data[0]][data[1]]+1 < minPath[idx][data[1]]){
                    minPath[idx][data[1]] = minPath[data[0]][data[1]]+1;
                    queue.offer(new int[]{idx, data[1]});
                }
            }

        }

        int min = minPath[s][0];
        for(int i = 1; i<s+1; i++){
            min = Math.min(min, minPath[s][i]);
        }
        System.out.println(min);
    }
}
