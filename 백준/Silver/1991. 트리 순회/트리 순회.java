

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


class Node{
	Node left;
	char data;
	Node right;
}


class Tree{
	Node root;

	public Node createNode(Node left, char data, Node right) {
		Node node = new Node();
		
		node.left = left;
		node.data = data;
		node.right = right;
		
		return node;
	}
	

	public Node getTree() {
		return root;
	}
	
	public void setTree(Node node) {
		root = node;
	}
	
	
	static void preOrder(Node node) {
		System.out.print(node.data);
		
		if(node.left != null)
			preOrder(node.left);
		
		if(node.right != null)
			preOrder(node.right);
	}
	
	static void inOrder(Node node) {
		if(node.left != null)
			inOrder(node.left);
		
		System.out.print(node.data);
		
		if(node.right != null)
			inOrder(node.right);
	}
	
	static void postOrder(Node node) {
		if(node.left != null)
			postOrder(node.left);
		
		if(node.right != null)
			postOrder(node.right);
		
		System.out.print(node.data);
	}
	
	
}
public class Main {

	public static int N;
	public static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
			
		N = Integer.parseInt(br.readLine());
			
		String[] str;
		
		Tree tree = new Tree();
		
		String[] temp = new String[N];
		Node[] node = new Node[N]; // 0부터 N-1까지의 노드를 만든다.
		for(int i =0; i<N; i++) 
			temp[i] = br.readLine();
		
		char c1,c2,c3;
		for(int i = N-1; i>=0; i--) {
			str = temp[i].split(" ");
			c1 = str[0].charAt(0);
			c2 = str[1].charAt(0);
			c3 = str[2].charAt(0);
			
			if(c2 == '.' && c3 == '.') {
				node[c1-'A'] = tree.createNode(null, c1, null);
			}else if(c2 == '.' && c3 != '.') {
				node[c1-'A'] = tree.createNode(null, c1, node[c3-'A']);
			}else if(c2 != '.' && c3 == '.') {
				node[c1-'A'] = tree.createNode(node[c2-'A'], c1, null);
			}else {
				node[c1-'A'] = tree.createNode(node[c2-'A'], c1, node[c3-'A']);
			}
		}
		
		tree.setTree(node[0]);
		
		tree.preOrder(node[0]);
		
		System.out.println();
		
		tree.inOrder(node[0]);
		
		System.out.println();
		
		tree.postOrder(node[0]);
		
		
		

		
		
		
		
		
	}
	
}

