import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  static long[][] matrix;

  private static long method(int k, int left) {

    if (matrix[k][left] != -1) {
      return matrix[k][left];
    }

    if (left == 0) {
      matrix[k][left] = 1;
      return 1;
    }

    if (k == 0) {
      matrix[k][left] = method(k + 1, left - 1) % 1_000_000_000;
    } else if (k == 9) {
      matrix[k][left] = method(k - 1, left - 1) % 1_000_000_000;
    } else {
      matrix[k][left] =
          method(k - 1, left - 1) % 1_000_000_000 + method(k + 1, left - 1) % 1_000_000_000;
    }

    return matrix[k][left];
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine());
    matrix = new long[10][N];
    long answer = 0;

    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < N; j++) {
        matrix[i][j] = -1;
      }
    }

    for (int j = 1; j <= 9; j++) {
      answer += method(j, N - 1);
    }

    sb.append(answer % 1_000_000_000);

    System.out.print(sb);
  }
}
