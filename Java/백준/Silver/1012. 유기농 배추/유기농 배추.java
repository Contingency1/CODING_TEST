import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

  private static void find(boolean[][] matrix, int y, int x) {
    matrix[y][x] = false;

    if (y + 1 < matrix.length && matrix[y + 1][x]) {
      find(matrix, y + 1, x);
    }

    if (x + 1 < matrix[0].length && matrix[y][x + 1]) {
      find(matrix, y, x + 1);
    }

    if (y > 0 && matrix[y - 1][x]) {
      find(matrix, y - 1, x);
    }

    if (x > 0 && matrix[y][x - 1]) {
      find(matrix, y, x - 1);
    }

  }


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int T = Integer.parseInt(br.readLine());
    for (int i = 0; i < T; i++) {
      int[] ints = Arrays.stream(br.readLine().split(" "))
          .mapToInt(Integer::parseInt).toArray();

      int M = ints[0];
      int N = ints[1];
      int K = ints[2];

      boolean[][] matrix = new boolean[N][M];

      for (int j = 0; j < K; j++) {
        int[] points = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt).toArray();

        matrix[points[1]][points[0]] = true;
      }

      int answer = 0;

      for (int y = 0; y < N; y++) {
        for (int x = 0; x < M; x++) {
          if (matrix[y][x]) {
            answer++;
            find(matrix, y, x);
          }
        }
      }

      sb.append(answer).append("\n");
    }

    System.out.print(sb);
  }
}