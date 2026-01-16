import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

// AI 도움 이용함...
public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int count = Integer.parseInt(br.readLine());

    int[] take = new int[count + 1];
    int[] gold = new int[count + 1];

    for (int i = 1; i <= count; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());

      int t = Integer.parseInt(st.nextToken());
      int g = Integer.parseInt(st.nextToken());

      take[i] = t;
      gold[i] = g;
    }

    int[] dp = new int[count + 2];

    int max = 0;

    for (int i = 1; i <= count; i++) {
      max = Math.max(max, dp[i]);
      
      int canGet = i + take[i];

      if (canGet <= count + 1) {
        dp[canGet] = Math.max(dp[canGet], max + gold[i]);
      }
    }

    System.out.print(Math.max(max, dp[count + 1]));
  }
}