import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

public class Main {
  static int COUNT, REVERSE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    List<Dot> list = new ArrayList<>();
    
    String str;
    
    while ((str = br.readLine()) != null) {
      if (str.trim().isEmpty()) {
        break;
      }
    
      StringTokenizer st = new StringTokenizer(str);
    
      int white = Integer.parseInt(st.nextToken());
      int black = Integer.parseInt(st.nextToken());
    
      list.add(new Dot(white, black));
    }

    int[][][] dp = new int[list.size() + 1][16][16];

    for (int i = 1; i <= list.size(); i++) {
      Dot cur = list.get(i - 1);

      int white = cur.white;
      int black = cur.black;

      for (int w = 0; w <= 15; w++) {
        for (int b = 0; b <= 15; b++) {
          dp[i][w][b] = dp[i - 1][w][b];

          if (w > 0) {
            dp[i][w][b] = Math.max(dp[i][w][b], dp[i - 1][w - 1][b] + white);
          }

          if (b > 0) {
            dp[i][w][b] = Math.max(dp[i][w][b], dp[i - 1][w][b - 1] + black);
          }
        }
      }
    }

    System.out.print(dp[list.size()][15][15]);
  }


  static class Dot {
    int white;
    int black;

    Dot(int white, int black) {
      this.white = white;
      this.black = black;
    }

  }
}