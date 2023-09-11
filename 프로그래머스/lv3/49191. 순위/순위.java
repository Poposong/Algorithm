import java.util.*;

class Solution {
	class Node{
		int win;
		int defeat;
		
	}
	
	public List<Integer>[] list;
	public Node[] nodes;
	public int N;
	
    public int solution(int n, int[][] results) {
        int answer = 0;
        list = new ArrayList[n+1];
        nodes = new Node[n+1];
        N = n;
        
        for(int i =0; i<N+1; i++) {
        	list[i] = new ArrayList<Integer>();
        	nodes[i] = new Node();
        }
        
        for(int i =0; i<results.length; i++) {
        	int win = results[i][0];
        	int lose = results[i][1];
        	
        	list[win].add(lose);
        }
        
        nodeUpdate();
        
     
        return hasRank();
    }
    
    public void nodeUpdate() {
    	for(int i =1; i<N+1; i++) {
    		Queue<Integer> que = new LinkedList<Integer>();
    		boolean visited[] = new boolean[N+1];
    		
    		que.offer(i);
    		visited[i] = true;
    		
    		while(!que.isEmpty()) {
    			int winner = que.poll();
    			
    			for(int loser : list[winner]) {
    				if(visited[loser]) continue;
    				else {
    					visited[loser] = true;
    					que.offer(loser);
    					//System.out.println(i+" "+winner+" "+loser);
    					nodes[i].win++;
    					nodes[loser].defeat++;
    				}
    			}
    		}
    		
    	}
    }
    
    public int hasRank() {
    	int count = 0;
    	for(Node node : nodes) {
    		if(node.win + node.defeat == N-1) count++;
    	}
    	return count;
    }
}