import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  static int N;
  static long answer = 0;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());

    if (N == 1) {
      System.out.println("1");
      return;
    } else if (N > 1 && N < 4) {
      System.out.println("0");
      return;
    }

    boolean[] usedCol = new boolean[N];
    boolean[] diag1 = new boolean[2 * N - 1];       // row + col
    boolean[] diag2 = new boolean[2 * N - 1];       // row - col + (N - 1)

    backTrack(0, usedCol, diag1, diag2);
    System.out.println(answer);
  }

  static void backTrack(int row, boolean[] usedCol, boolean[] diag1, boolean[] diag2) {
    if (row == N) {
      answer++;
      return;
    }

    for (int col = 0; col < N; col++) {
      if (usedCol[col] || diag1[row + col] || diag2[row - col + (N - 1)]) {
        continue;
      }

      usedCol[col] = true;
      diag1[row + col] = true;
      diag2[row - col + (N - 1)] = true;

      backTrack(row + 1, usedCol, diag1, diag2);

      usedCol[col] = false;
      diag1[row + col] = false;
      diag2[row - col + (N - 1)] = false;
    }
  }
}
