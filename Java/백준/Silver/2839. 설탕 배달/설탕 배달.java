import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  private static int calculation(int input, int[] coins) {
    int[] dp = new int[input + 1];
    // 4
    dp[0] = 0;
    dp[1] = 0;
    dp[2] = 0;
    dp[3] = 1;

    if (input >= 5) {
      dp[4] = 0;
      dp[5] = 1;
    }

    for (int i = 6; i <= input; i++) {
      for (int coin : coins) {
        if (i >= coin && dp[i - coin] != 0) { // 만약 i에서 동전을 뺀 DP에서 최소 경우가 있다면
          dp[i] = dp[i - coin] + 1; //그 경우의 수에서 +1 해라
        }
      }
    }

    return dp[input] == 0 ? -1 : dp[input];
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int input = Integer.parseInt(br.readLine());
    int[] coins = {3, 5};

    sb.append(calculation(input, coins));

    System.out.print(sb);
  }
}