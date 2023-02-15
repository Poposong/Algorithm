import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
	
	public static int N;
	
	public static int[] parent;
	
	public static List<Integer> findList;
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		String[] str;
		int v1,v2;
		for(int t = 0; t<T; t++) {
			N = Integer.parseInt(br.readLine());
			
			parent = new int[N+1];
			
			findList = new ArrayList<>();
			
			for(int i =0; i<N-1; i++) {
				str = br.readLine().split(" ");
				
				parent[Integer.parseInt(str[1])] = Integer.parseInt(str[0]);
			}
			
			str = br.readLine().split(" ");
			
			v1 = Integer.parseInt(str[0]);
			
			v2 = Integer.parseInt(str[1]);
			
			//System.out.println(Arrays.toString(parent));
			//System.out.println(findParent(v1,v2));
			sb.append(findParent(v1,v2)+"\n");
		}
		System.out.println(sb.toString());
	}
	
	static int findParent(int v1, int v2) {
		int result = 0;
		findList.add(v1);
		while(true) {
			if(parent[v1] == 0) {
				findList.add(v1);
				break;
			}else {
				findList.add(parent[v1]);
				v1 = parent[v1];
			}
		}
		
		while(true) {
			if(findList.contains(v2)) {
				result = v2;
				return result;
			}else
				v2 = parent[v2];
		}
	}

}