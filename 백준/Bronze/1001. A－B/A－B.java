import java.io.*;
import java.util.*;


public class Main {
	
	public static int number[];

	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		String res[] = br.readLine().split(" ");
		System.out.println(Integer.parseInt(res[0])-Integer.parseInt(res[1]));
		
		
		
	}


}
