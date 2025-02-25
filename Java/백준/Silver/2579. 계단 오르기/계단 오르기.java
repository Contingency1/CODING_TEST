import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  static int N = 0;

  private static void dp(int[] arr, int[] cache, int location, int three) {

  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    N = Integer.parseInt(br.readLine());

    int[] arr = new int[N + 1];
    int[] cache = new int[N + 1];

    for (int i = 1; i <= N; i++) {
      arr[i] = Integer.parseInt(br.readLine());
    }

    if (N == 1) {
      sb.append(arr[1]).append("\n");
      System.out.println(sb);
      return;
    }
    cache[1] = arr[1];
    cache[2] = cache[1] + arr[2];
    for (int i = 3; i <= N; i++) {
      cache[i] = Math.max(cache[i - 2] + arr[i], cache[i - 3] + arr[i - 1] + arr[i]);
    }

//    for (Integer row : answers) {
//      sb.append(row).append("\n");
//    }

    sb.append(cache[N]);
    System.out.print(sb);
  }
}
