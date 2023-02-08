
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

/*

첫째 줄에 두 정수 L, C가 주어진다. (3 ≤ L ≤ C ≤ 15) 다음 줄에는 C개의 문자들이 공백으로 구분되어 주어진다. 주어지는 문자들은 알파벳 소문자이며, 중복되는 것은 없다.

최소 한 개의 모음(a, e, i, o, u)과 최소 두 개의 자음

4 6
a t c i s w

 * */
public class Main {
	
	public static int L,C;
	
	public static char[] word;
	
	public static boolean[] isSelected;
	
	public static List<String> result = new ArrayList<String>();
	
	public static void main(String[] args) throws Exception {
		

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] str = br.readLine().split(" ");
		L = Integer.parseInt(str[0]);
		C = Integer.parseInt(str[1]);
		

		str = br.readLine().split(" ");
		
		word = new char[C];
		
		isSelected = new boolean[C];
		
		for(int i =0; i<C; i++) {
			char c = str[i].charAt(0);
			word[i] = c;
		}

		Arrays.sort(word);
		
		//System.out.println(word);
		
		perm(0, 0, "");
	
		
		//System.out.println(result);
		
		for(int i =0; i<result.size(); i++)
			sb.append(result.get(i)+"\n");
		
		System.out.println(sb.toString());
		
 	}
	
	static void perm(int start, int cnt, String str) {
		if(cnt == L) {
			if(!result.contains(str)) {
				
				if(isCheck(str)) {
					
					result.add(str);
				}
					
			}
			return;
		}
		for(int i =start; i<C; i++) {
			if(!isSelected[i]) {
				isSelected[i] = true;
				perm(i+1, cnt+1, str+word[i]);
				isSelected[i] = false;
			}
		}
		
	}
	
	static boolean isCheck(String str) {
		int cnt1 = 0, cnt2 = 0;
		for(int i =0; i<str.length(); i++) {
			char c = str.charAt(i);
			if('a' == c || 'e' == c || 'i' == c || 'o' == c || 'u' == c)
				cnt1++;
			else
				cnt2++;
		}
		
		if(cnt1 >= 1 && cnt2 >= 2)
			return true;
		else
			return false;
	}
	
	
}