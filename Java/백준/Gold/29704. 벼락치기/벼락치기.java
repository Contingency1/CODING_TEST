import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    final int MATTER_COUNT = Integer.parseInt(st.nextToken());
    final int LEFT_DAY = Integer.parseInt(st.nextToken());

    int[] spendDay = new int[MATTER_COUNT + 1];
    int[] fee = new int[MATTER_COUNT + 1];

    int sumPrice = 0;
    for (int i = 1; i < MATTER_COUNT + 1; i++) {
      st = new StringTokenizer(br.readLine());

      spendDay[i] = Integer.parseInt(st.nextToken());
      fee[i] = Integer.parseInt(st.nextToken());
      sumPrice += fee[i];
    }

    int[] dp = new int[LEFT_DAY + 1];

    for (int i = 1; i < MATTER_COUNT + 1; i++) {
      for (int j = LEFT_DAY; j >= 1; j--) {
        if (j - spendDay[i] >= 0) {
          dp[j] = Math.max(dp[j], dp[j - spendDay[i]] + fee[i]);
        }
      }
    }

    System.out.print(sumPrice - dp[LEFT_DAY]);
  }
}
