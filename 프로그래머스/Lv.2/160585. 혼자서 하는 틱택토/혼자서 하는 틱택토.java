// 1. O가 X보다 갯수가 항상 더 많거나 같아야 한다.
// 2. 빙고가 없을 때 O,X의 개수가

// 3. 가로, 세로, 대각선으로 빙고가 존재하는지 찾은 후,
// O만 빙고였다면 항상 O의 수가 더 많아야 한다. 
// X만 빙고였다면 O와 X의 수가 같아야 한다. 
// O의 빙고와 X의 빙고 수가 같다면 O가 빙고일 때 멈춰야 했으므로 잘못된 게임판이다.
import java.io.*;
import java.util.*;

class Solution {
    public int solution(String[] board) {
        int answer = -1;
    
        
        // 빙고를 찾는다.
        int firstBingo = 0, secondBingo = 0; // 선공, 후공
        
        
        int firstCnt = 0, secondCnt = 0;
        
        for(int i = 0; i<3; i++){
            // 가로
            if(board[i].equals("OOO")){
                firstBingo++;
            }else if(board[i].equals("XXX")){
                secondBingo++;
            }
            
            // 세로
            char c = board[0].charAt(i);
            if(c != '.' && board[1].charAt(i) == c && board[2].charAt(i) == c){
                if(c == 'O'){
                    firstBingo++;
                }else{
                    secondBingo++;
                }
            }
            
            for(int j = 0; j<3; j++){ // O와 X의 수
                c = board[i].charAt(j);
                if(c != '.'){
                    if(c == 'O'){
                        firstCnt++;
                    }else{
                        secondCnt++;
                    }
                }
            }
        }
        
        // 왼쪽 대각선
        char c = board[0].charAt(0);
        if(c != '.' && c == board[1].charAt(1) && c == board[2].charAt(2)){
            if(c == 'O'){
                firstBingo++;
            }else{
                secondBingo++;
            }
        }
        
        // 오른쪽 대각선
        c = board[0].charAt(2);
        if(c != '.' && c == board[1].charAt(1) && c == board[2].charAt(0)){
            if(c == 'O'){
                firstBingo++;
            }else{
                secondBingo++;
            }
        }
        
        if(!(firstCnt == secondCnt || firstCnt == secondCnt+1)){ // O와 X의 비율이 맞지 않는 경우
            return 0;
        }
        
        if(firstBingo>0){
            if(firstCnt == secondCnt){
                return 0;
            }
        }
        
        if(secondBingo>0){
            if(firstCnt == secondCnt+1){
                return 0;
            }
        }
        
        
        return 1;

        
    }
}