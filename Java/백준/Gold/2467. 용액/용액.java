import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    final int N = Integer.parseInt(br.readLine());

    int[] arr = new int[N];

    StringTokenizer st = new StringTokenizer(br.readLine());
    br.close();
    for (int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    int[] buffer = new int[3];
    Arrays.fill(buffer, Integer.MAX_VALUE);

    int start = 0;
    int end = N - 1;

    while (start < end) {
      int curValue = arr[start] + arr[end];
      if (curValue > 0) {
        if (Math.abs(curValue) < buffer[0]) {
          buffer[0] = Math.min(Math.abs(curValue), buffer[0]);
          buffer[1] = arr[start];
          buffer[2] = arr[end];
        }

        end--;
      } else if (curValue < 0) {
        if (Math.abs(curValue) < buffer[0]) {
          buffer[0] = Math.min(Math.abs(curValue), buffer[0]);
          buffer[1] = arr[start];
          buffer[2] = arr[end];
        }

        start++;
      } else {
        System.out.print(arr[start] + " " + arr[end]);
        return;
      }
    }

    sb.append(buffer[1]).append(" ").append(buffer[2]);

    System.out.print(sb);
  }
}
