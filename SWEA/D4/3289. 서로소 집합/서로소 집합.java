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



public class Solution {
    
	public static int T,n,m;
	
	public static int[] graph;
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        
        String[] str;
        StringBuilder temp;
        for(int t = 1; t<=T; t++) {
        	str = br.readLine().split(" ");
        	n = Integer.parseInt(str[0]);
        	m = Integer.parseInt(str[1]);
        	
        	temp = new StringBuilder();
        	
        	makeSet();
        	
        	int order,v1,v2;
        	for(int i =0; i<m; i++) {
        		str = br.readLine().split(" ");
        		order = Integer.parseInt(str[0]);
        		v1 = Integer.parseInt(str[1]) - 1;
        		v2 = Integer.parseInt(str[2]) - 1;
        		
        		switch(order) {
        		case 0:
        			union(v1,v2);
        			break;
        		case 1:
        			int p1 = findSet(v1);
        			int p2 = findSet(v2);
        			
        			if(p1 != p2)
        				temp.append(0);
        			else
        				temp.append(1);
        		}
        	}
        	
        	sb.append("#"+t+" "+temp.toString()+"\n");
        }
        
        System.out.println(sb.toString());
        
    
	}
	
	static void makeSet() {
		graph = new int[n];
		
		for(int i = 0; i<n; i++) 
			graph[i] = i;
	}
	
	static int findSet(int a) {
		if(graph[a] == a)
			return a;
		else
			return graph[a] = findSet(graph[a]);
	}
	
	static void union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if(aRoot != bRoot) {
			graph[bRoot] = aRoot;
		}
	}

}