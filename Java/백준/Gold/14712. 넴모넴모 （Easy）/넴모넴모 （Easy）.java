import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  static int ROW_COUNT, COL_COUNT;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    ROW_COUNT = Integer.parseInt(st.nextToken());
    COL_COUNT = Integer.parseInt(st.nextToken());

    boolean[][] board = new boolean[ROW_COUNT][COL_COUNT];

    int answer = 0;

    for (int r = 0; r < ROW_COUNT; r++) {
      for (int c = 0; c < COL_COUNT; c++) {
        board[r][c] = true;
        answer += back(board, r, c);
        board[r][c] = false;
      }
    }

    System.out.print(answer + 1);
  }

  static int back(boolean[][] board, int row, int col) {
    int result = 0;

    if (isRect(board, row, col)) {
      return 0;
    }

    result++;

    for (int r = row; r < ROW_COUNT; r++) {
      for (int c = col + 1; c < COL_COUNT; c++) {
        board[r][c] = true;
        result += back(board, r, c);
        board[r][c] = false;
      }
      col = -1;
    }

    return result;
  }

  static boolean isRect(boolean[][] board, int r, int c) {
    if (ROW_COUNT == 1 || COL_COUNT == 1) {
      return false;
    }

    if (r + 1 < ROW_COUNT) {
      if (c + 1 < COL_COUNT && board[r + 1][c] && board[r][c + 1] && board[r + 1][c + 1]) {
        return true;
      }

      if (c - 1 >= 0 && board[r + 1][c - 1] && board[r + 1][c] && board[r][c - 1]) {
        return true;
      }
    }

    if (r - 1 >= 0) {
      if (c + 1 < COL_COUNT && board[r - 1][c] && board[r][c + 1] && board[r - 1][c + 1]) {
        return true;
      }

      if (c - 1 >= 0 && board[r - 1][c - 1] && board[r - 1][c] && board[r][c - 1]) {
        return true;
      }
    }

    return false;
  }
}
