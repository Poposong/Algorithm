import java.util.*;

class Solution {
	
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        ArrayList<ArrayList<Character>> list = new ArrayList<>();
        ArrayList<ArrayList<Integer>> idx_list;
        
        // 문자 세팅
        for(int i =0; i<n; i++) 
        	list.add(new ArrayList<Character>());
        
        for(int i =board.length-1; 0<=i ; i--) {
        	String str = board[i];
        	for(int j =0; j<str.length(); j++){
        		list.get(j).add(str.charAt(j));
        	}
        }

        boolean ischeck = false; // list에서 점수를 얻을 수 있는 짝을 찾았는가?
        int idx1 = 0;
    	int idx2 = 1;
    	
        while(true) {

        	
        	ischeck = false;
        	
        	
        	idx_list = new ArrayList<>();
        	for(int i =0; i<list.size(); i++)
        		idx_list.add(new ArrayList<>());
        	
        	for(int i =0; i<list.size()-1; i++) {
        		int len1 = list.get(i).size();
        		int len2 = list.get(i+1).size();
        		idx1 = 0;
        		idx2 = 1;
        	
        		for(int j=0; j<len1-1; j++) {
        			if(len2<=idx1 || len2<= idx2)
            			break;
        			char c = list.get(i).get(j);
        			
        			if(list.get(i).get(j+1) == c && list.get(i+1).get(idx1) == c && list.get(i+1).get(idx2) == c) {
        				if(!idx_list.get(i).contains(j))
        					idx_list.get(i).add(j);
        				if(!idx_list.get(i).contains(j+1))
        					idx_list.get(i).add(j+1);
        				if(!idx_list.get(i+1).contains(idx1))
        					idx_list.get(i+1).add(idx1);
        				if(!idx_list.get(i+1).contains(idx2))
        					idx_list.get(i+1).add(idx2);
        			
        				ischeck = true;

        				//System.out.println(c);
        				
        			}
        			
        			idx1++;
        			idx2++;
        		}
        		
        	
        	}
        	if(idx_list.size() == 0)
        		break;
       
        	for(int s = 0; s<idx_list.size(); s++) {
        		Collections.sort(idx_list.get(s), (a,b) -> ( b - a)); // 리스트를 내림차순으로 정렬
        		answer += idx_list.get(s).size();
        		for(int k = 0; k<idx_list.get(s).size(); k++) {
        			int index = idx_list.get(s).get(k);
        			list.get(s).remove(index);
        		}
        	}

        	if(ischeck == false)
        		break;
        }
        
        return answer;
    }
}