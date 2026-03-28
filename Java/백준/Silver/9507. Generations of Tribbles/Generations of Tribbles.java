import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    long[] dp = new long[68];

    dp[0] = 1;
    dp[1] = 1;
    dp[2] = 2;
    dp[3] = 4;

    for (int i = 4; i < 68; i++) {
      dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3] + dp[i - 4];
    }

    int count = Integer.parseInt(br.readLine());

    for (int i = 0; i < count; i++) {
      System.out.println(dp[Integer.parseInt(br.readLine())]);
    }
  }
}

