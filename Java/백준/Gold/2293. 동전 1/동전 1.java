import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    StringTokenizer st = new StringTokenizer(br.readLine());
    final int COIN_COUNT = Integer.parseInt(st.nextToken());
    final int TARGET = Integer.parseInt(st.nextToken());
    int[] coins = new int[COIN_COUNT];
    for (int i = 0; i < COIN_COUNT; i++) {
      coins[i] = Integer.parseInt(br.readLine());
    }

    Arrays.sort(coins);

    int[] dp = new int[TARGET + 1];

    dp[0] = 1;

    for (int coin : coins) {
      for (int i = 1; i <= TARGET; i++) {
        if (i - coin >= 0) {
          dp[i] += dp[i - coin];
        }
      }
    }

    sb.append(dp[TARGET]);
    System.out.print(sb);
  }
}