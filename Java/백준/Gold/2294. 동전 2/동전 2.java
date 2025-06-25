import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    final int COIN_COUNT = Integer.parseInt(st.nextToken());
    final int TARGET = Integer.parseInt(st.nextToken());

    Set<Integer> set = new TreeSet<>();
    for (int i = 1; i < COIN_COUNT + 1; i++) {
      set.add(Integer.parseInt(br.readLine()));
    }
    int[] temp = set.stream().mapToInt(Integer::intValue).toArray();
    int[] coins = new int[temp.length + 1];
    coins[0] = 0;
    System.arraycopy(temp, 0, coins, 1, temp.length);

    int[][] dp = new int[coins.length][TARGET + 1];
    for (int i = 0; i < coins.length; i++) {
      Arrays.fill(dp[i], Integer.MAX_VALUE);
      dp[i][0] = 0;
    }
    for (int i = 1; i < coins.length; i++) {
      for (int j = 1; j < TARGET + 1; j++) {
        dp[i][j] = dp[i - 1][j];

        if (coins[i] <= j && dp[i][j - coins[i]] != Integer.MAX_VALUE) {
          dp[i][j] = Math.min(dp[i][j], dp[i][j - coins[i]] + 1);
        }
      }
    }

    if (dp[coins.length - 1][TARGET] == Integer.MAX_VALUE) {
      System.out.println(-1);
    } else {
      System.out.println(dp[coins.length - 1][TARGET]);
    }
  }


}