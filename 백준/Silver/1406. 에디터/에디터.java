
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;

/*
L	커서를 왼쪽으로 한 칸 옮김 (커서가 문장의 맨 앞이면 무시됨)
D	커서를 오른쪽으로 한 칸 옮김 (커서가 문장의 맨 뒤이면 무시됨)
B	커서 왼쪽에 있는 문자를 삭제함 (커서가 문장의 맨 앞이면 무시됨)
	삭제로 인해 커서는 한 칸 왼쪽으로 이동한 것처럼 나타나지만, 실제로 커서의 오른쪽에 있던 문자는 그대로임
P $	$라는 문자를 커서 왼쪽에 추가함 

abcd
3
P x
L
P y
*/

public class Main {
	
	public static List<Character> word;
	
	public static int cursor = 0;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		word = new LinkedList<>();
		
		String s = br.readLine();
		
		for(int i = 0; i<s.length(); i++)
			word.add(s.charAt(i));	
		
		int n = Integer.parseInt(br.readLine());
		
		char c;
		String[] str;
		
		ListIterator<Character> iter = word.listIterator();
		
		while(iter.hasNext())
			iter.next();
		
		
		
		for(int i =0; i<n; i++) {
			str = br.readLine().split(" ");
			
			if(str[0].equals("P")) { // P $	$라는 문자를 커서 왼쪽에 추가함 
				c = str[1].charAt(0);
				iter.add(c);
			}else if(str[0].equals("L")) { // L	커서를 왼쪽으로 한 칸 옮김 (커서가 문장의 맨 앞이면 무시됨)	
				if(iter.hasPrevious()) { // 왼쪽에 값이 있다면?
					iter.previous();
				}
			}else if(str[0].equals("D")) { // D	커서를 오른쪽으로 한 칸 옮김 (커서가 문장의 맨 뒤이면 무시됨)
				if(iter.hasNext()) {
					iter.next();
				}
			}else if(str[0].equals("B")) { // B	커서 왼쪽에 있는 문자를 삭제함 (커서가 문장의 맨 앞이면 무시됨), 삭제로 인해 커서는 한 칸 왼쪽으로 이동한 것처럼 나타나지만, 실제로 커서의 오른쪽에 있던 문자는 그대로임
				if(iter.hasPrevious()) {
					iter.previous();
					iter.remove();
				}
			}
		}
		
		for(char ch : word) {
			sb.append(ch+"");
		}
		
		System.out.println(sb.toString());
		
		
		
		
		
		
	}

}
