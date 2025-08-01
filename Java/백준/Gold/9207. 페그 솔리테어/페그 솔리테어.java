import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

  static int[][] offset = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    final int N = Integer.parseInt(br.readLine());

    for (int i = 0; i < N; i++) {
      char[][] grid = new char[5][9];
      int moveCount = 0;
      int wholePins = 0;

      for (int j = 0; j < 5; j++) {
        grid[j] = br.readLine().toCharArray();
      }

      br.readLine();

      for (int row = 0; row < grid.length; row++) {
        for (int col = 0; col < grid[0].length; col++) {
          char[][] bufferGrid = copyArr(grid);

          if (grid[row][col] == 'o') {
            wholePins++;

            for (int[] off : offset) {
              int rmRow = row + off[0];
              int rmCol = col + off[1];
              int toRow = row + off[0] * 2;
              int toCol = col + off[1] * 2;

              if (isValid(rmRow, rmCol) && isValid(toRow, toCol) &&
                  grid[rmRow][rmCol] == 'o' && grid[toRow][toCol] == '.') {

                bufferGrid[row][col] = '.';
                bufferGrid[rmRow][rmCol] = '.';
                bufferGrid[toRow][toCol] = 'o';

                moveCount = Math.max(moveCount, back(bufferGrid, 1));

                bufferGrid[row][col] = 'o';
                bufferGrid[rmRow][rmCol] = 'o';
                bufferGrid[toRow][toCol] = '.';
              }
            }
          }
        }
      }

      int leftPins = wholePins - moveCount;
      System.out.println(leftPins + " " + moveCount);
    }


  }

  static boolean isValid(int row, int col) {
    return row >= 0 && row < 5 && col >= 0 && col < 9;
  }

  static char[][] copyArr(char[][] original) {
    char[][] result = new char[original.length][9];

    for (int i = 0; i < original.length; i++) {
      System.arraycopy(original[i], 0, result[i], 0, original[i].length);
    }

    return result;
  }

  private static int back(char[][] grid, int count) {

    int moveCount = 0;
    boolean noting = true;

    for (int row = 0; row < grid.length; row++) {
      for (int col = 0; col < grid[0].length; col++) {

        if (grid[row][col] == 'o') {
          for (int[] off : offset) {
            int rmRow = row + off[0];
            int rmCol = col + off[1];
            int toRow = row + off[0] * 2;
            int toCol = col + off[1] * 2;

            if (isValid(rmRow, rmCol) && isValid(toRow, toCol) &&
                grid[rmRow][rmCol] == 'o' && grid[toRow][toCol] == '.') {
              noting = false;

              grid[row][col] = '.';
              grid[rmRow][rmCol] = '.';
              grid[toRow][toCol] = 'o';

              moveCount = Math.max(moveCount, back(grid, count + 1));

              grid[row][col] = 'o';
              grid[rmRow][rmCol] = 'o';
              grid[toRow][toCol] = '.';
            }
          }
        }
      }
    }

    if (noting) {
      return count;
    }

    return moveCount;
  }


}