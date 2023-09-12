import java.util.*;



class Solution {
    public int solution(int[][] triangle) {
        
        int result[][] = new int[triangle.length][triangle.length];
 
        result[0][0] = triangle[0][0];
        int answer = result[0][0];
        
        for(int i =1; i<triangle.length;i++) {
        	int len = triangle[i].length;
        	for(int j =0; j<len; j++) {
        		if(j == 0)
        			result[i][j] = result[i-1][j] + triangle[i][j];
        		else if(j == len-1) 
        			result[i][j] = result[i-1][j-1] + triangle[i][j];
        		else 
        			result[i][j] = Math.max(result[i-1][j-1] + triangle[i][j], result[i-1][j] + triangle[i][j]);
        		
        		if(i == triangle.length-1)
        			answer = Math.max(result[i][j], answer);
        	}
        }
        return answer;
    }
}