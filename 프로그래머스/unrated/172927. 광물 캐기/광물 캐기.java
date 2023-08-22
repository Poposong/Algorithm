import java.io.*;
import java.util.*;
// 각 곡갱이는 종류에 상관없이 광물 5개를 캔 후에는 더 이상 사용할 수 없다.
// 한 번 사용하기 시작한 곡갱이는 사용할 수 없을 때까지 사용한다.
// 광물은 주어진 순서대로만 캘 수 있다.
// 광산에 있는 모든 광물을 캐거나, 더 이상 사용할 곡갱이가 없을 때까지 광물을 캔다.
class Solution {
    public int[][] mountains = {
        {1, 1, 1},
        {5, 1, 1},
        {25, 5, 1}
    };
    
    public Map<String, Integer> change;

    public int bar1, bar2;
    
    public int result = Integer.MAX_VALUE;
    public int solution(int[] picks, String[] minerals) {
        
        change = new HashMap<>();
        change.put("diamond", 0);
        change.put("iron", 1);
        change.put("stone", 2);
        
        bar1 = picks[0]+picks[1]+picks[2];
        
        
        bar2 = minerals.length/5;
        if(minerals.length % 5 != 0)
            bar2++;
        System.out.println(bar2);
        
        int[] current = new int[3];
        Arrays.fill(current, 0);
        if(bar1 <= bar2){
            choiceNumber(picks, minerals, 0, bar1, current, new ArrayList<Integer>());
        }else{
            choiceNumber(picks, minerals, 0, bar2, current, new ArrayList<Integer>());
        }
        
        
        
        
        
        return result;
    }
    
    public int minValue(int[] current, String[] minerals, ArrayList<Integer> list){
        // int num;
        // int idx = 0;
        // int answer = 0;
        // for(int i = 0; i<3; i++){
        //     num = current[i];
        //     if(num != 0){
        //         for(int j = 0; j<num*5; j++){
        //             if(idx >= minerals.length)
        //                 return answer;
        //             int temp = change.get(minerals[idx++]);
        //             answer += mountains[i][temp];
        //         }
        //     }
        // }
        // return answer;
        
        int answer = 0,idx = 0;
        for(int value : list){
            for(int i = 0; i<5; i++){
                if(idx >= minerals.length){
                    return answer;
                }
                int temp = change.get(minerals[idx++]);
                answer += mountains[value][temp];
            }
        }
        return answer;
        
    }
    
    public void choiceNumber(int[] picks, String[] minerals, int count, int target, int[] current, ArrayList<Integer> list){
        
        if(count == target){
           // System.out.println(current[0]+","+current[1]+","+current[2]);
            //System.out.println(list);
            result = Math.min(result, minValue(current, minerals, list));
            return;
        }
        
        for(int i = 0; i<3; i++){
            if(current[i] < picks[i]){
                current[i]++;
                list.add(i);
                choiceNumber(picks, minerals, count+1, target, current, list);
                list.remove(list.size()-1);
                current[i]--;
            }
        }
        
        
    }
}