import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int TEST_CASE = Integer.parseInt(br.readLine());

    for (int k = 0; k < TEST_CASE; k++) {

      int COIN_COUNT = Integer.parseInt(br.readLine());
      int[] coins = new int[COIN_COUNT + 1];

      StringTokenizer st = new StringTokenizer(br.readLine());

      for (int j = 1; j < COIN_COUNT + 1; j++) {
        coins[j] = Integer.parseInt(st.nextToken());
      }

      int target = Integer.parseInt(br.readLine());

      int[] dp = new int[target + 1];
      dp[0] = 1;
      for (int i = 1; i < COIN_COUNT + 1; i++) {
        int coin = coins[i];
        for (int j = 1; j < dp.length; j++) {
          if (j - coin >= 0) {
            dp[j] += dp[j - coin];
          }
        }
      }

      System.out.println(dp[target]);
    }


  }
}
