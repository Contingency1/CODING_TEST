import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    final int BAG_COUNT = Integer.parseInt(br.readLine());

    int[] weight = new int[BAG_COUNT + 1];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= BAG_COUNT; i++) {
      weight[i] = Integer.parseInt(st.nextToken());
    }

    int[] value = new int[BAG_COUNT + 1];
    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= BAG_COUNT; i++) {
      value[i] = Integer.parseInt(st.nextToken());
    }

    int[][] dp = new int[100][BAG_COUNT + 1];

    for (int i = 1; i < 100; i++) {
      for (int j = 1; j < BAG_COUNT + 1; j++) {
        if (i - weight[j] >= 0) {
          dp[i][j] = Math.max(dp[i][j - 1], dp[i - weight[j]][j - 1] + value[j]);
        } else {
          dp[i][j] = dp[i][j - 1];
        }
      }
    }

    sb.append(dp[99][BAG_COUNT]);

    System.out.print(sb);
  }


}
