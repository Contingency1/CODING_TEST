import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    final int ORDER_COUNT = Integer.parseInt(st.nextToken());
    final int LEFT_BURGER = Integer.parseInt(st.nextToken());
    final int LEFT_POTATO = Integer.parseInt(st.nextToken());

    int answer = 0;
    int[][] dp = new int[LEFT_BURGER + 1][LEFT_POTATO + 1];
    for (int i = 1; i < ORDER_COUNT + 1; i++) {
      st = new StringTokenizer(br.readLine());
      int needBurger = Integer.parseInt(st.nextToken());
      int needPotato = Integer.parseInt(st.nextToken());

      for (int j = LEFT_BURGER; j > 0; j--) {
        for (int k = LEFT_POTATO; k > 0; k--) {
          if (j - needBurger >= 0 && k - needPotato >= 0) {
            dp[j][k] = Math.max(dp[j][k], dp[j - needBurger][k - needPotato] + 1);
            answer = Math.max(answer, dp[j][k]);
          }
        }
      }
    }
    System.out.print(answer);
  }
}
