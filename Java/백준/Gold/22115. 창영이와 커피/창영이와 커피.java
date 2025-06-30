import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    final int COFFE_COUNT = Integer.parseInt(st.nextToken());
    final int TARGET = Integer.parseInt(st.nextToken());
    if (TARGET == 0) {
      System.out.println(0);
      return;
    }

    st = new StringTokenizer(br.readLine());

    int[] coffee = new int[COFFE_COUNT + 1];
    for (int i = 1; i < COFFE_COUNT + 1; i++) {
      coffee[i] = Integer.parseInt(st.nextToken());
    }

    int[] dp = new int[TARGET + 1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    for (int i = 1; i < COFFE_COUNT + 1; i++) {
      for (int j = dp.length - 1; j >= 1; j--) {
        if (j == coffee[i]) {
          dp[j] = 1;
          continue;
        }

        if (j - coffee[i] >= 0 && dp[j - coffee[i]] != Integer.MAX_VALUE) {
          dp[j] = Math.min(dp[j], dp[j - coffee[i]] + 1);
        }
      }
    }

    System.out.print(dp[TARGET] == Integer.MAX_VALUE ? -1 : dp[TARGET]);
  }
}
