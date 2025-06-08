import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    StringTokenizer st = new StringTokenizer(br.readLine());

    int size = Integer.parseInt(st.nextToken());
    // 최대 1000억
    long square = Long.parseLong(st.nextToken());

    int[][] matrix = new int[size][size];

    for (int i = 0; i < size; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < size; j++) {
        matrix[i][j] = Integer.parseInt(st.nextToken()) % 1000;
      }
    }
    br.close();

    if (square == 1) {
      print(matrix, sb);
      System.out.print(sb);
      return;
    }

    boolean flag = (square & 1) == 1;
    square >>= 1;

    int[][] buffer = new int[size][size];
    for (int i = 0; i < size; i++) {
      System.arraycopy(matrix[i], 0, buffer[i], 0, size);
    }
    boolean isAnswerInit = false;
    int[][] answer = new int[size][size];

    while (square != 0) {
      buffer = calculate(buffer, buffer);
      if ((square & 1) == 1) {
        if (isAnswerInit) {
          answer = calculate(answer, buffer);
        } else {
          for (int i = 0; i < size; i++) {
            System.arraycopy(buffer[i], 0, answer[i], 0, size);
          }
          isAnswerInit = true;
        }
      }

      square >>= 1;
    }

    if (flag) {
      answer = calculate(answer, matrix);
    }

    print(answer, sb);
    System.out.print(sb);
  }

  private static void print(int[][] answer, StringBuilder sb) {
    for (int[] ints : answer) {
      for (int anInt : ints) {
        sb.append(anInt).append(" ");
      }
      sb.deleteCharAt(sb.length() - 1).append("\n");
    }

    sb.deleteCharAt(sb.length() - 1);
  }

  private static int[][] calculate(int[][] input1, int[][] input2) {
    int[][] matrix = new int[input1.length][input1.length];

    for (int i = 0; i < input1.length; i++) {
      for (int j = 0; j < input1.length; j++) {
        for (int k = 0; k < input1.length; k++) {
          matrix[i][j] += input1[i][k] * input2[k][j];
        }
        matrix[i][j] %= 1000;
      }
    }

    return matrix;
  }
}