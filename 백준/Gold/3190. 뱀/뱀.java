import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import javax.swing.plaf.synth.SynthSpinnerUI;


class Dot{
	int x;
	int y;
	public Dot(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	
	public static int N, K, L; // 보드의 크기 N, 사과의 개수 K, 뱀의 방향 변환 횟수 L
	
	public static int[][] map;
	
	public static HashMap<Integer, Character> orders;
	
	public static int[] dx = {-1, 0, 1, 0};
	public static int[] dy = {0, -1, 0, 1};
	
	public static HashMap<Integer, ArrayList<Integer>> direction;
	
	public static int current_direction = 3; // 뱀이 오른쪽 방향을 바라보는 상황이다.
	
	public static Deque<Dot> dq;
	
	public static int timer = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		
		String[] str;
		for(int i = 0 ; i<K; i++) {
			str = br.readLine().split(" ");
			map[Integer.parseInt(str[0]) - 1][Integer.parseInt(str[1]) - 1] = 2; // map[][] 에 사과를 놓는다.
		}
		
		L = Integer.parseInt(br.readLine());
		
		orders = new HashMap<>();
		
		
		for(int i = 0; i<L; i++) {
			str = br.readLine().split(" ");
			orders.put(Integer.parseInt(str[0]), str[1].charAt(0));
		}

		dq = new ArrayDeque<>();
		
		map[0][0] = 1;
		
		dq.addFirst(new Dot(0, 0));
		
		findPath(0,0);
		
		System.out.println(timer);
	}
	
	static void findPath(int x, int y) {
	
		
		timer++;
		
		int nx = x + dx[current_direction];
		int ny = y + dy[current_direction];
		
		if(rangeCheck(nx, ny)) {
			
			Dot d = dq.peekLast();
			
			if(d.x == nx && d.y == ny) {
				return;
			}
			if(map[nx][ny] == 2) { // 사과를 발견한 경우
				dq.addFirst(new Dot(nx, ny));
				map[nx][ny] = 1;
			}else if(map[nx][ny] == 1) { // 뱀의 몸을 만난 경우		
				Dot dot = dq.pollLast();
	
				if(dot.x == nx && dot.y == ny) { // 뱀의 머리와 꼬리가 만난 경우
					//map[dot.x][dot.y] = 1; // 머리의 좌표를 1로 만들어준다.
				}else // 뱀의 머리와 꼬리가 아닌 몸통과 머리가 만난 경우
					return;
				
			}else { // 빈 공간을 만난 경우
				Dot dot = dq.pollLast();
				map[dot.x][dot.y] = 0;
				
				dq.addFirst(new Dot(nx, ny));
				map[nx][ny] = 1;
			}
			
			
			
			if(orders.containsKey(timer)) {
				char value = orders.get(timer);
				
				// 오른쪽
				if(value == 'D') {
					if(current_direction == 0)
						current_direction = 3;
					else
						current_direction--;
				}else { // 왼쪽
					if(current_direction == 3)
						current_direction = 0;
					else
						current_direction++;
				}
			}
			
			findPath(nx,ny);
		}else
			return;
		
		
		
	}
	
	

	static boolean rangeCheck(int x, int y) {
		if(0<=x && x <N && 0<=y && y<N)
			return true;
		return false;
	}

}