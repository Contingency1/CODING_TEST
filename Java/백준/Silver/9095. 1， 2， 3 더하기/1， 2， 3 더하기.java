import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine());

    int[] dp = new int[12];

    dp[1] = 1;
    dp[2] = 2;
    dp[3] = 4;
    dp[4] = 7;

    for (int i = 5; i < 11; i++) {
      dp[i] += dp[i - 1];
      dp[i] += dp[i - 2];
      dp[i] += dp[i - 3];
    }

    for (int i = 0; i < N; i++) {
      int target = Integer.parseInt(br.readLine());

      sb.append(dp[target]).append("\n");
    }

    System.out.print(sb);
  }

}
