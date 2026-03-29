import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    final int PARTY_COUNT = Integer.parseInt(st.nextToken());
    final int CLIENT_COUNT = Integer.parseInt(st.nextToken());

    int[][] dp = new int[PARTY_COUNT + 1][PARTY_COUNT + 1];
    
    for (int i = 1; i <= PARTY_COUNT; i++) {
      st = new StringTokenizer(br.readLine());

      for (int j = 1; j <= PARTY_COUNT; j++) {
        dp[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    for (int i = 1; i <= PARTY_COUNT; i++) {
      for (int j = 1; j <= PARTY_COUNT; j++) {
        for (int k = 1; k <= PARTY_COUNT; k++) {
          dp[j][k] = Math.min(dp[j][k], dp[j][i] + dp[i][k]);
        }
      }
    }

    for (int i = 0; i < CLIENT_COUNT; i++) {
      st = new StringTokenizer(br.readLine());

      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());

      if(cost >= dp[from][to]) {
        System.out.println("Enjoy other party");
      } else {
        System.out.println("Stay here");
      }
    }
  }
}

