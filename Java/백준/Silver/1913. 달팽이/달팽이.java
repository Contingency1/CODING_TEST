import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    final int N = Integer.parseInt(br.readLine());
    final int TARGET = Integer.parseInt(br.readLine());

    int[][] arr = new int[N][N];

    int r = N / 2;
    int c = N / 2;
    int num = 1;
    int key = 1;

    while (num <= N * N) {
      for (int i = 0; i < key && num <= N * N; i++) {
        arr[r][c] = num++;
        r--;
      }
      if (num > N * N) {
        break;
      }

      for (int i = 0; i < key && num <= N * N; i++) {
        arr[r][c] = num++;
        c++;
      }
      key++;

      for (int i = 0; i < key && num <= N * N; i++) {
        arr[r][c] = num++;
        r++;
      }

      for (int i = 0; i < key && num <= N * N; i++) {
        arr[r][c] = num++;
        c--;
      }
      key++;
    }

    StringBuilder sb = new StringBuilder();

    int row = 0;
    int col = 0;

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        sb.append(arr[i][j]).append(" ");
        if (arr[i][j] == TARGET) {
          row = i + 1;
          col = j + 1;
        }
      }

      sb.deleteCharAt(sb.length() - 1).append("\n");
    }

    sb.append(row).append(" ").append(col);

    System.out.println(sb);
  }
}