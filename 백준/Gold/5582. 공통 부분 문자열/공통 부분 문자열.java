import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static String s1, s2;
	static int[][] map;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		s1 = br.readLine();
		s2 = br.readLine();
		
		map = new int[s1.length()+1][s2.length()+1];
		
		int max= 0;
		for(int i = 1; i<=s1.length(); i++) {
			for(int j = 1; j<=s2.length(); j++) {
				if(s1.charAt(i-1) == s2.charAt(j-1)) {
					map[i][j] = map[i-1][j-1]+1;
					max = Math.max(max, map[i][j]);
				}
			}
		}
		System.out.println(max);
		
		
	}
	

}