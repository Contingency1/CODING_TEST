import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    final int TEST_CASE = Integer.parseInt(br.readLine());

    for (int i = 0; i < TEST_CASE; i++) {
      final int CASE = Integer.parseInt(br.readLine());
      StringTokenizer st = new StringTokenizer(br.readLine());
      int[] coins = new int[CASE + 1];
      for (int j = 1; j < CASE + 1; j++) {
        coins[j] = Integer.parseInt(st.nextToken());
      }
      final int WEIGHT = Integer.parseInt(br.readLine());

      int[][] dp = new int[CASE + 1][WEIGHT + 1];

      for (int j = 1; j < CASE + 1; j++) {
        dp[j][0] = 1;
        for (int k = 1; k < WEIGHT + 1; k++) {
          if (k - coins[j] >= 0) {
            dp[j][k] = dp[j - 1][k] + dp[j][k - coins[j]];
          } else {
            dp[j][k] = dp[j - 1][k];
          }
        }
      }

      sb.append(dp[CASE][WEIGHT]).append("\n");
    }
    System.out.print(sb);
  }


}
