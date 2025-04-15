import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int[] input = Arrays.stream(br.readLine().split(" "))
        .mapToInt(Integer::parseInt).toArray();

    int rowCount = input[0];
    int colCount = input[1];

    int[][] arr = new int[rowCount][colCount];

    for (int i = 0; i < rowCount; i++) {
      arr[i] = Arrays.stream(br.readLine().split(" "))
          .mapToInt(Integer::parseInt).toArray();
    }

    int targetRow = 0;
    int targetCol = 0;

    for (int i = 0; i < rowCount; i++) {
      for (int j = 0; j < colCount; j++) {

        if (arr[i][j] == 2) {
          targetRow = i;
          targetCol = j;
          break;
        }
      }
    }

    int[][] offset = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    ArrayDeque<int[]> queue = new ArrayDeque<>();

    arr[targetRow][targetCol] = 0;

    ArrayList<Integer[]> arrList = new ArrayList<>();

    for (int[] set : offset) {
      int plusRow = targetRow + set[0];
      int plusCol = targetCol + set[1];

      if (plusRow >= 0 && plusCol >= 0 && plusRow < rowCount && plusCol < colCount) {
        if (arr[plusRow][plusCol] == 1) {
          arr[plusRow][plusCol] = 1;
          queue.add(new int[]{plusRow, plusCol, 1});
          arrList.add(new Integer[]{plusRow, plusCol});
        }
      }
    }

    while (!queue.isEmpty()) {
      int[] buffer = queue.poll();

      int queueRow = buffer[0];
      int queueCol = buffer[1];
      int plusCount = buffer[2] + 1;

      for (int[] set : offset) {
        int plusRow = queueRow + set[0];
        int plusCol = queueCol + set[1];

        if (plusRow >= 0 && plusCol >= 0 && plusRow < rowCount && plusCol < colCount) {
          if (arr[plusRow][plusCol] == 1) {
            queue.add(new int[]{plusRow, plusCol, plusCount});
            arr[plusRow][plusCol] = plusCount;
          }
        }
      }
    }

    for (int i = 0; i < rowCount; i++) {
      for (int j = 0; j < colCount; j++) {
        if (arr[i][j] == 1) {
          arr[i][j] = -1;
        }
      }
    }

    for (Integer[] set : arrList) {
      arr[set[0]][set[1]] = 1;
    }

    for (int[] key : arr) {
      for (int keyValue : key) {
        sb.append(keyValue).append(" ");
      }
      sb.deleteCharAt(sb.length() - 1);
      sb.append("\n");
    }
    System.out.print(sb);
  }
}
