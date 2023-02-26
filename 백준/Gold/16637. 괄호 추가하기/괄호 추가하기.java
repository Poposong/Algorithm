import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;



public class Main {
	public static int N;
	
	public static int[] number;// 숫자
	public static char[] operator; // 연산자
	public static int[] termSum; // 구간의 합
	
	public static int target;
	
	public static int max = Integer.MIN_VALUE;
	
	public static int number_len,operator_len,termSum_len;
    public static void main(String[] args) throws IOException {     
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(br.readLine());
        
        number_len = N/2+1;
        number = new int[number_len]; // 숫자
        
        termSum_len = N/2;
        termSum = new int[termSum_len]; // 구간의 합
        
        operator_len = N/2;
        operator = new char[operator_len]; // 연산자
        
        
        String[] str = br.readLine().split("");
        
        int idx1=0, idx2=0, idx3=0;
        for(int i =0; i<N; i++) {
        	if(i%2 == 0) {
        		number[idx1] = Integer.parseInt(str[i]); 
        		if(i != 0) {
        			char c = operator[idx3-1];
        			switch(c) {
        			case '*':
        				termSum[idx2++] = number[idx1-1]*number[idx1];
        				break;
        			case '-':
        				termSum[idx2++] = number[idx1-1]-number[idx1];
        				break;
        			case '+':
        				termSum[idx2++] = number[idx1-1]+number[idx1];
        				break;
        			}
        		}
        		
        		idx1++;
        	}else if(i%2 != 0) {
        		operator[idx3++] = str[i].charAt(0);
        	}
        }
        
       // System.out.println(Arrays.toString(number));
       // System.out.println(Arrays.toString(termSum));
       // System.out.println(Arrays.toString(operator));
        
        // 부분집합을 형성한다.
        int temp = number[0];
        for(int i =0; i<operator_len; i++) {
        	temp = calculator(operator[i], temp, number[i+1]);
        }
        
        
        max = Math.max(temp, max);
        
        for(int i =1; i<=number_len/2; i++) {
        	target = i;
        	choiceSum(0, 0, new ArrayList<Integer>());

        }
        
        System.out.println(max);
    }
    
    static void choiceSum(int start, int count, ArrayList<Integer> tempList) {
    	if(target == count) {
    		
    		
    		Collections.sort(tempList);
    		
    		
    		
    		boolean ischeck = false;
    		
    		boolean check1 = false;
    		
    		int sum;
    		int i;
    		if(tempList.contains(0)) {
    			sum = termSum[0];
    			i = 1;
    		}else {
    			sum = number[0];
    			i=0;
    		}
   
    		if(tempList.get(tempList.size()-1) == termSum_len-1) {
    			for(; i<termSum_len-1; i++) {
    		
    				if(tempList.contains(i+1)) {
    					sum = calculator(operator[i], sum, termSum[i+1]);
    					i++;
    				}else
    					sum = calculator(operator[i], sum, number[i+1]);
    			}
    		}else {
    			for(; i<operator_len; i++) {
    	
    				if(tempList.contains(i+1)) {
    					sum = calculator(operator[i], sum, termSum[i+1]);
    					i++;
    				}else
    					sum = calculator(operator[i], sum, number[i+1]);
    			}
    			
    		}

    		
    		max = Math.max(sum, max);

    		
    		return;
    	}
    	
    	for(int i = start; i<termSum_len; i++) {
    		tempList.add(i);
    		choiceSum(i+2, count+1, tempList);
    		tempList.remove(tempList.size()-1);
    	}
    
    	// 암묵적 return;
    }
    
    static int calculator(char c,int num1, int num2) {
    	switch(c) {
		case '*':
			return num1*num2;
		case '-':
			return num1-num2;
		case '+':
			return num1+num2;
		default:
			return -1;
		}
    	
    }

}