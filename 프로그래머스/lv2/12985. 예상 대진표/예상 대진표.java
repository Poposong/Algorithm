import java.util.*;

class Solution{
    public int solution(int n, int a, int b){
        int answer = 0;
        Queue<Integer> Q = new LinkedList<Integer>();
        for(int i =1; i<=n ;i++)
        	Q.add(i);

        n = n/2;
        boolean ischeck = false;
        while(n != 0) {
        	for(int i=0; i<n; i++) {
        		int n1 = Q.poll();
        		int n2 = Q.poll();
        		if((n1 == a && n2 == b) || (n1 == b && n2 == a)) {
        			ischeck = true;
        			break;
        		}else if(n2 == a || n2 == b) {
        			Q.add(n2);
        		}else
        			Q.add(n1);
        	}
        	
        	answer++;
        	if(ischeck == true)
        		break;
        	n = n/2;
        }
  
        return answer;
    }
}