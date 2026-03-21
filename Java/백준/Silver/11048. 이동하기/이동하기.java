import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  static int[][] offSet = {{0, -1}, {-1, 0}, {-1, -1}};

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    int row = Integer.parseInt(st.nextToken());
    int col = Integer.parseInt(st.nextToken());

    int[][] input = new int[row][col];

    for (int i = 0; i < row; i++) {
      st = new StringTokenizer(br.readLine());

      for (int j = 0; j < col; j++) {
        input[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int[][] dp = new int[row][col];
    dp[0][0] = input[0][0];

    int max = Math.max(row - 1, col - 1);

    for (int i = 1; i < row; i++) {
      dp[i][0] = input[i][0] + dp[i - 1][0];
    }

    for (int i = 1; i < col; i++) {
      dp[0][i] = input[0][i] + dp[0][i - 1];
    }

    for (int i = 1; i < row; i++) {
      for (int j = 1; j < col; j++) {
        for (int[] off : offSet) {
          int newRow = i + off[0];
          int newCol = j + off[1];

          dp[i][j] = Math.max(dp[i][j], input[i][j] + dp[newRow][newCol]);
        }
      }
    }

    System.out.println(dp[row - 1][col - 1]);
  }

}