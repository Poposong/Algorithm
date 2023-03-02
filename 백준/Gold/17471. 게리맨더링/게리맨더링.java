import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Main {
	
	public static int N; // 구역의 개수 N
	
	public static int[] population; // 1~N번 정점에서의 인구수를 저장한다. 
	
	public static ArrayList<Integer>[] graph; // 정점과 연결된 정점을 리스트로 저장한다. Ex) graph[1] 에는 1번 정점과 연결된 정점들이 들어가있다.
	
	public static int target; // 1~N-1개의 부분집합을 형성하는 경우에 사용할 변수 Ex) 1~N개의 정점에서 target개의 부분집합을 형성하려는 경우
	
	public static int locationTotal; // 1~N번 구역의 전체 인구수를 저장할 변수
	
	public static boolean[] visited;  // 1~N개의 정점의 방문을 체크할 배열
	
	public static int result = Integer.MAX_VALUE; // 두 선거구의 인구 차이의 최솟값을 출력하는 변수
	
	public static Queue<Integer> queue;
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		population = new int[N+1]; 
		
		visited = new boolean[N+1];
		
		graph = new ArrayList[N+1];
		
		for(int i =0; i<N+1; i++)
			graph[i] = new ArrayList<Integer>();
		
		String[] str = br.readLine().split(" "); // 구역의 인구를 키보드에서 받음
		
		for(int i =0; i<N; i++) {
			population[i+1] = Integer.parseInt(str[i]);
			locationTotal += population[i+1];
		}

		for(int i = 1; i<N+1; i++) {
			str = br.readLine().split(" ");
			for(int j =0; j<Integer.parseInt(str[0]); j++) {
				graph[i].add(Integer.parseInt(str[j+1])); // i번 정점과 연결된 정점을 배열에 넣어준다.
			}
		}

		// 1~N-1개의 부분집합을 형성한다.
		for(int i = 1; i<=N/2; i++) {
			Arrays.fill(visited, false);
			target = i;
			
			int[] vertexList = new int[i];
			Combination(0, 1, 0, vertexList); // Combination(count, start, sum, ArrayList<Integer>());
		}
		
		// 구역을 두 선거구로 나눌 수 없는 경우에는 -1을 반환하고 나눌 수 있는 경우에는 나눈 선거구 차이의 최솟값을 출력한다.
		System.out.println(result == Integer.MAX_VALUE? -1 : result);
	

	}
	
	static void Combination(int count, int start, int sum, int[] vertexList) {
		if(count == target) { // count개의 부분집합을 형성한 경우
	
			if(connectedCheck(vertexList)) { // vertexList에 존재하는 정점들이 모두 연결된 경우
				
				int value = else_connectedCheck(); // vertexList에 존재하지 않는 나머지 정점들이 연결된 정점의 수

				if(count + value == N) { // 연결된 그래프의 정점이 N개와 같은 경우 
					result = Math.min(result, Math.abs(sum - Math.abs((locationTotal-sum)))); // vertexList에 존재하는 정점들의 총 인구수와 vertexList에 존재하지 않는 정점들의 총 인구수 차이의 최솟값을 결과로 반환한다.
				}
			}

			return;
		}
		
		for(int i = start; i<N+1; i++) {

			 if(!visited[i]) {
				 
				 visited[i] = true;
				 
				 vertexList[count] = i;
				 
				 Combination(count+1, i, sum + population[i], vertexList);
				 
				 visited[i] = false;
				 
			 }
		}
		
	}
	
	static boolean connectedCheck(int[] vertexList) {
		
		queue = new LinkedList<>();
		
		boolean[] isSelected = new boolean[N+1];
		
		queue.add(vertexList[0]);
		
		isSelected[vertexList[0]] = true;
		
		int count = 0; 
		
		int num; // 큐에서 뽑은 정점을 임시로 저장할 변수
		while(!queue.isEmpty()) {
			
			num = queue.poll();
			
			count++;
			
			for(int value : graph[num]) {
				if(visited[value] && !isSelected[value]) { // 부분집합에서 뽑힌 원소 && 아직 방문하지 않은 정점인 경우
					
					isSelected[value] = true;
					
					queue.offer(value);
					
				}
			}
		}

		if(count == target) { // 부분집합에서 뽑힌 원소의 수와 큐에 들어갔던 원소의 수가 동일한 경우
			return true;
		}
		
		return false;
	}
	
	// vertexList에 존재하는 정점을 제외한 나머지 정점들이 연결그래프인지 체크하고 연결된 정점의 수를 반환한다.
	static int else_connectedCheck() {
		boolean[] tempVisited = new boolean[N+1];
		
		// tempVisited[]에 visited[]의 값을 복사한다.
		tempVisited = Arrays.copyOf(visited, N+1);
		
		int count = 0; // 연결된 정점의 수를 반환하기 위한 변수를 선언한다.
		
		queue = new LinkedList<>();
		
		// 이전에 방문하지 않은 정점을 큐에 넣어주고 방문 체크를 진행한다.
		for(int i = 1; i<N+1; i++) {
			if(!visited[i]) {
				queue.offer(i);
				
				tempVisited[i] = true;
				
				break;
			}
		}
		
		
		int num;
		while(!queue.isEmpty()) {
			num = queue.poll(); 
			
			count++; // 체크한 연결그래프 정점의 수를 증가시킨다.
			
			for(int value : graph[num]) {
				if(!tempVisited[value]) {
					
					tempVisited[value] = true;
					
					queue.offer(value);
					
				}
			}
		}

		return count;
	}
	
	
	
}