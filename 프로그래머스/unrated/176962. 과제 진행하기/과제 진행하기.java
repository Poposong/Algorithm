// 진행중이던 과제가 끝나는 시각과 새로운 과제를 시작해야하는 시각이 같은 경우 진행중이던 과제는 끝난 것으로 판단합니다.
import java.io.*;
import java.util.*;

class TimeTable{
    String name;
    int restTime;
    
    int startTime;
    
    public TimeTable(String name, int restTime){
        this.name = name;
        this.restTime = restTime;
    }
    
    public TimeTable(String name, int startTime, int restTime){
        this.name = name;
        this.startTime = startTime;
        this.restTime = restTime;
    }
}


class Solution {
    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
        int idx = 0;
        
        int currentTime = 0;
        
        PriorityQueue<TimeTable> schedule = new PriorityQueue<>(new Comparator<TimeTable>(){
            @Override
            public int compare(TimeTable t1, TimeTable t2){
                return t1.startTime - t2.startTime;
            }
        });
        
        Stack<TimeTable> rest = new Stack<>();
        
        String[] str;
        int sum;
        for(String[] plan : plans){
            str = plan[1].split(":");
            sum =  Integer.parseInt(str[0])*60 + Integer.parseInt(str[1]);
            schedule.add(new TimeTable(plan[0],sum,Integer.parseInt(plan[2])));
        }
        
        
        currentTime = schedule.peek().startTime;
        
        TimeTable tt1;
        while(!schedule.isEmpty()){
            tt1 = schedule.remove();
            
            currentTime = tt1.startTime;
            
            int nextStartTime = 0;
            
            if(schedule.isEmpty()){
                nextStartTime = 1440000;
            }else{
                nextStartTime = schedule.peek().startTime;
            }
            
            if(currentTime + tt1.restTime == nextStartTime){
                answer[idx++] = tt1.name;
                currentTime += tt1.restTime;
                continue;
            }else if(currentTime + tt1.restTime > nextStartTime){
              //  System.out.println(tt1.name+"이 rest로 들어감");
                rest.push(new TimeTable(tt1.name, tt1.restTime - (nextStartTime - tt1.startTime)));
                currentTime = nextStartTime;
            }else{
                currentTime += tt1.restTime;
                answer[idx++] = tt1.name;
                while(!rest.isEmpty()){
                    if(currentTime + rest.peek().restTime == nextStartTime){
                        currentTime += rest.peek().restTime;
                        answer[idx++] = rest.pop().name;
                        break;
                    }else if(currentTime + rest.peek().restTime < nextStartTime){
                        currentTime += rest.peek().restTime;
                        String n = rest.pop().name;
                      //  System.out.println(n+"이 rest에서 나옴");
                        answer[idx++] = n;
                    }else{
                        rest.peek().restTime -= (nextStartTime - currentTime);
                        currentTime = nextStartTime;
                        break;
                    }
                }
            }
        }
        
//         while(!rest.isEmpty()){
            
//         }
        
        // while(!schedule.isEmpty()){
        //     System.out.println(schedule.remove().startTime);
        // }
        
   
        
        
        
        return answer;
    }
}