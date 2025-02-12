import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 아니 2차원 누적합을 어떻게 혼자 유추해서 풀란거냐 처음 접하면 개뺑이 칠수밖에 없구만;;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int[] input = Arrays.stream(br.readLine().split(" "))
        .mapToInt(Integer::parseInt).toArray();

    int N = input[0]; // 세로
    int M = input[1]; // 가로
    int K = input[2]; // Field

    char[][] chars = new char[N][M];

    for (int i = 0; i < N; i++) {
      chars[i] = br.readLine().toCharArray();
    }

    int[][] B = new int[N][M];
    int[][] W = new int[N][M];

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (i % 2 == 0) {       // 0, 2, 4, 6
          judge(chars, W, B, i, j);
        } else {                // 1, 3, 5, 7
          judge(chars, B, W, i, j);
        }
      }
    }

//    for (int i = 0; i < N; i++) {
//      sb.append(Arrays.toString(B[i])).append("\n");
//      sb.append(Arrays.toString(W[i])).append("\n");
//    }

//    sb.append("----").append("\n");

    int[][] Bacc = new int[N][M];
    int[][] Wacc = new int[N][M];

    Bacc[0][0] = B[0][0];
    Wacc[0][0] = W[0][0];

    for (int i = 1; i < M; i++) {
      Wacc[0][i] = Wacc[0][i - 1] + W[0][i];
      Bacc[0][i] = Bacc[0][i - 1] + B[0][i];
    }

    for (int i = 1; i < N; i++) {
      Wacc[i][0] = Wacc[i - 1][0] + W[i][0];
      Bacc[i][0] = Bacc[i - 1][0] + B[i][0];
    }

    for (int i = 1; i < N; i++) {
      for (int j = 1; j < M; j++) {
        Wacc[i][j] = W[i][j] + Wacc[i - 1][j] + Wacc[i][j - 1] - Wacc[i - 1][j - 1];
        Bacc[i][j] = B[i][j] + Bacc[i - 1][j] + Bacc[i][j - 1] - Bacc[i - 1][j - 1];
      }
    }

//    for (int i = 0; i < N; i++) {
//      sb.append(Arrays.toString(Bacc[i])).append("\n");
//      sb.append(Arrays.toString(Wacc[i])).append("\n");
//    }

    int min = Integer.MAX_VALUE;

    for (int i = 0; i <= N - K; i++) {
      for (int j = 0; j <= M - K; j++) {
        min = Math.min(min, Math.min(calculation(Bacc, i, j, i + K - 1, j + K - 1),
            calculation(Wacc, i, j, i + K - 1, j + K - 1)));
      }
    }

    sb.append(min);
    System.out.print(sb);
  }

  private static void judge(char[][] chars, int[][] b, int[][] w, int i, int j) {
    if (j % 2 == 0) { // 0, 2, 4, 6
      if (chars[i][j] == 'B') {
        b[i][j]++;
      } else {
        w[i][j]++;
      }
    } else { // 1, 3, 5, 7
      if (chars[i][j] == 'B') {
        w[i][j]++;
      } else {
        b[i][j]++;
      }
    }
  }

//  sum(r1, c1, r2, c2) =
//  prefix[r2][c2]      // (0,0)부터 (r2,c2)까지의 합
//      - prefix[r1-1][c2]    // 위쪽 부분 제외
//      - prefix[r2][c1-1]    // 왼쪽 부분 제외
//      + prefix[r1-1][c1-1]  // 중복 제외한 부분 복구

  private static int calculation(int[][] intArr, int x1, int y1, int x2, int y2) {
    return intArr[x2][y2] -
        (x1 > 0 ? intArr[x1 - 1][y2] : 0)
        - (y1 > 0 ? intArr[x2][y1 - 1] : 0) +
        (x1 > 0 && y1 > 0 ? intArr[x1 - 1][y1 - 1] : 0);
  }
}
