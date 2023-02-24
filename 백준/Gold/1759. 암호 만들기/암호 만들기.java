import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.plaf.synth.SynthSeparatorUI;

// 자음을 뽑는 메소드를 호출하고 자음을 뽑는 메소드에서 모음을 뽑는 메소드를 호출하는 방식이다.

public class Main {

	public static int L,C;
	
	public static int cnt1, cnt2; // 자음의 수, 모음의 수
	
	public static List<Character> alphabet1, alphabet2; // 자음을 담을 리스트, 모음을 담을 리스트
	
	public static char[] password;
	
	public static boolean[] visited1, visited2; // 자음의 방문 체크, 모음의 방문 체크
	
	public static List<String> result = new ArrayList<>();


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] str = br.readLine().split(" ");
		L = Integer.parseInt(str[0]);
		C = Integer.parseInt(str[1]);
		
		password = new char[L];
		
		str = br.readLine().split(" ");
		
		alphabet1 = new ArrayList<>();
		alphabet2 = new ArrayList<>();
		
		
		for(int i = 0; i<C; i++) {
			char c = str[i].charAt(0);
			// 모음을 담는다.
			if(c == 'a' || c == 'e' ||
					c == 'i' || c == 'o' || c == 'u') {
				alphabet2.add(c);
			}else // 자음을 담는다.
				alphabet1.add(c);
		}
		
		cnt1 = alphabet1.size();
		cnt2 = alphabet2.size();
		
		
		visited1 = new boolean[cnt1];
		visited2 = new boolean[cnt2];
		
		for(int i = 1; i<=L-2; i++) {
			// 모음의 수는 항상 1보다 크거나 같고
			if(2 <= L-i) { // 자음의 수가 2보다 크거나 같으면?
				makePassword(0, L-i); // 자음의 수를 넘긴다.
			}
		}
		
		Collections.sort(result);
		
		for(String s : result)
			System.out.println(s);
		
	}
	
	static void makePassword(int count, int c) { // 자음의 수
		makeConsonant(c,0,0, new ArrayList<Character>()); // 자음 // c1개의 알파벳을 고른다
	}
	
	static void makeVowel(int target, int count, int start, ArrayList<Character> str) {
		if(target == count) {
			// 깊은 복사
			ArrayList<Character> temp = new ArrayList<>();
			
			temp.addAll(str);
			
			Collections.sort(temp);
			
			String s = "";
			for(int i =0; i<temp.size(); i++)
				s += temp.get(i);
			result.add(s);
		
			return;
		}
		for(int i =start; i<cnt2; i++) {
			if(!visited2[i]) {
				visited2[i] = true;

				str.add(alphabet2.get(i));
				
				makeVowel(target, count+1, i+1, str);
				
				str.remove(str.size()-1);
				
				visited2[i] = false;
			}
		}
	}
	
	
	// target개의 자음을 뽑는다.
	static void makeConsonant(int target,int count, int start, ArrayList<Character> str) {
		if(target == count) {
			
			makeVowel(L-target,0,0,str);
			
			return;
		}
	
		for(int i =start; i<cnt1; i++) {
			if(!visited1[i]) {
				visited1[i] = true;
				
				str.add(alphabet1.get(i));

				makeConsonant(target, count+1, i+1 ,str);
				
				str.remove(str.size()-1);
				
				visited1[i] = false;
			}
		}
	}

}