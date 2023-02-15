
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/*
첫째 줄에 배열의 크기 N, M과 수행해야 하는 연산의 수 R이 주어진다.

둘째 줄부터 N개의 줄에 배열 A의 원소 Aij가 주어진다.

마지막 줄에는 수행해야 하는 연산이 주어진다. 연산은 공백으로 구분되어져 있고, 문제에서 설명한 연산 번호이며, 순서대로 적용시켜야 한다.

 * */

public class Main {

	public static int N,M,R;
	
	public static int[][] number;
	
	public static int[][] result;
	
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringBuilder sb = new StringBuilder();
        
        String[] str = br.readLine().split(" ");
        
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        R = Integer.parseInt(str[2]);
        
        number = new int[N][M];
        
        for(int i = 0; i<N; i++) {
        	str = br.readLine().split(" ");
        	for(int j =0; j<M; j++) {
        		number[i][j] = Integer.parseInt(str[j]);
        	}
        }
        
        str = br.readLine().split(" ");
        

        for(int i =0; i<R; i++) {
        	 menuBoard(Integer.parseInt(str[i]));
        }
        
        
        for(int i =0; i<N; i++) {
        	for(int j =0; j<M; j++)
        		sb.append(result[i][j]+" ");
        	sb.append("\n");
        }
        
        System.out.println(sb.toString());
        
    }
    
    static void menuBoard(int menu) {
    	
    	result = new int[N][M];
    	
    	int row, col;

    	switch(menu) {
    	case 1:
    		result = new int[N][M];
    		for(int i = 0; i<N; i++) {
    			for(int j = 0; j<M; j++) 
    				result[N-1-i][j] = number[i][j]; 
    		}
    		break;
    	case 2:
    		result = new int[N][M];
    		for(int j =0; j<M; j++) {
    			for(int i =0; i<N; i++) {
    				result[i][M-1-j] = number[i][j];
    			}
    		}
    		break;
    	case 3:
    		result = new int[M][N];
    		for(int i =0; i<N; i++) {
    			for(int j =0; j<M; j++) {
    				result[j][N-1-i] = number[i][j];
    			}
    		}
    		int temp = N;
    		N = M;
    		M = temp;
    		break;
    	case 4:
    		result = new int[M][N];
    		for(int j =0; j<M; j++) {
    			for(int i =0; i<N; i++) {
    				result[M-1-j][i] = number[i][j];
    			}
    		}
    		temp = N;
    		N = M;
    		M = temp;
    		break;
    	case 5:
    		result = new int[N][M];
    		row = N/2;
    		col = M/2;
    		//System.out.println(row+"/"+col);
    		move(0, row, 0, col, 0, col);
    		move(0, row, col, 2*col, row, 0);
    		move(row, row*2, col, col*2, 0, -col);
    		move(row, row*2, 0, col, -row, 0);
    		break;
    	case 6:
    		result = new int[N][M];
    		row = N/2;
    		col = M/2;
    		//System.out.println(row+"/"+col);
    		move(0, row, 0, col, row,0);
    		move(row, row*2, 0, col, 0, col);
    		move(row, row*2, col, col*2, -row, 0);
    		move(0, row, col, col*2, 0, -col);
    		break;
    	}
    	
    	number = new int[N][M];
    	
    	for(int i =0; i<result.length; i++) {
    		for(int j =0; j<result[i].length ;j++) {
    			number[i][j] = result[i][j];
    		}
    	}
    	
    	return;
    }
    
    static void move(int x1, int x2, int y1, int y2, int t1, int t2) {
    	
    	for(int i = x1 ; i<x2; i++) {
    		for(int j = y1 ; j< y2; j++) {
    			result[i+t1][j+t2] = number[i][j]; 
    		}
    	}
    }
    
}