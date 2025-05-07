import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    String[] input1 = br.readLine().split(" ");

    final int CASE = Integer.parseInt(input1[0]);
    final int WEIGHT = Integer.parseInt(input1[1]);

    int[][] dp = new int[WEIGHT + 1][CASE + 1];

    int[] weights = new int[CASE + 1];
    int[] values = new int[CASE + 1];

    for (int i = 1; i < CASE + 1; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      weights[i] = Integer.parseInt(st.nextToken());
      values[i] = Integer.parseInt(st.nextToken());
    }

    for (int i = 1; i < WEIGHT + 1; i++) {
      for (int j = 1; j < CASE + 1; j++) {
        if (i - weights[j] >= 0) {
          dp[i][j] = Math.max(dp[i][j - 1], dp[i - weights[j]][j - 1] + values[j]);
        } else {
          dp[i][j] = dp[i][j - 1];
        }
      }
    }

    sb.append(dp[WEIGHT][CASE]);
    System.out.print(sb);
  }


}
