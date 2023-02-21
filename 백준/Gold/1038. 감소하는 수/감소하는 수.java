import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;


public class Main {
	public static int N;
	public static int[] number = {0,1,2,3,4,5,6,7,8,9};
	public static boolean[] isSelected;
	
	public static int target = 0, current = 0;
	
	public static Long answer = -1L;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine())+1;
		
		isSelected = new boolean[10]; // 0번 ~ 9번 인덱스를 사용한다.
		
		/*if(N < 10)
			System.out.println(N);
		else {
			current = 9;
			target = 1;
			ranking(0, "", 0);
		}*/
		if(N <= 1023) {
			for(int i=1; i<=10; i++) {
				target = i;
				ranking(0, "", 0);
				
				if(answer != -1) 
					break;
				
				if(current > N)
					break;
			}
		}
	
		
		System.out.println(answer);
		
		return;
		
	}
	
	static void ranking(int count, String str, int before) {
		//System.out.println("target : " +target +"," + str.length());
		if(count == target) {
			current++;
			//System.out.println(target +" : "+str +" / "+ current);
			if(current == N) {

				answer = Long.parseLong(str);
				return;
			}
			return;
		}
		for(int i = 0; i<10 ; i++) {
			if(count == 0) {
				ranking(count+1, str+i, i);
			}else {
				if(before > i) {
					ranking(count+1, str + i , i);
				}
			}
		}
	}
	
}