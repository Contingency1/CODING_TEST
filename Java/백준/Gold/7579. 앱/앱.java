import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    StringTokenizer st = new StringTokenizer(br.readLine());

    final int APP_COUNT = Integer.parseInt(st.nextToken());
    final int MAX_VALUE = Integer.parseInt(st.nextToken());

    int[] weight = new int[APP_COUNT + 1];
    int[] value = new int[APP_COUNT + 1];

    st = new StringTokenizer(br.readLine());
    for (int i = 1; i < APP_COUNT + 1; i++) {
      weight[i] = Integer.parseInt(st.nextToken());
    }

    int sumValue = 0;

    st = new StringTokenizer(br.readLine());
    for (int i = 1; i < APP_COUNT + 1; i++) {
      value[i] = Integer.parseInt(st.nextToken());
      sumValue += value[i];
    }
    br.close();

    int[][] dp = new int[APP_COUNT + 1][sumValue + 1];

    for (int i = 1; i < APP_COUNT + 1; i++) {
      for (int j = 0; j < sumValue + 1; j++) {
        if (j - value[i] >= 0) {
          //넣거나 안넣거나
          dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - value[i]] + weight[i]);
        } else {
          //넣으면 안됨.
          dp[i][j] = dp[i - 1][j];
        }
      }
    }

    int answer = 0;
    for (int i = 0; i < APP_COUNT + 1; i++) {
      for (int j = 0; j < sumValue + 1; j++) {
        if (dp[i][j] >= MAX_VALUE) {
          answer = j;
          break;
        }
      }
    }
//
//    for (int[] a : dp) {
//      sb.append(Arrays.toString(a)).append("\n");
//    }
    sb.append(answer);

    System.out.print(sb);
  }
}
