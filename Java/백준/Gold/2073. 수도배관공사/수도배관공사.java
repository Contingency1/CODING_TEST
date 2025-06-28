import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    final int TARGET = Integer.parseInt(st.nextToken());
    final int PIPE_COUNT = Integer.parseInt(st.nextToken());

    int[] length = new int[PIPE_COUNT + 1];
    int[] capacity = new int[PIPE_COUNT + 1];
    for (int i = 1; i <= PIPE_COUNT; i++) {
      st = new StringTokenizer(br.readLine());
      length[i] = Integer.parseInt(st.nextToken());
      capacity[i] = Integer.parseInt(st.nextToken());
    }

    int[] dp = new int[TARGET + 1];

    dp[0] = Integer.MAX_VALUE;
    for (int i = 1; i <= TARGET; i++) {
      dp[i] = 0;
    }

    for (int i = 1; i <= PIPE_COUNT; i++) {
      for (int j = TARGET; j >= length[i]; j--) {
        if (dp[j - length[i]] > 0) {
          dp[j] = Math.max(dp[j], Math.min(dp[j - length[i]], capacity[i]));
        }
      }
    }

    System.out.print(dp[TARGET]);
  }
}
