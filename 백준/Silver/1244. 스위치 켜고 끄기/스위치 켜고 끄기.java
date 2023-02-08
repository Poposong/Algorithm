

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

/*

첫째 줄에는 스위치 개수가 주어진다.
스위치 개수는 100 이하인 양의 정수이다. 둘째 줄에는 각 스위치의 상태가 주어진다. 켜져 있으면 1, 꺼져있으면 0이라고 표시하고 사이에 빈칸이 하나씩 있다.
셋째 줄에는 학생수가 주어진다.
학생수는 100 이하인 양의 정수이다.
넷째 줄부터 마지막 줄까지 한 줄에 한 학생의 성별, 학생이 받은 수가 주어진다.
남학생은 1로, 여학생은 2로 표시하고, 학생이 받은 수는 스위치 개수 이하인 양의 정수이다.
학생의 성별과 받은 수 사이에 빈칸이 하나씩 있다.

8
0 1 0 1 0 0 0 1
2
1 3
2 3

6
0 0 0 0 0 0
1
2 6

 * */
public class Main {
	static int[] array;

	static int N;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		String[] str = br.readLine().split(" ");

		array = new int[N];

		for (int i = 0; i < N; i++)
			array[i] = Integer.parseInt(str[i]);

		int M = Integer.parseInt(br.readLine());

		int person, num;

		for (int i = 0; i < M; i++) {
			str = br.readLine().split(" ");
			
			person = Integer.parseInt(str[0]);
			num = Integer.parseInt(str[1]);

			Switch(person, num);
			
		}
		
		for(int i =0; i<array.length; i++) {
			if(0<i && i%20 == 0)
				sb.append("\n");
			sb.append(array[i]+" ");
		}
		System.out.println(sb.toString());

		
	}// main()

	static void Switch(int person, int num) {
		int idx = num - 1;
		if(person == 1) { // 남학생인 경우
			for(int t = idx; t<array.length; t += num)
				array[t] = array[t] == 1? 0 : 1;
		}else if(person == 2) { // 여학생인 경우
			if(idx == 0 || idx == N-1) { // 끝의 자리인 경우
				array[idx] = array[idx] == 1? 0 : 1;
			}else if(0<idx && idx < N-1) { //
				if(array[idx-1] != array[idx+1]) {
					array[idx] = array[idx] == 1 ? 0 : 1;
				}else {
					int s1 = idx-1;
					int s2 = idx+1; 
					while(true) {
						if(s1 < 0 || N-1 < s2) {
							for(int t = s1+1; t < s2; t++)
								array[t] = array[t] == 1? 0 : 1;
							return;
						}else if(array[s1] != array[s2]) {
							for(int t = s1+1; t < s2; t++)
								array[t] = array[t] == 1? 0 : 1;
							return;
						}else if(array[s1] == array[s2]) {
							s1--;
							s2++;
						}
					}
				}
			}
		}
	}
}