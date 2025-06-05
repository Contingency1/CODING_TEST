import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    final int COUNT = Integer.parseInt(br.readLine());

    StringTokenizer st = new StringTokenizer(br.readLine());
    int[] arr = new int[COUNT];
    for (int i = 0; i < COUNT; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(arr);

    int distance = Integer.MAX_VALUE;
    int[] answer = new int[2];

    int start = 0;
    int end = COUNT - 1;
    while (start < end) {
      int sum = arr[start] + arr[end];

      if (sum == 0) {
        System.out.println(arr[start] + " " + arr[end]);
        return;
      }

      if (sum < 0) {
        if (Math.abs(sum) < distance) {
          distance = Math.abs(sum);
          answer[0] = arr[start];
          answer[1] = arr[end];
        }
        start++;

      } else {
        if (Math.abs(sum) < distance) {
          distance = Math.abs(sum);
          answer[0] = arr[start];
          answer[1] = arr[end];
        }
        end--;
      }
    }
    sb.append(answer[0]).append(" ").append(answer[1]);

    System.out.print(sb);
  }
}