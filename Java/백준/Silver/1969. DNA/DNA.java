import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int count = Integer.parseInt(st.nextToken());
    int length = Integer.parseInt(st.nextToken());

    int[][] arr = new int[length][26];

    for (int i = 0; i < count; i++) {
        char[] input = br.readLine().toCharArray();
        for (int j = 0; j < length; j++) {
            int idx = input[j] - 65;

            arr[j][idx]++;
        }
    }

    int answer = 0;
    StringBuilder sb = new StringBuilder();

    for (int[] ar : arr) {
        int max = 0;

        for (int a: ar) {
            if(a == 0) {
                continue;
            }
            
            if(max < a) {
                max = a;
            }
        }

        answer += count - max;
        
        for (int j = 0; j < 26; j++) {
            if(max == ar[j]) {
                sb.append((char) (j + 65));
                break;
            }
        }
            
    }
      
    System.out.println(sb);
    System.out.print(answer);
  }
}