import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


// 같은 집합인지 확인(find) -> 부모가 다르다? 같은 집합으로 만든다(merge)
public class Main {
   public static int n,m;
   
   public static int[] set;
    
    public static void main(String[] args) throws IOException {     
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	
    	String[] str = br.readLine().split(" ");
        
        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);
        
      
        makeSet();
        
        int order, a, b;
        for(int i = 0; i<m; i++) {
        	str = br.readLine().split(" ");
        	order = Integer.parseInt(str[0]);
        	a = Integer.parseInt(str[1]);
        	b = Integer.parseInt(str[2]);
        	
        	if(order == 0) { // a,b가 있는 집합을 합친다.
        		merge(a,b);
        	}else if(order == 1) { // a,b가 같은 집합인지 확인한다.
        		if(findSet(a) != findSet(b))
        			sb.append("NO\n");
        		else
        			sb.append("YES\n");
        	}
        	
        }
        //System.out.println(Arrays.toString(set));
        	
        System.out.println(sb.toString());
        
    }
    
    static void makeSet() {
    	set = new int[n+1];
    	for(int i = 0; i<n+1; i++)
    		set[i] = i;
    }
    
    static int findSet(int a) {
    	if(set[a] == a)
    		return a;
    	else
    		return set[a] = findSet(set[a]);
    }
    
    static void merge(int a, int b) {
    	int aRoot = findSet(a);
    	int bRoot = findSet(b);
    	if(aRoot != bRoot) { // 원소 a와 원소 b의 집합이 다른 경우
    		set[bRoot] = aRoot;
    	}
    }
    



}