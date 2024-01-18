import java.io.*;
import java.util.*;
public class Main {

    static class Node{
        char data;
        boolean check;

        public Node(char data, boolean check){
            this.data = data;
            this.check = check;
        }
    }
    static Stack<Node> stack;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] word = br.readLine().toCharArray();
        stack = new Stack<>();
        Stack<Integer> store = new Stack();
        // int idx = 0;
        for(char c : word){
            // System.out.println((idx++)+":"+stack.size()+","+store.size()+"=>"+ c);
            if(c == '(' || c == '['){
                if(stack.isEmpty()){
                    stack.push(new Node(c, false));
                }else if(stack.peek().data == '('){
                    if(!stack.peek().check){
                        store.add(-1);
                        // System.out.println("store add -1");
                        stack.peek().check = true;
                    }
                    stack.push(new Node(c, false));
                }else if(stack.peek().data == '['){
                    if(!stack.peek().check){
                        store.add(-1);
                        // System.out.println("store add -1");
                        stack.peek().check = true;
                    }
                    stack.push(new Node(c, false));
                }
            }else{
                if(stack.isEmpty() || !possibleCheck(c)){
                    // System.out.println(stack.peek().data);
                    System.out.println(0);
                    return;
                }

                if(stack.peek().check){
                    int sum = 0;
                    while(!store.isEmpty()){
                        if(store.peek() == -1){
                            store.pop();
                            break;
                        }
                        sum += store.pop();
                    }


                    if(stack.peek().data == '('){
                        // System.out.println("store add "+ store.size()+","+(2*sum));
                        store.add(2 * sum);
                    }else{
                        // System.out.println("store add "+ store.size()+"," +(3*sum));
                        store.add(3 * sum);
                    }

                    stack.pop();

//                    while(!store.isEmpty()){
//                        System.out.println(store.pop());
//                    }

                }else{
                    if(stack.peek().data == '(' && c == ')'){
                        stack.pop();
                        // System.out.println("store add 2");
                        store.add(2);
                    }else if(stack.peek().data == '[' && c == ']'){
                        stack.pop();
                        // System.out.println("store add 3");
                        store.add(3);
                    }
                }


            }
        }
        if(!stack.isEmpty()){
            System.out.println(0);
            return;
        }

        int sum = 0;
        while(!store.isEmpty()){
            sum += store.pop();
        }
        System.out.println(sum);

    }

    static boolean possibleCheck(char c){
        if((stack.peek().data == '(' && c == ')') || (stack.peek().data == '[' && c == ']')) return true;
        return false;
    }
}