import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.regex.Pattern;

// 키보드에서 받는 문자열을 오름차순으로 정렬한다.

// 문자열에서 

public class Main {
	static int T, idx; 
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		
		Pattern P = Pattern.compile("(100+1+|01)+");
		
		while(idx++ < T) {
			if(P.matcher(br.readLine()).matches()) {
				sb.append("YES").append("\n");
			}else {
				sb.append("NO").append("\n");
			}
		}
		
		System.out.println(sb.toString());
		
		
		
	}

}