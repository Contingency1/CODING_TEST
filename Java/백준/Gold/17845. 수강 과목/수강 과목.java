import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    final int STUDY_TIME = Integer.parseInt(st.nextToken());
    final int SUBJECT_COUNT = Integer.parseInt(st.nextToken());

    int[] importance = new int[SUBJECT_COUNT + 1];
    int[] time = new int[SUBJECT_COUNT + 1];

    for (int i = 0; i < SUBJECT_COUNT; i++) {
      st = new StringTokenizer(br.readLine());
      importance[i + 1] = Integer.parseInt(st.nextToken());
      time[i + 1] = Integer.parseInt(st.nextToken());
    }

    int[][] dp = new int[SUBJECT_COUNT + 1][STUDY_TIME + 1];

    for (int i = 1; i < dp.length; i++) {
      for (int j = dp[0].length - 1; j >= 1; j--) {
        dp[i][j] = dp[i - 1][j];

        if (j - time[i] >= 0) {
          dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - time[i]] + importance[i]);
        }
      }
    }

    int answer = 0;

    for (int i = 1; i < dp.length; i++) {
      answer = Math.max(answer, dp[i][STUDY_TIME]);
    }
    System.out.print(answer);
  }
}
