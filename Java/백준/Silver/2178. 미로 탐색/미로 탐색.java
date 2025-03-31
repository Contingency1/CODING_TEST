import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;

public class Main {

  static int[] plusHaeng = new int[]{1, 0, -1, 0};
  static int[] plusYeol = new int[]{0, 1, 0, -1};
  static int haeng;
  static int yeol;
  static int star = 100;
  static int[][] matrix;
  static int min = Integer.MAX_VALUE;

  private static void method(ArrayDeque<int[]> queue, HashSet<Integer> visited) {
    // x = M, y = N
    while (!queue.isEmpty()) {
      int[] cur = queue.poll();

      if (cur[0] == haeng && cur[1] == yeol) {
        min = Math.min(min, cur[2]);
        return;
      }

      for (int i = 0; i < 4; i++) {
        int Bhaeng = cur[0] + plusYeol[i];
        int Byeol = cur[1] + plusHaeng[i];

        if (Byeol > 0 && Byeol <= yeol && Bhaeng > 0 && Bhaeng <= haeng) {
          if (matrix[Bhaeng][Byeol] == 1 && !visited.contains(Bhaeng * star + Byeol)) {
            queue.add(new int[]{Bhaeng, Byeol, cur[2] + 1});
            visited.add(Bhaeng * star + Byeol);
          }
        }
      }
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int[] ints = Arrays.stream(br.readLine().split(" "))
        .mapToInt(Integer::parseInt).toArray();

    haeng = ints[0];
    yeol = ints[1];

    matrix = new int[haeng + 1][yeol + 1];

    for (int i = 1; i <= haeng; i++) {
      int[] input = Arrays.stream(br.readLine().split(""))
          .mapToInt(Integer::parseInt).toArray();

      for (int j = 1; j <= yeol; j++) {
        matrix[i][j] = input[j - 1];
      }
    }

    ArrayDeque<int[]> queue = new ArrayDeque<>();
    HashSet<Integer> visited = new HashSet<>();

    queue.add(new int[]{1, 1, 1});
    visited.add(star + 1);

    method(queue, visited);

    sb.append(min);

    System.out.print(sb);
  }
}