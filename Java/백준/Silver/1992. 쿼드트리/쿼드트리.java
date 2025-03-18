import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

  static int[][] base;

  private static void method(int amount, int x, int y, boolean isFirst, boolean isLast,
      StringBuilder sb) {

    int start = base[x][y];
    boolean flag = false;

    if (isFirst) {
      sb.append("(");
    }

    for (int i = x; i < x + amount; i++) {
      for (int j = y; j < y + amount; j++) {
        if (base[i][j] != start) {
          method(amount / 2, x, y, true, false, sb);
          method(amount / 2, x, y + amount / 2, false, false, sb);
          method(amount / 2, x + amount / 2, y, false, false, sb);
          method(amount / 2, x + amount / 2, y + amount / 2, false, true, sb);

          flag = true;
          break;
        }
      }
      if (flag) {
        break;
      }
    }

    if (!flag) {
      sb.append(base[x][y]);
    }

    if (isLast) {
      sb.append(")");
    }

  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine());

    base = new int[N][N];

    for (int i = 0; i < N; i++) {
      base[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
    }

    int start = base[0][0];

    boolean flag = false;

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (base[i][j] != start) {
          method(N / 2, 0, 0, true, false, sb);
          method(N / 2, 0, N / 2, false, false, sb);
          method(N / 2, N / 2, 0, false, false, sb);
          method(N / 2, N / 2, N / 2, false, true, sb);
          flag = true;
          break;
        }
      }

      if (flag) {
        break;
      }
    }

    if (!flag) {
      sb.append(base[0][0]);
    }

    System.out.print(sb);
  }
}

