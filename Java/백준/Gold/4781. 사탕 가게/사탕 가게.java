import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    while (true) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      final int CANDY_COUNT = Integer.parseInt(st.nextToken());
      final int BUDGET = new BigDecimal(st.nextToken()).multiply(new BigDecimal("100")).intValue();

      if (CANDY_COUNT == 0 && BUDGET == 0) {
        break;
      }

      int[] calorie = new int[CANDY_COUNT + 1];
      int[] cost = new int[CANDY_COUNT + 1];
      for (int i = 0; i < CANDY_COUNT; i++) {
        st = new StringTokenizer(br.readLine());
        calorie[i + 1] = Integer.parseInt(st.nextToken());
        cost[i + 1] = new BigDecimal(st.nextToken()).multiply(new BigDecimal("100")).intValue();
      }

      int[] dp = new int[BUDGET + 1];

      for (int i = 0; i < CANDY_COUNT + 1; i++) {
        for (int j = 0; j < dp.length; j++) {
          if (j - cost[i] >= 0) {
            dp[j] = Math.max(dp[j], dp[j - cost[i]] + calorie[i]);
          }
        }
      }

      System.out.println(dp[BUDGET]);

    }


  }


}