import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Main {
	
	public static int N;
	
	public static int[] population;
	
	public static List<Integer>[] graph;
	
	public static int target;
	
	public static int locationTotal;
	
	public static boolean[] visited;
	
	public static int result = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		population = new int[N+1];
		visited = new boolean[N+1];
		
		graph = new List[N+1];
		
		for(int i =0; i<N+1; i++)
			graph[i] = new ArrayList<Integer>();
		
		String[] str = br.readLine().split(" ");
		
		for(int i =0; i<N; i++) {
			population[i+1] = Integer.parseInt(str[i]);
			locationTotal += population[i+1];
		}

		for(int i = 1; i<N+1; i++) {
			str = br.readLine().split(" ");
			for(int j =0; j<Integer.parseInt(str[0]); j++) {
				graph[i].add(Integer.parseInt(str[j+1]));
			}
		}
		
		//for(int i = 1; i<N+1; i++)
		//	System.out.println(graph[i]);
		
		//System.out.println("----");
		
		for(int i = 1; i<N; i++) {
			Arrays.fill(visited, false);
			target = i;
			Combination(0, 1, 0, new ArrayList<Integer>());
			//System.out.println("-----------------------------------------");
		}
		
		System.out.println(result == Integer.MAX_VALUE? -1 : result);
	

	}
	
	static void Combination(int count, int start, int sum, ArrayList<Integer> tempList) {
		if(count == target) {
			//System.out.println(tempList);
			if(connectedCheck(tempList)) {
				
				int value = else_connectedCheck();
				
				//System.out.println("value : "+ value);
				
				if(value + tempList.size() == N) {
					//System.out.println(sum+"/"+ Math.abs((locationTotal-sum)));
					result = Math.min(result, Math.abs(sum - Math.abs((locationTotal-sum))));
				}
			}

			return;
		}
		
		for(int i = start; i<N+1; i++) {

			 if(!visited[i]) {
				 visited[i] = true;
				 tempList.add(i);
				 
				 Combination(count+1, i, sum + population[i], tempList);
				 
				 tempList.remove(tempList.size()-1);
				 visited[i] = false;
			 }
		}
		
	}
	
	static boolean connectedCheck(ArrayList<Integer> tempList) {
		Queue<Integer> queue = new LinkedList<>();
		
		boolean[] isSelected = new boolean[N+1];
		
		queue.add(tempList.get(0));
		isSelected[tempList.get(0)] = true;
		
	//	System.out.println("---");
		int cnt = 0;
		while(!queue.isEmpty()) {
			int num = queue.poll();
			//System.out.print(num+" ");
			cnt++;
			
			for(int value : graph[num]) {
				if(!isSelected[value] && visited[value]) {
					isSelected[value] = true;
					queue.offer(value);
				}
			}
		}
		//System.out.println("---");
		
		//System.out.println("cnt : "+cnt);
		if(cnt == tempList.size()) {
			//System.out.println(tempList+"는 연결됨");
			return true;
		}
		return false;
	}
	
	
	static int else_connectedCheck() {
		boolean[] tempVisited = new boolean[N+1];
		
		for(int i =1; i<N+1; i++)
			tempVisited[i] = visited[i];
		
		int count = 0;
		Queue<Integer> queue = new LinkedList<>();
		for(int i = 1; i<N+1; i++) {
			if(!visited[i]) {
				queue.offer(i);
				
				tempVisited[i] = true;
				
				break;
			}
		}
		
		
		
		//System.out.println("--");
		int num;
		while(!queue.isEmpty()) {
			num = queue.poll();
			
			//System.out.print(num+" ");
			count++;
			
			for(int value : graph[num]) {
				if(!tempVisited[value]) {
					tempVisited[value] = true;
					queue.offer(value);
				}
			}
		}
		//System.out.println("\n--");
		return count;
	}
	
	
	
}