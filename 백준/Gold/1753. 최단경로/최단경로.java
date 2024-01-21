import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Node implements Comparable<Node>{
	int index;
	int distance;
	public int getIndex() {
		return index;
	}
	public int getDistance() {
		return distance;
	}
	
	public Node(int index, int distance) {
		this.index = index;
		this.distance = distance;
	}
	
	@Override
	public int compareTo(Node other) {
		if(this.distance < other.distance)
			return -1;
		return 1;
	}
	
	
}

public class Main{
	
	
	public static int V,E,K;
    
    public static final int INF = (int) 1e9; // 무한을 의미하는 값으로 10억을 설정
    
    public static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    
    public static int d[] = new int[20001];
    
    public static void dijkstra(int start) {
    	PriorityQueue<Node> que = new PriorityQueue<>();
    	que.add(new Node(start, 0));
    	d[start] = 0;
    	//System.out.println("add : "+ start + " " + 0);
    	while(!que.isEmpty()) {
    		Node node = que.poll();
    		int now = node.index;
    		int distance = node.distance;
    		//System.out.println("poll : "+ now +" " + distance);
    		
    		
    		if(d[now] < distance) continue;
    		
    		for(int i =0; i<graph.get(now).size(); i++) {
    			int cost = d[now] + graph.get(now).get(i).distance;
    			
    			if(cost < d[graph.get(now).get(i).index]) {
    				d[graph.get(now).get(i).index] = cost;
    				que.offer(new Node(graph.get(now).get(i).index, cost));
    				//System.out.println("add :"+now +"  " + cost);
    			}
    		}
    	}
    }
    
    
    public static void main(String[] args) throws Exception {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String s[] = br.readLine().split(" ");
		V = Integer.parseInt(s[0]);
		E = Integer.parseInt(s[1]);
		K = Integer.parseInt(br.readLine());
		
		for(int i =0; i<V+1; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i =0; i<E; i++) {
			s = br.readLine().split(" ");
			int u = Integer.parseInt(s[0]);
			int v = Integer.parseInt(s[1]);
			int w = Integer.parseInt(s[2]);
			
			graph.get(u).add(new Node(v,w));
		}
		
		Arrays.fill(d, INF);
		
		dijkstra(K);
		
		for(int i =1; i<V+1; i++) {
			if(d[i] == INF)
				System.out.println("INF");
			else
				System.out.println(d[i]);
		}
		
		
		
		
    }// main()
}