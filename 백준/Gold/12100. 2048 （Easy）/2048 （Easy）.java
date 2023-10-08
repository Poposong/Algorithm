import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
	static int N,r,c;
	static int[][] graph;
	static int maxNumber = -1;
	static Queue<Integer> queue;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new int[N][N];
		
		String[] str;
		for(int i =0; i<N; i++) {
			str = br.readLine().split(" ");
			for(int j = 0; j<N; j++) {
				graph[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		queue = new ArrayDeque<>();

		
		calculator(graph, 0);
		
		System.out.println(maxNumber);
		
		
	}
	
	static void calculator(int[][] map, int count) {
		
		if(count ==5)
			return;
		// 상
		int[][] newMap = new int[N][N];
		
		for(int i = 0; i<N; i++) {
			Arrays.fill(newMap[i], 0);
		}
		for(c = 0; c<N; c++) {
			r = 0;
			queue.clear();
			for(int j = 0; j<N; j++) {
				if(map[j][c] != 0) {
					if(queue.isEmpty()) {
						queue.offer(map[j][c]);
					}else {
						int number = queue.poll();
						if(number == map[j][c]) {
							newMap[r][c] = number*2;
						}else {
							newMap[r][c] = number;
							queue.offer(map[j][c]);
						}
						if(count == 4) {
							maxNumber = Math.max(maxNumber, newMap[r][c]);
						}
						r++;
					}
				}
			}
			if(!queue.isEmpty()) {
				newMap[r][c] = queue.poll();
				if(count == 4) {
					maxNumber = Math.max(maxNumber, newMap[r][c]);
				}
			}
		}
		
		calculator(newMap, count+1); // 위로 움직임
		
		// 하
		for(int i = 0; i<N; i++) {
			Arrays.fill(newMap[i], 0);
		}
		queue.clear();
		for(c = 0; c<N; c++) {
			r = N-1;
			queue.clear();
			for(int j = N-1; j>=0; j--) {
				if(map[j][c] != 0) {
					if(queue.isEmpty()) {
						queue.offer(map[j][c]);
					}else {
						int number = queue.poll();
						if(number == map[j][c]) {
							newMap[r][c] = number*2;
						}else {
							newMap[r][c] = number;
							queue.offer(map[j][c]);
						}
						if(count == 4) {
							maxNumber = Math.max(maxNumber, newMap[r][c]);
						}
						r--;
					}
				}
			}
			if(!queue.isEmpty()) {
				newMap[r][c] = queue.poll();
				if(count == 4) {
					maxNumber = Math.max(maxNumber, newMap[r][c]);
				}
			}
		}
		
		calculator(newMap, count+1); // 아래로
		
		// 좌
		for(int i = 0; i<N; i++) {
			Arrays.fill(newMap[i], 0);
		}

		for(r = 0; r<N; r++) {
			c = 0;
			queue.clear();
			for(int j =0; j<N; j++) {
				if(map[r][j] != 0) {
					if(queue.isEmpty()) {
						queue.offer(map[r][j]);
					}else {
						int number = queue.poll();
						if(number == map[r][j]) {
							newMap[r][c] = number*2;
						}else {
							newMap[r][c] = number;
							queue.offer(map[r][j]);
						}
						if(count == 4) {
							maxNumber = Math.max(maxNumber, newMap[r][c]);
						}
						c++;
					}
				}
			}
			if(!queue.isEmpty()) {
				newMap[r][c] = queue.poll();
				if(count == 4) {
					maxNumber = Math.max(maxNumber, newMap[r][c]);
				}
			}
		}
		
		calculator(newMap, count+1); // 좌
		
		// 우
		for(int i = 0; i<N; i++) {
			Arrays.fill(newMap[i], 0);
		}
		for(r = 0; r<N; r++) {
			c = N-1;
			queue.clear();
			for(int j = N-1; j>=0; j--) {
				if(map[r][j] != 0) {
					if(queue.isEmpty()) {
						queue.offer(map[r][j]);
					}else {
						int number = queue.poll();
						if(number == map[r][j]) {
							newMap[r][c] = number*2;
						}else {
							newMap[r][c] = number;
							queue.offer(map[r][j]);
						}
						if(count == 4) {
							maxNumber = Math.max(maxNumber, newMap[r][c]);
						}
						c--;
					}
				}
			}
			if(!queue.isEmpty()) {
				newMap[r][c] = queue.poll();
				if(count == 4) {
					maxNumber = Math.max(maxNumber, newMap[r][c]);
				}
			}
		}
		
		calculator(newMap, count+1); // 우
		
		
		
		
		
	}
	

}