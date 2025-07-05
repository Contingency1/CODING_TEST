import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    final int PIPE_COUNT = Integer.parseInt(st.nextToken());
    final int TARGET = Integer.parseInt(st.nextToken());

    int[] dp = new int[TARGET + 1];
    dp[0] = 1;
    for (int i = 0; i < PIPE_COUNT; i++) {
      st = new StringTokenizer(br.readLine());

      int length = Integer.parseInt(st.nextToken());
      int amount = Integer.parseInt(st.nextToken());

      int[] buffer = dp.clone();
      for (int j = 1; j <= amount; j++) {
        for (int k = TARGET; k > 0; k--) {
          if (k - j * length >= 0) {
            dp[k] += buffer[k - j * length];
          }
        }
      }
    }

    System.out.println(dp[TARGET]);
  }
}

