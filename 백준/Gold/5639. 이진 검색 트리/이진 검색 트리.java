

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


// Node 구조체
class Node{
    Node left;
    int data;
    Node right;
}


// Node를 가지고 있는 Tree
class Tree{
    Node root;

    public Node createNode(Node left, int data, Node right) {
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

    // 후위순회(left - right - root)
    static void postOrder(Node node) {
        if(node.left != null)
            postOrder(node.left);
        
        if(node.right != null)
            postOrder(node.right);
        
        System.out.println(node.data);
    }

}
public class Main {

    public static int N;
    public static StringBuilder sb = new StringBuilder();
    public static Tree tree;
    public static Node[] node;
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        tree = new Tree();

        node = new Node[10000];
        
        String s;
        int number;
        for(int i =0; ; i++) {
        	s = br.readLine();
        	if(s == null || s.equals(""))
				break;
            number = Integer.parseInt(s);
            if(i == 0) { // root 노드를 만드는 경우이다.
                node[i] = tree.createNode(null, number, null);
                tree.setTree(node[i]);
            }else {  // 새로 생긴 node[i]와 node[i]의 부모 노드를 연결해주는 코드
                node[i] = tree.createNode(null, number, null);
                makeTree(i);
            }
        }
        
        // 후위 순회를 호출한다.
        tree.postOrder(tree.getTree());
        
    }
    
    static void makeTree(int idx) { //새로 생긴 node[idx]와 node[idx]의 부모 노드를 연결해주는 메소드
    	Node pointer = tree.getTree();
    	
    	int number = node[idx].data;
    	
    	while(pointer != null) {
    		if(number < pointer.data) {
    			if(pointer.left == null) {
    				pointer.left = node[idx];
    				break;
    			}else
    				pointer = pointer.left;
    		}else {
    			if(pointer.right == null) {
    				pointer.right = node[idx];
    				break;
    			}else
    				pointer = pointer.right;
    		}
    	}

    	
    	
    }
    
}
