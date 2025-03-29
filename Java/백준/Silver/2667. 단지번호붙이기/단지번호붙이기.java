import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

  private static int find(int[][] matrix, int i, int j) {
    matrix[i][j] = 0;
    int count = 1;

    if (matrix[i - 1][j] == 1) {
      count += find(matrix, i - 1, j);
    }

    if (matrix[i][j - 1] == 1) {
      count += find(matrix, i, j - 1);
    }

    if (i + 1 < matrix.length && matrix[i + 1][j] == 1) {
      count += find(matrix, i + 1, j);
    }

    if (j + 1 < matrix.length && matrix[i][j + 1] == 1) {
      count += find(matrix, i, j + 1);
    }

    return count;
  }


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine());
    int[][] matrix = new int[N + 1][N + 1];
    PriorityQueue<Integer> queue = new PriorityQueue<>();

    for (int i = 1; i <= N; i++) {
      int[] buffer = Arrays.stream(br.readLine().split(""))
          .mapToInt(Integer::parseInt).toArray();

      for (int j = 1; j <= N; j++) {
        matrix[i][j] = buffer[j - 1];
      }
    }

//    for (int[] row : matrix) {
//      sb.append(Arrays.toString(row)).append("\n");
//    }

    int danji = 0;

    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        if (matrix[i][j] == 1) {
          danji++;
          queue.add(find(matrix, i, j));
        }
      }
    }

    sb.append(danji).append("\n");

    for (int i = 0; i < danji; i++) {
      sb.append(queue.poll()).append("\n");
    }

    System.out.print(sb);
  }
}