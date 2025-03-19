import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

  static int[][] base;
  static int minus, zero, plus;

  private static boolean check(int amount, int x, int y) {

    int standard = base[x][y];

    for (int i = x; i < x + amount; i++) {
      for (int j = y; j < y + amount; j++) {

        if (base[i][j] != standard) {
          return false;
        }
      }
    }

    return true;
  }

  private static void divide(int amount, int x, int y) {
    if (check(amount, x, y)) {
      if (base[x][y] == 1) {
        plus++;
      } else if (base[x][y] == 0) {
        zero++;
      } else {
        minus++;
      }
    } else {
      int key = amount / 3;
      divide(key, x, y);
      divide(key, x + key, y);
      divide(key, x, y + key);

      divide(key, x + key, y + key);
      divide(key, x + key, y + 2 * key);
      divide(key, x + 2 * key, y + key);

      divide(key, x + 2 * key, y);
      divide(key, x, y + 2 * key);
      divide(key, x + 2 * key, y + 2 * key);

    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine());

    base = new int[N][N];

    for (int i = 0; i < N; i++) {
      base[i] = Arrays.stream(br.readLine().split(" "))
          .mapToInt(Integer::parseInt).toArray();
    }

    divide(N, 0, 0);

    sb.append(minus).append("\n")
        .append(zero).append("\n")
        .append(plus);

    System.out.print(sb);
  }
}

