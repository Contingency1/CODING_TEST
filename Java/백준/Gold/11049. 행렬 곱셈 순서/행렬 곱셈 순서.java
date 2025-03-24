import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine());

    int[][] input = new int[N][2];

    for (int i = 0; i < N; i++) {
      input[i] = Arrays.stream(br.readLine().split(" "))
          .mapToInt(Integer::parseInt).toArray();
    }

    long[][] dp = new long[N][N];

    for (int i = 0; i < N - 1; i++) {
      dp[i][i + 1] = (long) input[i][0] * input[i][1] * input[i + 1][1];
    }

    int plus = 2;

    while (true) {
      int x = 0;

      for (int y = x + plus; y < N; y++) {

        long min = Integer.MAX_VALUE;

        for (int i = x; i < y; i++) {
          long buffer;

          if (i == x) {
            buffer = (long) input[x][0] * input[x][1] * input[y][1] + dp[i + 1][y];
          } else if (i + 1 == y) {
            buffer = dp[x][i] + (long) input[x][0] * input[y][0] * input[y][1];
          } else {
            buffer = dp[x][i] + dp[i + 1][y] + (long) input[x][0] * input[i][1] * input[y][1];
          }

          min = Math.min(min, buffer);
        }

        dp[x][y] = min;

        x++;
      }

      if (dp[0][N - 1] != 0) {
        break;
      }

      plus++;
    }

    sb.append(dp[0][N - 1]);

    System.out.print(sb);
  }
}