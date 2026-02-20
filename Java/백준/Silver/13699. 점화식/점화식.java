import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int input = Integer.parseInt(br.readLine());

    long[] dp = new long[input + 1];

    if (input == 0) {
      System.out.print(1);
      return;
    }

    dp[0] = 1;
    dp[1] = 1;

    for (int i = 2; i <= input; i++) {
      long buffer = 0;

      for (int j = 0; j < i; j++) {
        buffer += dp[j] * dp[(i - 1) - j];
      }

      dp[i] = buffer;
    }

    System.out.print(dp[input]);

  }
}