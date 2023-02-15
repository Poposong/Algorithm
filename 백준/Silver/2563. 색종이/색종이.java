
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;


public class Main {

	
	public static boolean[][] paper;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        
        String[] str;
        
        paper = new boolean[100][100];
        
        int count = 0;
        
        int num1, num2;
        for(int i =0 ; i<N; i++) {
        	str = br.readLine().split(" ");
        	num1 = Integer.parseInt(str[0]);
        	num2 = Integer.parseInt(str[1]);
        	for(int x = num1-1; x<num1+9; x++) {
        		for(int y = num2-1; y<num2+9; y++) {
        			if(!paper[x][y]) {
        				count++;
        				paper[x][y] = true;
        			}
        		}
        	}

        }
        
        System.out.println(count);



        
    }
    
}