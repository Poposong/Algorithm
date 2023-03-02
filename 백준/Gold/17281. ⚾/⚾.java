import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

	public static int N;

	
	public static int[] numbering;
	
	public static int[][] inning;

	public static boolean[] visited;

	public static Queue<Integer> queue;
	
	public static int result;
	
	public static int max;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		String[] str;
		
		inning = new int[N][9];
		
		for(int i =0; i<N; i++) {
			str = br.readLine().split(" ");
			for(int j =0; j<9; j++)
				inning[i][j] = Integer.parseInt(str[j]);
		}
		
		numbering = new int[9];
		
		visited = new boolean[9];
		
		Arrays.fill(visited, false);
		
		numbering[3] = 0; // 4번 타자에 1번 타자를 이미 배치한 경우

		visited[0] = true;
		
		max = Integer.MIN_VALUE;
		
		Permutation(0);
		
		//System.out.println(total);
		
		System.out.println(max);

	}

	static void Permutation(int idx) {
		if (idx == 9) {
			
			recurInning();

			return;
		}

		for (int i = 0; i < 9; i++) {
			if (!visited[i]) {
				visited[i] = true;

				numbering[idx] = i;

				if (idx + 1 == 3)
					Permutation(4);
				else
					Permutation(idx + 1);

				visited[i] = false;
			}
		}
	}
	
	static void recurInning() {
		queue = new ArrayDeque<>();

		int score = 0;

		int outCnt = 0;

		int idx = 0; // inning 배열을 가리킬 인덱스

		
		//System.out.println("----------------------");
		
		for(int i = 0; i<N; i++) {
			
			outCnt = 0;
			
			int num;
			
			queue.clear();
			
			while(outCnt < 3) {
				
				if(idx < 9) {
					num = inning[i][numbering[idx]];
				}else {
					idx = 0;
					num = inning[i][numbering[idx]];
				}
				
				idx++;
				
				if(num == 0) { // 아웃
					outCnt++;
					
					if(outCnt == 3)
						break;
				}else if (num == 1) {
					queue.offer(1);
					if (queue.size() == 4) {
						score += queue.poll();
					} 
					
				} else if (num == 2) {
					queue.offer(1);
					queue.offer(0);

					
					if (queue.size() <= 3)
						continue;
					else {
						while (3 < queue.size()) {
							score += queue.poll();
						}
					}
				} else if (num == 3) {
		
					queue.offer(1);
					queue.offer(0);
					queue.offer(0);


					if (queue.size() <= 3)
						continue;
					else {
						while (3 < queue.size()) {
							score += queue.poll();
						}
					}
				}else if(num == 4) { // 홈런
		
					while(!queue.isEmpty()) {
						score += queue.poll();
					}
					score++;
				}
				
				
			}
	
		}
	
		max = Math.max(max, score);
		
		return;
		
	}

}