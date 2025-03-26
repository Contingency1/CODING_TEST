import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

  static ArrayList<Integer> arr;
  static boolean[][] matrix;
  static int count = 0;
  static boolean[] visited;

  private static void check(int n) {
    boolean flag = false;

    for (int i = matrix.length - 1; i >= 1; i--) {
      if (matrix[n][i] && !visited[i]) {
        arr.add(n);
//        System.out.println(n + " " + i);
        matrix[n][i] = false;
        matrix[i][n] = false;
        flag = true;
        visited[i] = true;
        count++;
        check(i);
      }
    }

    if (!flag) {
      if (!arr.isEmpty()) {
        arr.remove(arr.size() - 1);
      }

      if (!arr.isEmpty()) {
        check(arr.get(arr.size() - 1));
      }
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine());
    int M = Integer.parseInt(br.readLine());

    matrix = new boolean[N + 1][N + 1];
    visited = new boolean[N + 1];

    for (int i = 1; i <= M; i++) {
      int[] values = Arrays.stream(br.readLine().split(" "))
          .mapToInt(Integer::parseInt).toArray();

      matrix[values[0]][values[1]] = true;
      matrix[values[1]][values[0]] = true;
    }

    arr = new ArrayList<>();

//    for (boolean[] row : matrix) {
//      System.out.println((Arrays.toString(row)));
//    }

    boolean check = true;
    for (int i = 0; i < N + 1; i++) {
      // 1번째에 이어져있는게 없다면
      if (matrix[1][i]) {
        check = false;
      }
    }

    if (check) {
      System.out.print("0");
      return;
    }
    visited[1] = true;
    check(1);

    sb.append(count);

    System.out.print(sb);
  }
}