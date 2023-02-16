import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;


public class Main {
	static class RCS{
		int r;
		int c;
		int s;
		
		public RCS(int r,int c,int s) {
			this.r=r;
			this.c=c;
			this.s=s;
		}
	}
	public static int N,M,K;
	
	public static int[][] number, tempArray;
	
	public static RCS[] rcs_order;
	
	public static boolean[] isSelected;
	
	public static int result = Integer.MAX_VALUE;

	
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringBuilder sb = new StringBuilder();
        
        
        String[] str = br.readLine().split(" ");
        
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        K = Integer.parseInt(str[2]);
        
        number = new int[N][M];
        
        rcs_order = new RCS[K];
        
        int tmp = 0;
        for(int i =0; i<N; i++) {
        	str = br.readLine().split(" ");
        	tmp = 0;
        	for(int j = 0; j<M; j++) 
        		tmp += number[i][j] = Integer.parseInt(str[j]);
        }
        
        for(int i =0; i<K; i++) {
        	str = br.readLine().split(" ");
        	rcs_order[i] = new RCS(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]));
        }
        
        isSelected = new boolean[K];
        
        perm(0, 0, new ArrayList<>());
        
        System.out.println(result);
        
       
    }
    
    static void move(RCS rcs) {
    	int x1 = rcs.r - rcs.s - 1;
    	int y1 = rcs.c - rcs.s - 1;
    	
    	int x2 = rcs.r + rcs.s - 1;
    	int y2 = rcs.c + rcs.s - 1;
    	
    	int temp;
    	while(x1 < x2 && y1 < y2) {
    		temp = tempArray[x1][y2];
    		
    		for(int i = y2-1; i >= y1; i--) {
    			tempArray[x1][i+1] = tempArray[x1][i];
    		}
    		
    		

    		for(int i = x1+1; i<x2+1; i++) {
    			tempArray[i-1][y1] = tempArray[i][y1];
    		}
    		
    		
  
    		
    		for(int i = y1+1; i< y2+1; i++) {
    			tempArray[x2][i-1] = tempArray[x2][i];
    		}
    		
    		
    		

    		
    		for(int i = x2-1; i>=x1; i--) {
    			tempArray[i+1][y2] = tempArray[i][y2];
    		}
    		
    		
    		tempArray[x1+1][y2] = temp;
    		
    		x1++;
    		x2--;
    		
    		y1++;
    		y2--;
    		
    	}
    	
    	
    }
    
    static void perm(int start, int count, ArrayList<Integer> list) {

    	if(count == K) {

    		int min = Integer.MAX_VALUE;
    		
    		tempArray = new int[N][M];
    		
    		for(int i =0; i<N; i++) {
    			for(int j =0; j<M; j++)
    				tempArray[i][j] = number[i][j];
    		}
    		
    		
    		for(int i =0; i<list.size() ;i++) {
    			RCS rcs = rcs_order[list.get(i)];
    			move(rcs);
    		}
    		int rowSum = 0;
    		for(int i =0; i<N; i++) {
    			rowSum = 0;
    			for(int j =0; j<M; j++)
    				rowSum += tempArray[i][j];
    			min = Math.min(min, rowSum);
    		}
    		
    		result = Math.min(result, min);
    		
    	}
    	
    	for(int i = 0 ; i<K; i++) {
    		if(!isSelected[i]) {
    			isSelected[i] = true;
    			list.add(i);
    			perm(i+1, count+1,list);
    			list.remove(list.size()-1);
    			isSelected[i] = false;
    		}
    	}
    }
    
  
    
}