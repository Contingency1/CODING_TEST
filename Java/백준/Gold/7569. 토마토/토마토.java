import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

public class Main {


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    int col = input[0];
    int row = input[1];
    int hei = input[2];

    int[][][] matrix = new int[hei][row][col];

    for (int i = 0; i < hei; i++) {
      for (int j = 0; j < row; j++) {
        matrix[i][j] = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt).toArray();
      }
    }

    int left = 0;

    ArrayDeque<int[]> queue = new ArrayDeque<>();

    for (int i = 0; i < hei; i++) {
      for (int j = 0; j < row; j++) {
        for (int k = 0; k < col; k++) {
          if (matrix[i][j][k] == 0) {
            left++;
          } else if (matrix[i][j][k] == 1) {
            queue.add(new int[]{i, j, k, 0});
          }
        }
      }
    }

    int[][] offset = {
        {1, 0, 0}, {0, 1, 0}, {0, 0, 1},
        {-1, 0, 0}, {0, -1, 0}, {0, 0, -1},
    };

    int answer = 0;

    while (!queue.isEmpty()) {
      int[] point = queue.poll();
      int curHei = point[0];
      int curRow = point[1];
      int curCol = point[2];
      int count = point[3];

      if (left == 0) {
        answer = count;
      }

      for (int[] buf : offset) {
        int newHei = curHei + buf[0];
        int newRow = curRow + buf[1];
        int newCol = curCol + buf[2];

        if (newHei >= 0 && newRow >= 0 && newCol >= 0
            && newHei < hei && newRow < row && newCol < col) {
          if (matrix[newHei][newRow][newCol] == 0) {
            queue.add(new int[]{newHei, newRow, newCol, count + 1});
            matrix[newHei][newRow][newCol] = 1;
            left--;
          }
        }
      }
    }

    if (left > 0) {
      sb.append(-1);
    } else {
      sb.append(answer);
    }

    System.out.print(sb);
  }
}