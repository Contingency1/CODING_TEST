import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine());

    int[][] matrix = new int[N + 1][N + 1];

    for (int i = 1; i <= N; i++) {
      int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

      for (int j = 1; j <= N; j++) {
        matrix[i][j] = line[j - 1];
      }
    }

    ArrayDeque<Location> queue = new ArrayDeque<>();
    queue.add(new Location(1, 2, 1));

    int answer = 0;

    while (!queue.isEmpty()) {
      Location location = queue.poll();
      int row = location.row, col = location.col;
      int status = location.status;

      if (row == N && col == N) {
        answer++;
      }

      switch (status) {
        // 가로
        case 1:
          if (col + 1 <= N) {
            if (matrix[row][col + 1] == 0) {
              // 가로 추가
              queue.add(new Location(row, col + 1, 1));
            }

            if (row + 1 <= N) {
              if (matrix[row + 1][col + 1] == 0 && matrix[row][col + 1] == 0
                  && matrix[row + 1][col] == 0) {
                // 대각 추가
                queue.add(new Location(row + 1, col + 1, 2));
              }
            }
          }

          break;
        // 대각
        case 2:
          if (col + 1 <= N) {
            if (matrix[row][col + 1] == 0) {
              // 가로 추가
              queue.add(new Location(row, col + 1, 1));
            }
          }

          if (row + 1 <= N) {
            if (matrix[row + 1][col] == 0) {
              //세로 추가
              queue.add(new Location(row + 1, col, 3));
            }

            if (col + 1 <= N) {
              if (matrix[row + 1][col + 1] == 0 && matrix[row][col + 1] == 0
                  && matrix[row + 1][col] == 0) {
                // 대각 추가
                queue.add(new Location(row + 1, col + 1, 2));
              }
            }
          }

          break;
        // 세로
        case 3:
          if (row + 1 <= N) {
            if (matrix[row + 1][col] == 0) {
              // 세로 추가
              queue.add(new Location(row + 1, col, 3));
            }

            if (col + 1 <= N) {
              if (matrix[row + 1][col + 1] == 0 && matrix[row][col + 1] == 0
                  && matrix[row + 1][col] == 0) {
                // 대각 추가
                queue.add(new Location(row + 1, col + 1, 2));
              }
            }

          }

      }

    }

    sb.append(answer);

    System.out.print(sb);
  }

  static class Location {

    int row, col;
    // 1은 가로, 2는 대각선, 3은 세로
    int status;

    Location(int row, int col, int status) {
      this.row = row;
      this.col = col;
      this.status = status;
    }
  }

}
