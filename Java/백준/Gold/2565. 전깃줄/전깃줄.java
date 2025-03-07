import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int amount = Integer.parseInt(br.readLine());

    int[][] input = new int[amount][2];

    for (int i = 0; i < amount; i++) {
      input[i] = Arrays.stream(br.readLine().split(" "))
          .mapToInt(Integer::parseInt).toArray();
    }

    Arrays.sort(input, (a, b) -> a[0] - b[0]);

    int[] dp = new int[input.length];
    dp[dp.length - 1] = 0;

    //첫 포인터, 커지면서 비교
    for (int i = dp.length - 2; i >= 0; i--) {
      //둘째 포인터, 커지면서 비교
      for (int j = i + 1; j < dp.length; j++) {
        if (input[i][1] < input[j][1]) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }
    }

    int max = 0;

    for (int i = 0; i < dp.length; i++) {
      max = Math.max(max, dp[i]);
    }

    sb.append(amount - max - 1);

    System.out.print(sb);
  }
}
