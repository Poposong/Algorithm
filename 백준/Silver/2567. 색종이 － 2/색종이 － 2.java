import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	public static int T; // 검은 스카프의 수
	
	public static int[][] map; // 1 또는 0으로 검은색 스카프가 배치된 상황을 저장할 2차원 배열
	
	
	public static int[] dx = {-1, 1, 0, 0}; // x좌표를 상,하,좌,우 탐색할 배열
	public static int[] dy = {0, 0, -1, 1}; // y좌표를 상,하,좌,우 탐색할 배열
	
	// 검은색 스카프가 시작하는 가장 작은 x좌표, 검은색 스카프가 끝나는 가장 큰 x좌표
	public static int startRow = Integer.MAX_VALUE, endRow = Integer.MIN_VALUE;
	
	// 검은색 스카프가 시작하는 가장 작은 y좌표, 검은색 스카프가 끝나는 가장 큰 y좌표
	public static int startCol = Integer.MAX_VALUE, endCol = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine()); // T의 값을 키보드에서 입력받음
		
		String[] str;
		
		map = new int[100][100]; // map[][] 배열 초기화
		
		int x1, y1, x2, y2; // 검은색 스카프의 시작점과 끝점을 저장할 변수 선언
		for(int t = 0; t<T; t++) {
			str = br.readLine().split(" ");
			
			// 스카프에서 왼쪽 아래의 점
			x1 = Integer.parseInt(str[0]); // 검은색 스카프의 시작점 x
			y1 = Integer.parseInt(str[1]); // 검은색 스카프의 시작점 y
			
			// 스카프에서 오른쪽 위의 점
			x2 = x1 + 9; // 검은색 스카프의 끝나는점 x
			y2 = y1 + 9; // 검은색 스카프의 끝나는점 y
			
			// 기존에 존재하던 x,y 좌표에서 가장 큰 x,y 좌표를 다시 비교한 후 저장한다.
			startRow = Math.min(startRow, x1);
			endRow = Math.max(endRow, x2);
			
			startCol = Math.min(startCol, y1);
			endCol = Math.max(endCol, y2);
			
			// 검은색 스카프를 map[][] 에 저장한다.
			for(int i = x1; i<=x2; i++) {
				for(int j = y1; j<=y2; j++) {
					map[i][j] = 1;
				}
			}

		}

		
		int result = 0; // 결과를 반환할 변수 result를 선언한다.
		int nextX, nextY; // 기존의 x,y 값에서 상하좌우 탐색했을 경우의 좌표를 저장할 변수를 선언한다.
		for(int i = startRow; i<=endRow; i++) {
			for(int j = startCol ; j<=endCol; j++) {
				if(map[i][j] == 1) { // 검은색 스카프를 만난 경우
					for(int t = 0; t<4; t++) { // 상하좌우를 탐색한다.
						nextX = i + dx[t];
						nextY = j + dy[t];
						if(rangeCheck(nextX, nextY)) { // 범위 안에 있는 경우
							if(map[nextX][nextY] == 0) { // 상하좌우의 값이 0이라면 테두리에 존재한다는 뜻이므로 둘레의 길이(result)에 1을 더해준다.
								result++; // 둘레의 길이에 1을 더해준다.
							}
						}else { // 범위를 초과하는 경우에는 가장 끝에 있는 벽 또한 0으로 볼 수 있으므로 둘레의 길이에 1을 더해준다.
							result++; // 둘레의 길이에 1을 더해준다.
						}
					}
				}
			}
		}
		
		System.out.println(result);
		
	}
	// x,y 좌표가 정사각형 모양의 흰색 천 안에 있는 좌표인지 체크한 후 범위에 존재하면 true, 아니면 false를 반환하는 메소드
	static boolean rangeCheck(int x, int y) {
		if(0<=x && x < 100 && 0<=y && y<100)
			return true;
		return false;
	}

}