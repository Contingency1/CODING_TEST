import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

public class Main {


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int[] input = Arrays.stream(br.readLine().split(" "))
        .mapToInt(Integer::parseInt).toArray();

    int rowCount = input[0];
    int colCount = input[1];

    int[][] matrix = new int[rowCount + 1][colCount + 1];

    for (int[] row : matrix) {
      Arrays.fill(row, -3);
    }

    for (int i = 1; i <= rowCount; i++) {
      int[] row = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
      for (int j = 1; j <= colCount; j++) {
        matrix[i][j] = row[j - 1];
      }
    }

    ArrayDeque<int[]> queue = new ArrayDeque<>();
    boolean[][][] visited = new boolean[rowCount + 1][colCount + 1][2];

    // 0 : row, 1 : col, 2 : count, 3 : choice
    queue.add(new int[]{1, 1, 1, 1});
    visited[1][1][0] = true;

    int[][] offset = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    int answer = -1;

    boolean flag = true;
    while (!queue.isEmpty()) {

      int[] cur = queue.poll();
      int row = cur[0];
      int col = cur[1];
      int count = cur[2];
      int choice = cur[3];

      if (row == rowCount && col == colCount) {
        if (flag) {
          answer = count;
          flag = false;
        } else {
          answer = Math.min(answer, count);
        }
      }

      for (int[] plus : offset) {
        int newRow = row + plus[0];
        int newCol = col + plus[1];
        if (newRow > 0 && newCol > 0 && newRow <= rowCount && newCol <= colCount) {
          if (choice == 1) { // 벽을 부순적이 없는 경우
            if (matrix[newRow][newCol] == 1 && !visited[newRow][newCol][1]) {
              visited[newRow][newCol][1] = true;
              queue.add(new int[]{newRow, newCol, count + 1, 0});
            } else if (matrix[newRow][newCol] == 0 && !visited[newRow][newCol][0]) {
              visited[newRow][newCol][0] = true;
              queue.add(new int[]{newRow, newCol, count + 1, 1});
            }
          } else if (choice == 0) { // 벽을 부순적이 있는 경우
            if (matrix[newRow][newCol] == 0 && !visited[newRow][newCol][1]) {
              visited[newRow][newCol][1] = true;
              queue.add(new int[]{newRow, newCol, count + 1, 0});
            }
          }
        }
      }
    }

    sb.append(answer);

    System.out.print(sb);
  }
}