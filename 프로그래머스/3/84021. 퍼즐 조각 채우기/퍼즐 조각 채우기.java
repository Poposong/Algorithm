import java.io.*;
import java.util.*;

/**
1. board, table에서 조각들의 정보를 가져오는 bfs 함수
2. board의 조각 하나씩 table의 조각을 돌려보며 맞춰볼 함수
3. 조각을 돌리는 함수
*/

class Solution {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    // board의 빈칸 조각을 저장한다.
    static List<List<int[]>> boardList = new ArrayList<>();
    
    // table의 조각 정보를 저장한다.
    static List<List<int[]>> tableList = new ArrayList<>();
    
    // bfs를 위한 방문배열
    static boolean[][] visitedBoard;
    static boolean[][] visitedTable;
    
    static int result = 0; // 답을 저장할 변수
    
    static int size; // board, table의 크기 저장
    
    static int answer;
    
    public int solution(int[][] game_board, int[][] table) {
        size = game_board.length;
        
        visitedBoard = new boolean[size][size];
        visitedTable = new boolean[size][size];
        
        // 1. board, table에서 조각들의 정보를 가져오는 bfs 함수
        for(int i = 0; i<size; i++){
            for(int j = 0; j<size; j++){
                if(game_board[i][j] == 0 && !visitedBoard[i][j]){
                    bfs(game_board, 0, i, j, boardList, visitedBoard);
                }
                
                if(table[i][j] == 1 && !visitedTable[i][j]){
                    bfs(table, 1, i, j, tableList, visitedTable);
                }
            }
        }
        
        // 2. board의 조각 하나씩 table의 조각을 돌려보며 맞춰볼 함수
        checkSegment(boardList, tableList);
        
        return answer;
    }
    
    static void bfs(int[][] map, int trigger, int startX, int startY, List<List<int[]>> list, boolean[][] visited){
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{startX, startY});
        visited[startX][startY] = true;
        
        List<int[]> segmentInfo = new ArrayList<>();
        segmentInfo.add(new int[]{0, 0});
        
        while(!queue.isEmpty()){
            int[] current = queue.poll();
            int currentX = current[0];
            int currentY = current[1];
            
            for(int i = 0; i<4; i++){
                int nextX = currentX + dx[i];
                int nextY = currentY + dy[i];
                
                if(nextX < 0 || nextY < 0 || nextX > size-1 || nextY > size -1) continue;
                
                if(visited[nextX][nextY] || map[nextX][nextY] != trigger) continue;
                
                visited[nextX][nextY] = true;
                queue.offer(new int[]{nextX, nextY});
                segmentInfo.add(new int[]{
                   nextX - startX, nextY - startY 
                });
            }
        }
        
        list.add(segmentInfo);
    }
    
    static void checkSegment(List<List<int[]>> boardList, List<List<int[]>> tableList){
        boolean[] completedTableList = new boolean[tableList.size()];
        
        for(int i = 0; i<boardList.size(); i++){
            for(int j = 0; j<tableList.size(); j++){
                if(!completedTableList[j] && boardList.get(i).size() == tableList.get(j).size()){
                    boolean isCorrect = rotateCheck(boardList.get(i), tableList.get(j));
                    
                    // 하나의 board 칸에 맞는 table 조각을 찾은 경우
                    if(isCorrect){
                        answer += tableList.get(j).size();
                        completedTableList[j] = true;
                        break;
                    }
                }
            }
        }
    }
    
    static boolean rotateCheck(List<int[]> currentBoardSeg, List<int[]> currentTableSeg){
        
        // x좌표 오름차순, y좌표 오름차순
        Collections.sort(currentBoardSeg, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                if(a[0] == b[0]){
                    return a[1]-b[1];
                }
                return a[0]-b[0];
            }
        });
        // 0, 90, 180, 270도로 회전
        for(int i = 0; i<4; i++){
            Collections.sort(currentTableSeg, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                if(a[0] == b[0]){
                    return a[1]-b[1];
                }
                return a[0]-b[0];
            }
        });
            
            int baseX = currentTableSeg.get(0)[0];
            int baseY = currentTableSeg.get(0)[1];
            
            for(int j = 0; j<currentTableSeg.size(); j++){
                currentTableSeg.get(j)[0] -= baseX;
                currentTableSeg.get(j)[1] -= baseY;
            }
            
            boolean isCorrect = true;
            
            int[] tableSeg, boardSeg;
            for(int j = 0; j<currentTableSeg.size(); j++){
                tableSeg = currentTableSeg.get(j);
                boardSeg = currentBoardSeg.get(j);
                
                if(tableSeg[0] != boardSeg[0] || tableSeg[1] != boardSeg[1]){
                    isCorrect = false;
                    break;
                }
            }
            
            if(!isCorrect){
                // 맞지 않는 경우
                // 90도로 회전
                for(int j = 0; j<currentTableSeg.size(); j++){
                    int temp = currentTableSeg.get(j)[0];
                    currentTableSeg.get(j)[0] = currentTableSeg.get(j)[1];
                    currentTableSeg.get(j)[1] = (-1)*temp;
                }
            }else{
                return true;
            }
            
        }
        
        return false;
    }
}