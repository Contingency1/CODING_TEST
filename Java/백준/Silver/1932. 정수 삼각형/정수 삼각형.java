import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int n = Integer.parseInt(br.readLine());
    int[][] arr = new int[n][n];

    for (int i = 0; i < n; i++) {
      arr[i] = Arrays.stream(br.readLine().split(" "))
          .mapToInt(Integer::parseInt).toArray();
    }

    for (int i = 1; i < n; i++) {
      for (int j = 0; j < arr[i].length; j++) {
        if (j == 0) {
          arr[i][j] += arr[i - 1][j];
        } else if (j == arr[i].length - 1) {
          arr[i][j] += arr[i - 1][j - 1];
        } else {
          arr[i][j] += Math.max(arr[i - 1][j - 1], arr[i - 1][j]);
        }
      }
    }

    int answer = 0;

    for (int i = 0; i < arr[n - 1].length; i++) {
      answer = Math.max(answer, arr[n - 1][i]);
    }

    sb.append(answer);

    System.out.print(sb);
  }
}
