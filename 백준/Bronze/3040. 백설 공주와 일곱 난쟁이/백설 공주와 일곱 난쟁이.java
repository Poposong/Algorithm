import java.io.*;
import java.util.*;

/*
8
6
5
1
37
30
28
22
36
 * */

public class Main {
	
	public static int number[];

	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		
		number = new int[9];
		
		for(int i =0; i<9; i++) 
			number[i] = Integer.parseInt(br.readLine());
		
	
		
		for(int i =0; i<8; i++) {
			String result = perm(i);
			if(result != "") {
				System.out.println(result);
				break;
			}
		}
		
		
	}
	
	public static String perm(int num) {
		int sum=0;
		String result = "";
		for(int i = num+1; i<9; i++) {
			sum=0; result="";
			for(int j = 0; j<9 ; j++) {
				if(i != j && num != j) {
					//System.out.println("J : "+ j);
					sum += number[j];
					result += number[j]+"\n";
				}
			}
			if(sum==100)
				return result;		
		}
		return "";
	}

}
