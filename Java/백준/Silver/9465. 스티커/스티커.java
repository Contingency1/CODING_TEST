import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int testCase = Integer.parseInt(br.readLine());

    for (int tc = 0; tc < testCase; tc++) {
      int colCount = Integer.parseInt(br.readLine());

      int[][] array = new int[2][colCount];

      for (int c = 0; c < 2; c++) {
        array[c] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
      }

      int[][] dp = new int[2][colCount];

      // 초기값 세팅
      dp[0][0] = array[0][0];
      dp[1][0] = array[1][0];

      if (colCount > 1) {
        dp[0][1] = array[0][1] + dp[1][0];
        dp[1][1] = array[1][1] + dp[0][0];
      }

      for (int j = 2; j < colCount; j++) {
        for (int i = 0; i < 2; i++) {
          int arr = array[i][j];
          int max = Math.max(dp[Math.abs(i - 1)][j - 1], Math.max(dp[0][j - 2], dp[1][j - 2]));

          dp[i][j] = max + arr;
        }
      }
      sb.append(Math.max(dp[0][colCount - 1], dp[1][colCount - 1])).append("\n");
    }
    System.out.print(sb);
  }
}
