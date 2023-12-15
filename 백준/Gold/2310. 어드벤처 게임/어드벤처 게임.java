import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, fee[];
	static ArrayList<Pair> list[];
	static char type[];
	static boolean flag, visit[];
	static class Pair {
		int node;

		public Pair(int node) {
			this.node = node;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		while (true) {
			st = new StringTokenizer(in.readLine());
			N = Integer.valueOf(st.nextToken());
			type = new char[N+1];
			flag = false;
			visit = new boolean[N+1];
			if(N==0)
				break;
			list = new ArrayList[N + 1];
			fee = new int[N+1];
			for (int i = 1; i <= N; i++) {
				list[i] = new ArrayList<>();
			}
			for(int i=1; i<=N; i++) {
				st = new StringTokenizer(in.readLine());
				type[i] = st.nextToken().charAt(0);
				fee[i] =  Integer.valueOf(st.nextToken());
				while(true) {
					int node = Integer.valueOf(st.nextToken());
					if(node==0) break;
					list[i].add(new Pair(node));				
				}
			}
			//탐색
			if(type[1]!='T') {
				visit[1] = true;
				recur(fee[1], 1);
			}
			if(flag) {
				System.out.println("Yes");
			}else
				System.out.println("No");
		}
	}
	static void recur(int myMoney, int curNode) {
		if(flag) return;
		if(curNode == N) {
			flag = true;
			return;
		}
		for(Pair next : list[curNode]) {
			if(visit[next.node]) {
				continue;
			}
			
			if(type[next.node]=='L') {
				if(myMoney>=fee[next.node]) {
					visit[next.node] = true;
					recur(myMoney, next.node);
				}else {
					visit[next.node] = true;
					recur(fee[next.node], next.node);
				}
			}else if(type[next.node]=='T') {
				if(myMoney >= fee[next.node]) {
					visit[next.node] = true;
					recur(myMoney - fee[next.node], next.node);
				}else {
					return;
				}
			}else {
				visit[next.node] = true;
				recur(myMoney, next.node);
			}
			visit[next.node]=false;
		}
	}
}
