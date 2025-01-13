import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine());

    int[][] matrix = new int[N][2];

    for (int i = 0; i < N; i++) {
      matrix[i] = Arrays.stream(br.readLine().split(" "))
          .mapToInt(Integer::parseInt).toArray();
    }

    Arrays.sort(matrix, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

    for (int[] row : matrix) {
      sb.append(row[0]).append(" ").append(row[1]).append("\n");
    }

    sb.deleteCharAt(sb.length() - 1);
    System.out.print(sb);
  }
}