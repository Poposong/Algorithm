class Solution {
    
    
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        int maxValue = 1000*100;
        temperature += 10;
        t1 += 10;
        t2 += 10;
        
        int[][] dp = new int[onboard.length][51]; // -10~0 => 40~50
        
        for(int i = 0; i<onboard.length; i++){
            for(int j = 0; j<51; j++){
                dp[i][j] = maxValue;
            }
        }
        
        int flag = 1; // 난방
        
        if(temperature > t2)
            flag = -1; // 냉방
        
        // onboard[i]가 1이면 O인 경우  
        // onboard[i]가 0이면 X인 경우
        
        dp[0][temperature] = 0;
      
        int standard;
        for(int i = 1; i<onboard.length; i++){
            for(int j = 0; j<51; j++){
                standard = maxValue;
                
                if((onboard[i] == 1 && t1 <= j && j <= t2) || (onboard[i] == 0)){
                    // 에어컨을 틀지 않고 온도가 올라간 경우(off)
                    if(0<= j+flag && j+flag <= 50){
                        standard = Math.min(standard, dp[i-1][j+flag]);
                    }
                    
                    // 에어컨을 틀지 않고 실내온도가 되어서 유지하는 경우
                    if(j == temperature){
                        standard = Math.min(standard, dp[i-1][j]);
                    }
                    
                    // 에어컨을 틀고 이전의 온도보다 낮아진 경우
                    if(0<= j-flag && j-flag <= 50){
                        standard = Math.min(standard, dp[i-1][j-flag] + a);
                    }
                    
                    // 에어컨을 틀고 희망온도라서 유지하는 경우
                    if(0<= j && j<=50){
                        standard = Math.min(standard, dp[i-1][j] + b);
                    }
                }  
                dp[i][j] = standard;
            }
        }
        
        int answer = dp[onboard.length-1][0];
        for(int i = 1; i<51; i++){
            answer = Math.min(answer, dp[onboard.length-1][i]);
        }
        
        return answer;
        
        
    }
}