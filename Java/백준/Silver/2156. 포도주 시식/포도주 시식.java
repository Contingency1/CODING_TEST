import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine());

    int[] input = new int[N + 1];

    for (int i = 1; i <= N; i++) {
      input[i] = Integer.parseInt(br.readLine());
    }

    int[] dp = new int[N + 1];

    dp[1] = input[1];

    if (N > 1) {
      dp[2] = input[1] + input[2];
    }

    if (N > 2) {
      dp[3] = Math.max(input[1] + input[2], Math.max(input[1] + input[3], input[2] + input[3]));
    }

    for (int i = 4; i <= N; i++) {
      dp[i] = Math.max(dp[i - 1],
          Math.max(dp[i - 2] + input[i], dp[i - 3] + input[i - 1] + input[i]));
    }

    sb.append(dp[N]);
    System.out.print(sb);
  }
}
