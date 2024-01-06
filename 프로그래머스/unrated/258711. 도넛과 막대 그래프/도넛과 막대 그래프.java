import java.io.*;
import java.util.*;

// 생성된 그래프 : 나가는 정점이 2개 이상이며 들어오는 정점은 존재하지 않는다.

// 막대 모양 그래프 : 들어오는 간선이 없는 정점이 하나 존재하고, 나가는 간선이 없는 정점이 하나 존재한다(두 정점은 같을 수 있다.)

// 도넛 모양 그래프 : 모든 정점이 나가는 간선과 들어오는 간선을 가진다.

// 8자 모양 그래프 : 모든 정점이 나가는 간선과 들어오는 간선을 가지지만 하나의 장점은 들어오고 나가는 간선이 2개씩 존재한다.

class Solution {
    static int vertexCount;
    static Map<Integer, ArrayList<Integer>> map;
    static int[] answer;
    static int[][] inOut;
    public int[] solution(int[][] edges) {
        answer = new int[4];
        
        map = new HashMap<>();
        for(int[] v : edges){
            if(!map.containsKey(v[0])){
                map.put(v[0], new ArrayList<Integer>());
            }
            map.get(v[0]).add(v[1]);
            vertexCount = Math.max(vertexCount, Math.max(v[0],v[1]));
        }
        
        inOut = new int[2][vertexCount+1]; // 0번째 행은 in, 1번째 행은 out
        
        
        for(int i = 1; i<vertexCount+1; i++){
            if(!map.containsKey(i)){
                map.put(i, new ArrayList<Integer>());
            }
        }
        
         for(int i = 1; i<vertexCount+1; i++){
            inOut[1][i] += map.get(i).size();
            for(int next : map.get(i)){
                inOut[0][next]++;
            }
        }
        
        
        int totalCount = 0;
        int bar = 0;
        int loop = 0;
        for(int i = 1; i<vertexCount+1; i++){
            if(inOut[0][i] == 0 && inOut[1][i]>=2){
                answer[0] = i;
                totalCount = inOut[1][i];
                
                for(int next : map.get(answer[0])){
                    inOut[0][next]--;
                }
            }
        }
        
        int cnt = 0;
        for(int i = 1; i<vertexCount+1; i++){
            if(i == answer[0]){
                continue;
            }
            if(inOut[0][i] == 0 && inOut[1][i] == 0){
                bar++;
            }else if(inOut[0][i] == 0 || inOut[1][i] == 0){
                bar++;
                cnt++;
            }else if(inOut[0][i] == 2 && inOut[1][i] == 2){
                loop++;
            }
        }
        
        cnt /= 2;
        
        bar -= cnt;
        
    
        
        // 도넛,막대,8자
        answer[1] = totalCount - bar - loop;
        answer[2] = bar;
        answer[3] = loop;
        
        
        // System.out.println(bar+"/"+ loop+" / "+ totalCount);


        
        
        
        return answer;
    }
}