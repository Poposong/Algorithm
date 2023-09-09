import java.util.*;

class WordScore implements Comparable<WordScore>{
	String word;
	ArrayList<Integer> visit;
	
	public WordScore(String word) {
		this.word = word;
		visit = new ArrayList<>();
	}
	
	@Override
	public int compareTo(WordScore wordscore) { // words[]의 단어와 비교한 단어의 수가 적은 단어가 우선순위를 가진다. 
		return this.visit.size() - wordscore.visit.size();
	}
}

class Solution {
	
	// str1와 str2가 한 개만 다르면 true 아니면 false값을 반환한다.
	public static boolean compareWord(String str1, String str2) {
		if(str1.length() != str2.length())
			return false;
		
		int differ = 0;
		for(int i =0; i<str1.length(); i++) {
			if(str1.charAt(i) != str2.charAt(i))
				differ++;
		}
		
		if(differ == 1)
			return true;
		else
			return false;
	}
	
	
	public static boolean visited[];
	
	public static PriorityQueue<WordScore> pq;
	
	public static int n, answer = 0;
	
    public int solution(String begin, String target, String[] words) {
   
        n = words.length;
        
        visited = new boolean[n];
        
        boolean check = false;
        for(int i =0; i<n; i++) {
        	if(words[i].equals(target))
        		check = true;
        }
        
        if(!check) // target과 같은 값이 words에 한 개도 없는 경우에는 0을 반환한다.
        	return answer;
        
        pq = new PriorityQueue<>();
        
        for(int i =0; i<words.length; i++) {
        	if(compareWord(begin, words[i])) {
        		WordScore ws = new WordScore(words[i]);
        		ws.visit.add(i);
        		pq.offer(ws);
        	}
        }
       
        BFS(target, words);

        
        return answer;
    }
    
    void BFS(String target, String[] words) {
    	
    	while(!pq.isEmpty()) {
    		WordScore ws = pq.poll();
    	
    		if(ws.word.equals(target)) { // target과 같은 문자열을 찾은 경우에는 리스트의 전체 사이즈를 반환한다.
    			answer = ws.visit.size();
    			return;
    		}
    		
    		for(int i =0; i<n; i++) {
    			if(!ws.visit.contains(i) && compareWord(ws.word, words[i])) { // words[]에서 체크했던 적이 없던 단어이면서 words[i]와 다른 글자가 1개인 경우
    				
    				WordScore ws1 = new WordScore(words[i]);
    				for(int j =0; j<ws.visit.size(); j++) {
    					ws1.visit.add(ws.visit.get(j));
    				}
    				
    				ws1.visit.add(i);
    				
    				pq.offer(ws1);
    	
    			}
    		}
    		
    	}
    }
}