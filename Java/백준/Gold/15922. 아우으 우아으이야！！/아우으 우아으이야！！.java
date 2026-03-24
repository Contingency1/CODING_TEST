import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    final int COUNT = Integer.parseInt(br.readLine());

    StringTokenizer st = new StringTokenizer(br.readLine());

    int left = Integer.parseInt(st.nextToken());
    int right = Integer.parseInt(st.nextToken());

    int answer = 0;

    for (int i = 1; i < COUNT; i++) {
      st = new StringTokenizer(br.readLine());

      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());

      if (right < start) {
        answer += Math.abs(right - left);
        left = start;
        right = end;
        continue;
      }

      if (left <= start && start <= right) {
        if(right < end) {
          right = end;
        }
      }
    }

    answer += Math.abs(right - left);

    System.out.print(answer);
  }
}