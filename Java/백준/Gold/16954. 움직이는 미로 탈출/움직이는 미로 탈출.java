import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    boolean[][][] matrix = new boolean[9][9][9];

    for (int i = 1; i < 9; i++) {
      String[] str = br.readLine().split("");
      for (int j = 0; j < 8; j++) {
        matrix[0][i][j + 1] = str[j].equals("#");
      }
    }

    for (int i = 1; i < 9; i++) {
      matrix[i] = tick(matrix[i - 1]);
    }

//    for (int i = 0; i < 9; i++) {
//      System.out.println("엄: " + i + "===============");
//      for (boolean[] row : matrix[i]) {
//        System.out.println(Arrays.toString(row));
//      }
//    }

    int[][] offset = new int[][]
        {{0, 0},
            {1, 0}, {0, 1}, {-1, 0}, {0, -1},
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    int startRow = 8, startCol = 1;
    int targetRow = 1, targetCol = 8;
    int count = 0;

    ArrayDeque<int[]> queue = new ArrayDeque<>();
    boolean[][][] visited = new boolean[9][9][9];

    queue.add(new int[]{startRow, startCol, count});
    visited[startRow][startCol][count] = true;

    int answer = 0;

    while (!queue.isEmpty()) {
      int[] cur = queue.poll();
      int curRow = cur[0];
      int curCol = cur[1];
      int curCount = cur[2];

      if (curRow == targetRow && curCol == targetCol) {
        answer = 1;
        break;
      }

      for (int[] set : offset) {
        int plusRow = curRow + set[0];
        int plusCol = curCol + set[1];
        int plusCount = curCount == 8 ? curCount : curCount + 1;

        if (plusRow > 0 && plusCol > 0 && plusRow < 9 && plusCol < 9) {
          if (!matrix[curCount][plusRow][plusCol]) {
            if (plusRow == 1) {
              if (!visited[plusRow][plusCol][plusCount]) {
                visited[plusRow][plusCol][plusCount] = true;
                queue.add(new int[]{plusRow, plusCol, plusCount});
              }
            } else {
              if (!matrix[curCount][plusRow - 1][plusCol]) {

                if (!visited[plusRow][plusCol][plusCount]) {
                  visited[plusRow][plusCol][plusCount] = true;
                  queue.add(new int[]{plusRow, plusCol, plusCount});
                }
              }
            }
          }
        }
      }
    }

    sb.append(answer);

    System.out.print(sb);
  }


  static boolean[][] tick(boolean[][] matrix) {
    boolean[][] newMatrix = new boolean[9][9];

    for (int i = 8; i > 0; i--) {
      newMatrix[i] = matrix[i - 1];
    }

    return newMatrix;
  }


}
