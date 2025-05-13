import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {


  static int ROW_COUNT;
  static int COLUMN_COUNT;
  static int[][] offset;
  static int[][] arr;
  static int[] answer = new int[2];
  static int[][] memo;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    String[] str = br.readLine().split(" ");

    ROW_COUNT = Integer.parseInt(str[0]);
    COLUMN_COUNT = Integer.parseInt(str[1]);

    arr = new int[ROW_COUNT][COLUMN_COUNT];
    answer = new int[]{ROW_COUNT - 1, COLUMN_COUNT - 1};

    for (int i = 0; i < ROW_COUNT; i++) {
      int[] input = Arrays.stream(br.readLine().split(" "))
          .mapToInt(Integer::parseInt).toArray();

      arr[i] = input;
    }

//    for (int i = 0; i < ROW_COUNT; i++) {
//      sb.append(Arrays.toString(arr[i])).append("\n");
//    }

    offset = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    memo = new int[ROW_COUNT][COLUMN_COUNT];

    for (int[] m : memo) {
      Arrays.fill(m, -1);
    }

    sb.append(dfs(0, 0));
    System.out.print(sb);
  }

  static int dfs(int row, int column) {
    if (row == answer[0] && column == answer[1]) {
      return 1;
    }

    if (memo[row][column] != -1) {
      return memo[row][column];
    }

    memo[row][column] = 0;

    for (int[] set : offset) {
      int nextRow = row + set[0];
      int nextColumn = column + set[1];

      if (nextRow < 0 || nextRow >= ROW_COUNT || nextColumn < 0 || nextColumn >= COLUMN_COUNT) {
        continue;
      }

      if (arr[row][column] <= arr[nextRow][nextColumn]) {
        continue;
      }

      memo[row][column] += dfs(nextRow, nextColumn);
    }

    return memo[row][column];
  }

}
