import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    final int ROW_COUNT = Integer.parseInt(st.nextToken());
    final int COL_COUNT = Integer.parseInt(st.nextToken());
    final int ROTATE_COUNT = Integer.parseInt(st.nextToken());

    final int LAYER_COUNT = Math.min(ROW_COUNT, COL_COUNT) / 2;

    int[][] arr = new int[ROW_COUNT][COL_COUNT];

    for (int i = 0; i < ROW_COUNT; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < COL_COUNT; j++) {
        arr[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    for (int i = 0; i < ROTATE_COUNT; i++) {
      for (int j = 0; j < LAYER_COUNT; j++) {
        rotate(arr, ROW_COUNT, COL_COUNT, j);
      }
    }

    print(ROW_COUNT, COL_COUNT, arr);
  }

  static void print(int ROW_COUNT, int COL_COUNT, int[][] arr) {
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < ROW_COUNT; i++) {
      for (int j = 0; j < COL_COUNT; j++) {
        sb.append(arr[i][j]).append(" ");
      }

      sb.deleteCharAt(sb.length() - 1);
      sb.append("\n");
    }
    sb.deleteCharAt(sb.length() - 1);

    System.out.print(sb);
  }

  static void rotate(int[][] arr, int r, int c, int layer) {
    int buffer = arr[layer][layer];

    for (int i = layer; i < c - 1 - layer; i++) {
      arr[layer][i] = arr[layer][i + 1];
    }

    for (int i = layer; i < r - 1 - layer; i++) {
      arr[i][c - 1 - layer] = arr[i + 1][c - 1 - layer];
    }

    for (int i = c - 1 - layer; i > layer; i--) {
      arr[r - 1 - layer][i] = arr[r - 1 - layer][i - 1];
    }

    for (int i = r - 1 - layer; i > layer + 1; i--) {
      arr[i][layer] = arr[i - 1][layer];
    }

    arr[layer + 1][layer] = buffer;
  }
}