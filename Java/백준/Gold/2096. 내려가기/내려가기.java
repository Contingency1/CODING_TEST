import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    int N = Integer.parseInt(br.readLine());

    int[][] matrix = new int[N][3];
    int[][] maxDP = new int[N][3];
    int[][] minDP = new int[N][3];

    for (int i = 0; i < N; i++) {
      matrix[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    maxDP[0] = matrix[0];
    minDP[0] = matrix[0];

    for (int i = 1; i < N; i++) {

      //MAX
      maxDP[i][0] = Math.max(maxDP[i - 1][0] + matrix[i][0], maxDP[i - 1][1] + matrix[i][0]);
      maxDP[i][1] = Math.max(maxDP[i - 1][0] + matrix[i][1],
          Math.max(maxDP[i - 1][1] + matrix[i][1], maxDP[i - 1][2] + matrix[i][1]));
      maxDP[i][2] = Math.max(maxDP[i - 1][1] + matrix[i][2], maxDP[i - 1][2] + matrix[i][2]);

      //MIN
      minDP[i][0] = Math.min(minDP[i - 1][0] + matrix[i][0], minDP[i - 1][1] + matrix[i][0]);
      minDP[i][1] = Math.min(minDP[i - 1][0] + matrix[i][1],
          Math.min(minDP[i - 1][1] + matrix[i][1], minDP[i - 1][2] + matrix[i][1]));
      minDP[i][2] = Math.min(minDP[i - 1][1] + matrix[i][2], minDP[i - 1][2] + matrix[i][2]);

    }

    int MAX = 0;
    int MIN = Integer.MAX_VALUE;
    for (int i = 0; i < 3; i++) {
      MAX = Math.max(MAX, maxDP[N - 1][i]);
      MIN = Math.min(MIN, minDP[N - 1][i]);
    }

    sb.append(MAX).append(" ").append(MIN);

    System.out.print(sb);
  }

}
