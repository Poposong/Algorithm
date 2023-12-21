import java.io.*;

// 시간복잡도 : O(n^2)
public class Main {
    static int[] height;
    static int[] answer;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] str = br.readLine().split(" ");
        height = new int[N+1];
        answer = new int[N+1];
        for(int i = 1; i<N+1; i++){
            height[i] = Integer.parseInt(str[i-1]);
        }
        
        double r1, r2;
        for(int i = 1; i<N; i++){
            r1 = calculator(i, height[i], i+1, height[i+1]);
            // 초반에 양쪽은 모두 연결이 가능하기 때문에 값을 세팅해준다.
            answer[i]++;
            answer[i+1]++;
            for(int j = i+2; j<N+1; j++){
                r2 = calculator(i, height[i], j, height[j]); // x1, y1, x2, y2
                if(r1 < r2){ // 현재까지의 기울기의 최대보다 더 큰값이 나온 경우
                    r1 = r2;
                    answer[i]++;
                    answer[j]++;
                }
            }
        }
        int result = answer[1];
        for(int i = 2; i<N+1; i++){
            result = Math.max(result, answer[i]);
        }
        System.out.println(result);
    }
    
    // 기울기를 구한다.
    static double calculator(int x1, int y1, int x2, int y2){
        return (y2-y1)*1.0/(x2-x1);
    }
}