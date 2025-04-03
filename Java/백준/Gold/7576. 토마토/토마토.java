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

    int col = input[0];
    int row = input[1];

    int[][] offset = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    int[][] matrix = new int[row][col];

    for (int i = 0; i < matrix.length; i++) {
      matrix[i] = Arrays.stream(br.readLine().split(" "))
          .mapToInt(Integer::parseInt).toArray();
    }

    ArrayDeque<int[]> arr = new ArrayDeque<>();

    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        if (matrix[i][j] == 1) {
          arr.add(new int[]{i, j, 0});
        }
      }
    }

    int max = 0;

    while (!arr.isEmpty()) {
      int[] cur = arr.poll();
      int curRow = cur[0];
      int curCol = cur[1];
      int count = cur[2];

      for (int i = 0; i < offset.length; i++) {
        int newRow = curRow + offset[i][0];
        int newCol = curCol + offset[i][1];

        if (newRow >= 0 && newCol >= 0
            && newRow < matrix.length
            && newCol < matrix[newRow].length
            && matrix[newRow][newCol] == 0
        ) {
          matrix[newRow][newCol] = 1;
          arr.add(new int[]{newRow, newCol, count + 1});
          max = Math.max(max, count + 1);
        }
      }
    }

    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        if (matrix[i][j] == 0) {
          max = -1;
          break;
        }
      }
    }

    sb.append(max);

    System.out.print(sb);
  }
}