import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

  static int[][] grid;
  static int rowCount, colCount;
  static int[][][] offset = {
      {{1, 0}, {0, 1}},
      {{-1, 0}, {0, -1}},
      {{1, 0}, {0, -1}},
      {{-1, 0}, {0, 1}}};

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    rowCount = Integer.parseInt(st.nextToken());
    colCount = Integer.parseInt(st.nextToken());

    grid = new int[rowCount][colCount];

    for (int i = 0; i < rowCount; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < colCount; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int answer = 0;

    for (int row = 0; row < rowCount; row++) {
      for (int col = 0; col < colCount; col++) {
        boolean[][] used = new boolean[rowCount][colCount];
        used[row][col] = true;

        for (int[][] ints : offset) {
          int newRow1 = row + ints[0][0];
          int newCol1 = col + ints[0][1];

          int newRow2 = row + ints[1][0];
          int newCol2 = col + ints[1][1];

          if (
              newRow1 < 0 || newRow1 >= rowCount || newRow2 < 0 || newRow2 > rowCount ||
                  newCol1 < 0 || newCol1 >= colCount || newCol2 < 0 || newCol2 >= colCount) {
            continue;
          }

          used[newRow1][newCol1] = true;
          used[newRow2][newCol2] = true;

          int buffer = grid[row][col] * 2 + grid[newRow1][newCol1] + grid[newRow2][newCol2];

          answer = Math.max(answer, method(row, col, used) + buffer);

          used[newRow1][newCol1] = false;
          used[newRow2][newCol2] = false;
        }
      }
    }

    System.out.print(answer);
  }

  static int method(int inputRow, int inputCol, boolean[][] used) {
    int answer = 0;

    for (int row = inputRow; row < rowCount; row++) {
      for (int col = (row == inputRow ? inputCol : 0); col < colCount; col++) {
        if (used[row][col]) {
          continue;
        }
        used[row][col] = true;

        for (int[][] ints : offset) {
          int newRow1 = row + ints[0][0];
          int newCol1 = col + ints[0][1];

          int newRow2 = row + ints[1][0];
          int newCol2 = col + ints[1][1];

          if (
              newRow1 < 0 || newRow1 >= rowCount || newRow2 < 0 || newRow2 > rowCount ||
                  newCol1 < 0 || newCol1 >= colCount || newCol2 < 0 || newCol2 >= colCount ||
                  used[newRow1][newCol1] || used[newRow2][newCol2]) {
            continue;
          }

          used[newRow1][newCol1] = true;
          used[newRow2][newCol2] = true;

          int buffer = grid[row][col] * 2 + grid[newRow1][newCol1] + grid[newRow2][newCol2];

          answer = Math.max(answer, buffer + method(row, col + 1, used));

          used[newRow1][newCol1] = false;
          used[newRow2][newCol2] = false;
        }

        used[row][col] = false;
      }
    }

    return answer;
  }
}