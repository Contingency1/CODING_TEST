import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

  static int white = 0;
  static int black = 0;
  static int[][] matrix;

  private static void method(int amount, int x, int y) {
    if (amount == 1) {
      if (matrix[x][y] == 0) {
        white++;
      } else {
        black++;
      }

      return;
    } else {

      int key = matrix[x][y];

      for (int i = x; i < x + amount; i++) {
        for (int j = y; j < y + amount; j++) {
          if (matrix[i][j] != key) {
            method(amount / 2, x, y);
            method(amount / 2, x + amount / 2, y);
            method(amount / 2, x, y + amount / 2);
            method(amount / 2, x + amount / 2, y + amount / 2);
            return;
          }
        }
      }
    }

    if (matrix[x][y] == 0) {
      white++;
    } else {
      black++;
    }

  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    // 2, 4, 8, 16, 32, 64, 128
    int N = Integer.parseInt(br.readLine());
    matrix = new int[N][N];

    for (int i = 0; i < N; i++) {
      matrix[i] = Arrays.stream(br.readLine().split(" "))
          .mapToInt(Integer::parseInt).toArray();
    }

    int key = matrix[0][0];

    for (int i = 0; i < N; i++) {
      boolean boo = false;
      for (int j = 0; j < N; j++) {
        if (key != matrix[i][j]) {
          method(N / 2, 0, 0);
          method(N / 2, N / 2, 0);
          method(N / 2, 0, N / 2);
          method(N / 2, N / 2, N / 2);
          boo = true;
          break;
        }
      }

      if (boo) {
        break;
      }
    }

    if (white == 0 && black == 0) {
      if (key == 0) {
        white++;
      } else {
        black++;
      }
    }

    sb.append(white).append("\n").append(black);

    System.out.print(sb);
  }
}

