import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    final int TOPPING_COUNT = Integer.parseInt(br.readLine());

    StringTokenizer st = new StringTokenizer(br.readLine());
    final int DOUGH_PRICE = Integer.parseInt(st.nextToken());
    final int TOPPING_PRICE = Integer.parseInt(st.nextToken());

    final int DOUGH_CALORIE = Integer.parseInt(br.readLine());

    int[] toppingCalories = new int[TOPPING_COUNT];

    for (int i = 0; i < TOPPING_COUNT; i++) {
      toppingCalories[i] = Integer.parseInt(br.readLine());
    }

    Arrays.sort(toppingCalories);

    int[][] dp = new int[TOPPING_COUNT][TOPPING_COUNT];
    for (int i = 0; i < dp.length; i++) {
      dp[i][i] = toppingCalories[i];
    }

    for (int i = 0; i < dp.length; i++) {
      for (int j = i + 1; j < dp.length; j++) {
        dp[i][j] = (dp[i][j - 1] + toppingCalories[j]);
      }
    }

    int answer = DOUGH_CALORIE / DOUGH_PRICE;
    for (int i = 0; i < dp.length; i++) {
      for (int j = i; j < dp.length; j++) {

        answer = Math.max(answer,
            (DOUGH_CALORIE + dp[i][j]) / (DOUGH_PRICE + (j - i + 1) * TOPPING_PRICE));
      }
    }

    System.out.print(answer);
  }
}