import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    final int X_COUNT = Integer.parseInt(st.nextToken()) + 1;
    final int Y_COUNT = Integer.parseInt(st.nextToken()) + 1;

    final int DL = Integer.parseInt(br.readLine());

    long[][] dp = new long[X_COUNT][Y_COUNT];

    Map<Dot, List<Dot>> map = new HashMap<>();

    for (int i = 0; i < DL; i++) {
      st = new StringTokenizer(br.readLine());
    
      int x1 = Integer.parseInt(st.nextToken());
      int y1 = Integer.parseInt(st.nextToken());
      int x2 = Integer.parseInt(st.nextToken());
      int y2 = Integer.parseInt(st.nextToken());

      Dot dot1 = new Dot(x1, y1);
      Dot dot2 = new Dot(x2, y2);

      if(map.containsKey(dot1)) {
        map.get(dot1).add(dot2);
      } else {
        map.put(dot1, (new ArrayList<Dot>()));
        map.get(dot1).add(dot2);
      }

      if(map.containsKey(dot2)) {
        map.get(dot2).add(dot1);
      } else {
        map.put(dot2, (new ArrayList<Dot>()));
        map.get(dot2).add(dot1);
      }
    }

    for (int i = 1; i < X_COUNT; i++) {
      Dot dot = new Dot(i, 0);

      if(map.containsKey(dot)) {
        boolean flag = false;

        for (Dot d: map.get(dot)) {
          if (d.equals(new Dot(i - 1, 0))) {
            flag = true;
            break;
          }
        }

        if (flag) {
          break;
        }
      }

      dp[i][0] = 1;
    }

    for (int i = 1; i < Y_COUNT; i++) {
      Dot dot = new Dot(0, i);

      if(map.containsKey(dot)) {
        boolean flag = false;

        for (Dot d: map.get(dot)) {
          if (d.equals(new Dot(0, i - 1))) {
            flag = true;
            break;
          }
        }

        if (flag) {
          break;
        }
      }

      dp[0][i] = 1;
    }

    for (int x = 1; x < X_COUNT; x++) {
      for (int y = 1; y < Y_COUNT; y++) {
        long x1 = dp[x - 1][y];
        long y1 = dp[x][y - 1];

        Dot cur = new Dot(x, y);
        Dot dot1 = new Dot(x - 1, y);
        Dot dot2 = new Dot(x, y - 1);

        if(map.containsKey(dot1)) {
          for (Dot d: map.get(dot1)) {
            if(cur.equals(d)) {
              x1 = 0;
              break;
            }
          }
        }

        if(map.containsKey(dot2)) {
          for (Dot d: map.get(dot2)) {
            if(cur.equals(d)) {
              y1 = 0;
              break;
            }
          }
        }

        dp[x][y] = x1 + y1;
      }
    }

    System.out.print(dp[X_COUNT - 1][Y_COUNT - 1]);
  }

  static class Dot {
    int x;
    int y;

    Dot(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object o) {
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Dot dot = (Dot) o;
      return x == dot.x && y == dot.y;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y);
    }
  }
}