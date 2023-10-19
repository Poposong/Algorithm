import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {

			public int compare(Integer n1, Integer n2) {
				return n2-n1; // 내림차순
			}

		});
		
		PriorityQueue<Integer> minHeap = new PriorityQueue<>(new Comparator<Integer>() {
			public int compare(Integer n1, Integer n2) {
				return n1-n2; // 오름차순
			}
		});
		
		
		
		
		Integer number;
		int temp;
		while(N-- != 0) {
			number = Integer.valueOf(br.readLine());
			
			if(maxHeap.size() == minHeap.size()) { // 크기가 같다면 maxHeap에 넣는다. (포인터는 maxHeap의 탑으로 설정되어있음)
				maxHeap.add(number);
			}else { // 크기가 다른 경우는 두 큐의 차이가 1인 경우이고 항상 minHeap이 더 작음
				minHeap.add(number);
			}
			
			if(maxHeap.isEmpty() || minHeap.isEmpty()) {
				sb.append(maxHeap.peek()).append("\n");
				continue;
			}
			
			if(maxHeap.peek() > minHeap.peek()) {
				temp = maxHeap.poll();
				maxHeap.add(minHeap.poll());
				minHeap.add(temp);
			}
			sb.append(maxHeap.peek()).append("\n");
		}
		
		System.out.println(sb.toString());
		

	}

}