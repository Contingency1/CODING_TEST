import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    int[] input1 = Arrays.stream(br.readLine().split(" "))
        .mapToInt(Integer::parseInt).toArray();

    int N = input1[0];
    int M = input1[1];

    int[][] arr1 = new int[N][M];

    for (int i = 0; i < N; i++) {
      arr1[i] = Arrays.stream(br.readLine().split(" "))
          .mapToInt(Integer::parseInt).toArray();
    }

    int K = Integer.parseInt(br.readLine().split(" ")[1]);

    int[][] arr2 = new int[M][K];

    for (int i = 0; i < M; i++) {
      arr2[i] = Arrays.stream(br.readLine().split(" "))
          .mapToInt(Integer::parseInt).toArray();
    }

    int[][] answer = new int[N][K];

    for (int i = 0; i < N; i++) {

      int[] row = arr1[i];
      int[] col = new int[M];

      for (int j = 0; j < K; j++) {

        int buffer = 0;

        for (int k = 0; k < M; k++) {
          col[k] = arr2[k][j];
        }

        for (int y = 0; y < row.length; y++) {
          buffer += row[y] * col[y];
        }

        answer[i][j] = buffer;
      }

    }

    for (int[] arr : answer) {
      for (int i = 0; i < arr.length; i++) {
        sb.append(arr[i]).append(" ");
      }
      sb.deleteCharAt(sb.length() - 1);

      sb.append("\n");
    }
    sb.deleteCharAt(sb.length() - 1);
    System.out.print(sb);
  }
}
