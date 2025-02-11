import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    String[] input = br.readLine().split(" ");
    int N = Integer.parseInt(input[0]); // 표의 크기 N
    int M = Integer.parseInt(input[1]); // 구해야 하는 횟수 M

    int[][] field = new int[N][N];

    for (int i = 0; i < N; i++) {
      field[i] = Arrays.stream(br.readLine().split(" "))
          .mapToInt(Integer::parseInt).toArray();
    }

    int[][] inputArr = new int[M][4];

    for (int i = 0; i < M; i++) {
      inputArr[i] = Arrays.stream(br.readLine().split(" "))
          .mapToInt(Integer::parseInt).toArray();
    }

    int[][] acc = new int[N][N];

    for (int i = 0; i < N; i++) {
      acc[i][0] = field[i][0];
      for (int j = 1; j < N; j++) {
        acc[i][j] = acc[i][j - 1] + field[i][j];
      }
    }

    // 누적합 출력
//    for (int[] row : acc) {
//      sb.append(Arrays.toString(row)).append("\n");
//    }
//
//    // 구해야할 좌표 줄력
//    for (int[] row : inputArr) {
//      sb.append(Arrays.toString(row)).append("\n");
//    }

    for (int i = 0; i < inputArr.length; i++) {
      int answer = 0;

      int x1, x2, y1, y2;

      x1 = inputArr[i][1] - 1;
      y1 = inputArr[i][0] - 1;
      x2 = inputArr[i][3] - 1;
      y2 = inputArr[i][2] - 1;

//      sb.append(x1).append(" ").append(y1).append(" ").append(x2).append(" ").append(y2)
//          .append("\n");

      for (int j = y1; j <= y2; j++) {
        answer += x1 - 1 >= 0 ? acc[j][x2] - acc[j][x1 - 1]
            : acc[j][x2];
      }

      sb.append(answer).append("\n");
    }

    System.out.print(sb);
  }
}
