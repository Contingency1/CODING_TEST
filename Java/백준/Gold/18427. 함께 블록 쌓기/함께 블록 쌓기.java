import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    final int STUDENT_COUNT = Integer.parseInt(st.nextToken());
    final int MAX_HAVE = Integer.parseInt(st.nextToken());
    final int TARGET = Integer.parseInt(st.nextToken());

    int[][] studentHave = new int[STUDENT_COUNT + 1][MAX_HAVE + 1];

    for (int i = 1; i < STUDENT_COUNT + 1; i++) {
      st = new StringTokenizer(br.readLine());

      int index = 1;
      while (st.hasMoreTokens()) {
        studentHave[i][index++] = Integer.parseInt(st.nextToken());
      }


    }

    int[] dp = new int[TARGET + 1];

    dp[0] = 1;

    for (int i = 1; i < STUDENT_COUNT + 1; i++) {

      int[] buffer = dp.clone();
      for (int j = 1; j < studentHave[i].length; j++) {
        int curHeight = studentHave[i][j];
        if (curHeight == 0) {
          break;
        }

        for (int k = TARGET; k > 0; k--) {
          if (k - curHeight >= 0) {
            dp[k] += buffer[k - curHeight];
            dp[k] %= 10007;
          }
        }
      }
    }

    System.out.print(dp[TARGET]);
  }
}
