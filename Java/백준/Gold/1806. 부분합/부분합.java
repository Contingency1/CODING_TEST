import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    StringTokenizer st = new StringTokenizer(br.readLine());
    final int COUNT = Integer.parseInt(st.nextToken());
    final int TARGET = Integer.parseInt(st.nextToken());

    int[] input = new int[COUNT];

    st = new StringTokenizer(br.readLine());
    br.close();

    for (int i = 0; i < COUNT; i++) {
      input[i] = Integer.parseInt(st.nextToken());
    }

    int start = 0;
    int end = 0;

    int answer = Integer.MAX_VALUE;

    int sum = input[0];
    while (start < COUNT) {

      if (sum >= TARGET) {
        answer = Math.min(answer, end - start + 1);
        sum -= input[start];
        start++;
      } else {
        if (end == COUNT - 1) {
          sum -= input[start];
          start++;
          continue;
        }
        
        sum += input[++end];
      }
    }

    sb.append(answer == Integer.MAX_VALUE ? 0 : answer);

    System.out.print(sb);
  }
}
