import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int T = Integer.parseInt(br.readLine());
    for (int i = 1; i <= T; i++) {
      int K = Integer.parseInt(br.readLine());

      int[] arr = Arrays.stream(br.readLine().split(" "))
          .mapToInt(Integer::parseInt).toArray();

      if (arr.length == 1) {
        sb.append(arr[0]);
        System.out.println(sb);
        return;
      } else if (arr.length == 2) {
        sb.append(arr[0] + arr[1]);
        System.out.println(sb);
        return;
      }

      int[][] dp = new int[K][K];

      int[] sum = new int[K];

      sum[0] = arr[0];

      for (int j = 1; j < K; j++) {
        sum[j] = sum[j - 1] + arr[j];
      }

      for (int j = 0; j < K - 1; j++) {
        dp[j][j + 1] = arr[j] + arr[j + 1];
      }

      boolean flag = true;
      int plus = 2;

      while (flag) {
        int x = 0;

        for (int y = x + plus; y < K; y++) {

          int min = Integer.MAX_VALUE;

          for (int k = x; k < y; k++) {
            min = Math.min(min, dp[x][k] + dp[k + 1][y]);
          }

          dp[x][y] = min + (sum[y] - (x == 0 ? 0 : sum[x - 1]));

          if (y == K - 1) {
            plus++;
            if (x == 0) {
              flag = false;
            }
          }

          x++;
        }
      }

      sb.append(dp[0][K - 1]).append("\n");
    }

    System.out.print(sb);
  }
}