import java.io.*;
import java.util.*;
/**
0<=alp, cop <= 150
1<= problems의 길이 <= 100
[문제] 주어진 모든 문제들을 풀 수 있는 알고력과 코딩력을 얻는 최단시간은?
알고력 : alp
코딩력 : cop
문제의 정보를 담은 2차원 정수 배열 : problems

bfs를 사용한 우선순위큐 (time이 적은 순서대로? 많이 체크한 순서대로?)
time 오름차순, 체크 내림차순

1. 알고력을 올리는 경우
2. 코딩력을 올리는 경우
3. 문제를 푸는 경우

*/

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int answer = 0;
        int N = problems.length, goal_a = 0, goal_c = 0; // 알고력 목표, 코딩력 목표
        for(int i = 0; i<N; i++){
            goal_a = Math.max(goal_a, problems[i][0]);
            goal_c = Math.max(goal_c, problems[i][1]);
        }
        
        // 이미 만들어진 경우
        if(alp >= goal_a && cop >= goal_c){
            return 0;
        }
        // 현재 알고력을 최대 알고력으로 세팅하기
        if(alp > goal_a){
            alp = goal_a;
        }
        // 현재 코딩력을 최대 코딩력으로 세팅하기
        if(cop > goal_c){
            cop = goal_c;
        }

        int[][] dp = new int[goal_a+2][goal_c+2];
        for(int i = 0; i<goal_a+2; i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        
        dp[alp][cop]=0;
        
        for(int i = alp; i<=goal_a; i++){
            for(int j = cop; j<=goal_c; j++){
                // 1. 알고력 올리는 경우
                dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j]+1);
                
                // 2. 코딩력 올리는 경우
                dp[i][j+1] = Math.min(dp[i][j+1], dp[i][j]+1);
                
                // (i, j) 의 코딩력과 알고력을 가진 상태에서 다른 문제들을 사용해서 좌표를 이동할 수 있는지 확인함
                
                for(int[] p : problems){
                    if(i >= p[0] && j>=p[1]){
                        if(i+p[2] > goal_a &&  j+p[3] > goal_c){
                            dp[goal_a][goal_c] = Math.min(dp[goal_a][goal_c], dp[i][j] + p[4]);
                        }else if(i+p[2] > goal_a){
                            dp[goal_a][j+p[3]] = Math.min(dp[goal_a][j+p[3]], dp[i][j] + p[4]);
                        }else if(j+p[3] > goal_c){
                            dp[i+p[2]][goal_c] = Math.min(dp[i+p[2]][goal_c], dp[i][j] + p[4]);
                        }else if(i+p[2] <= goal_a && j+p[3] <= goal_c){
                            dp[i+p[2]][j+p[3]] = Math.min(dp[i+p[2]][j+p[3]], dp[i][j] + p[4]);
                        }
                    }
                }
            }
        }
        return dp[goal_a][goal_c];
    }
}