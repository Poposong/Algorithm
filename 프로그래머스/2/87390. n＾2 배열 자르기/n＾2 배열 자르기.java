import java.io.*;
import java.util.*;
class Solution {
    public int[] solution(int n, long left, long right) {
        int[] answer = new int[(int)(right-left+1)];
        
        int r, c, num;
        for(int i = 0; i<answer.length; i++, left++){
            r = (int)(left/n + 1);
            c = (int)(left%n + 1);
            answer[i] = Math.max(r, c);
        }

        return answer;
    }
}