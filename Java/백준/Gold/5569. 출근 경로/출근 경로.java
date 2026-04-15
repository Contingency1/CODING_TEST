import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  static int OFF_SET = 100_000;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    final int COL_COUNT = Integer.parseInt(st.nextToken());
    final int ROW_COUNT = Integer.parseInt(st.nextToken());

    int[][][] dp = new int[ROW_COUNT + 1][COL_COUNT + 1][4];

    // 0: 위 위, 1: 우 위, 2: 우 우, 3: 위 우
    for (int i = 1; i <= ROW_COUNT; i++) {
      dp[i][1][0] = 1;
    }

    for (int i = 1; i <= COL_COUNT; i++) {
      dp[1][i][2] = 1;
    }

    for (int r = 2; r <= ROW_COUNT; r++) {
      for (int c = 2; c <= COL_COUNT; c++) {
        dp[r][c][0] = (dp[r - 1][c][0] + dp[r - 1][c][1]) % OFF_SET;
        dp[r][c][1] = dp[r - 1][c][2];
        dp[r][c][2] = (dp[r][c - 1][2] + dp[r][c - 1][3]) % OFF_SET;
        dp[r][c][3] = dp[r][c - 1][0];
      }
    }

    System.out.print(
        (dp[ROW_COUNT][COL_COUNT][0]
            + dp[ROW_COUNT][COL_COUNT][1]
            + dp[ROW_COUNT][COL_COUNT][2]
            + dp[ROW_COUNT][COL_COUNT][3]) % OFF_SET
    );

  }
}
