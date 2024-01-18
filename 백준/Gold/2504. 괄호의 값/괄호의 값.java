// 방법1) 내가 작성한 코드

import java.io.*;
import java.util.*;
public class Main {
    static class Node{ // data는 괄호, check가 true이면 곱해야 하는 괄호라는 의미이고 false이면 더하는 괄호라는 의미이다.
        char data;
        boolean check;

        public Node(char data, boolean check){
            this.data = data;
            this.check = check;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] word = br.readLine().toCharArray();

        Stack<Node> stack = new Stack<>();
        Stack<Integer> store = new Stack(); // 각 자리까지 계산한 값을 저장하는 스택
        for(char c : word){
            if(c == '(' || c == '['){
                if(!stack.isEmpty()){
                    if(!stack.peek().check){ // 중복되는 여는 괄호가 있으면 이전의 괄호는 true로 바꿔준다.
                        store.add(-1); // 곱하는 시점까지라는 의미로 -1을 넣음
                        stack.peek().check = true;
                    }
                }
                stack.push(new Node(c, false));
            }else{
                if(!possibleCheck(stack, c)){
                    System.out.println(0);
                    return;
                }

                if(stack.peek().check){
                    stack.pop();

                    int sum = 0;
                    while(!store.isEmpty()){
                        if(store.peek() == -1){ // 곱하는 시점까지 수를 다 더한다.
                            store.pop();
                            break;
                        }
                        sum += store.pop();
                    }

                    if(c == ')'){
                        store.add(2 * sum);
                    }else{
                        store.add(3 * sum);
                    }
                }else{
                    stack.pop();
                    if(c == ')'){
                        store.add(2);
                    }else if(c == ']'){
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

    static boolean possibleCheck(Stack<Node> stack, char c){
        if(!(stack.isEmpty()) && ((stack.peek().data == '(' && c == ')') || (stack.peek().data == '[' && c == ']'))) return true;
        return false;
    }
}


// 방법2) https://myeongju00.tistory.com/12

package Baekjoon;
import java.io.*;
import java.util.*;
public class BJ_2504_괄호의값 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] word = br.readLine().toCharArray();

        int result = 0;
        int value = 1;
        Stack<Character> stack = new Stack();
        for(int i = 0; i<word.length; i++){
            char c = word[i];
            if(c == '('){
                stack.add(c);
                value *= 2;
            }else if(c == '['){
                stack.add(c);
                value *= 3;
            }else if(c == ')'){
                if(stack.isEmpty() || stack.peek() != '(') {
                    result = 0;
                    break;
                }
                if(word[i-1] == '(') result += value;

                value /= 2;
                stack.pop();
            }else if(c == ']'){
                if(stack.isEmpty() || stack.peek() != '[') {
                    result = 0;
                    break;
                }
                
                if(word[i-1] == '[') result += value;
                value /= 3;

                stack.pop();
            }
        }

        if(!stack.isEmpty()){
            System.out.println(0);
        }else{
            System.out.println(result);
        }
    }
}

