import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;


public class Main{
	
    public static void main(String[] args) throws Exception {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		String str[] = new  String[N];

		for(int i =0; i<N; i++) {
			String input = br.readLine();
			sb = new StringBuilder(input);
			str[i] = sb.reverse().toString();
			//System.out.println(str[i]);

		}
		
		Arrays.sort(str,(s1,s2) ->  s2.length() - s1.length());
		
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		
		int max_len = str[0].length() - 1;
		
		for(int i = max_len; i>=0; i--) {
			for(int j =0; j<N; j++) {
				if(i >= str[j].length())
					break;
				
				char c = str[j].charAt(i);
				
				if(!map.containsKey(c))
					map.put(c, (int)Math.pow(10, i));
				else
					map.put(c, map.get(c) + (int)Math.pow(10, i));
			}
		}
		
		ArrayList<Integer> result = new ArrayList<>();
		Iterator<Character> it = map.keySet().iterator();
		
		while(it.hasNext()) {
			char c = it.next();
			result.add(map.get(c));
		}
		
		Collections.sort(result,(a,b) -> b-a); // 내림차순으로 정렬
		
		int res = 0;
		int num = 9;
		
		for(int i =0; i<result.size(); i++) {
			res += result.get(i) * num--;
		}
		System.out.println(res);
	

		
    }// main()
}