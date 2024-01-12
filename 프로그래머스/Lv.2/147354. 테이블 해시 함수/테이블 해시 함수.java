import java.io.*;
import java.util.*;

// 
class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = -1;
        Arrays.sort(data, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                if(a[col-1] == b[col-1]){
                    return b[0] - a[0]; // 첫 번째 컬럼의 값을 기준으로 내림차순 정렬
                }
                return a[col-1] - b[col-1]; // col-1 번째 컬럼의 값을 기준으로 오름차순 정렬
            }
        });
        
        
        
        for(int i = row_begin-1; i<row_end; i++){
            int sum = 0;
            for(int j = 0; j<data[0].length; j++){
                sum += data[i][j]%(i+1);
            }
         //   System.out.println("sum : "+sum);
            if(answer == -1){
                answer = sum;
          //      System.out.println(answer);
            }else{
                answer = answer ^ sum;
          //      System.out.println(answer);
            }
        }
        return answer;
    }
}