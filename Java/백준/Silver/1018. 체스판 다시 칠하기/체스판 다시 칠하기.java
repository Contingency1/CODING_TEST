import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

  private static int Calculation(char[][] matrix, StringBuilder sb) {
    int Bstart = 0;
    int Wstart = 0;

    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if (i % 2 == 0) { // i : 0, 2, 4, 6
          if (j % 2 == 0) { // j : 0, 2, 4 ,6
            if (matrix[i][j] != 'B') {
              Bstart++;
            } else {
              Wstart++;
            }
          } else { // j : 1, 3, 5, 7
            if (matrix[i][j] != 'W') {
              Bstart++;
            } else {
              Wstart++;
            }
          }
        } else { // i : 1, 3, 5, 7
          if (j % 2 == 0) { // j : 0, 2, 4, 6, 8
            if (matrix[i][j] != 'W') {
              Bstart++;
            } else {
              Wstart++;
            }
          } else { // j : 1, 3, 5, 7
            if (matrix[i][j] != 'B') {
              Bstart++;
            } else {
              Wstart++;
            }
          }
        }
      }
    }

    return Math.min(Wstart, Bstart);
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int[] input = Arrays.stream(br.readLine().split(" "))
        .mapToInt(Integer::parseInt)
        .toArray();

    char[][] original = br.lines()
        .map(String::toCharArray)
        .toArray(char[][]::new);

    char[][] copy = new char[8][8];
    int answer = 2500;
    int[] x = new int[input[0] - 7];
    int[] y = new int[input[1] - 7];

    for (int i = 0; i < input[0] - 7; i++) {
      x[i] = i;
    }

    for (int i = 0; i < input[1] - 7; i++) {
      y[i] = i;
    }

    for (int Xrow : x) {
      for (int Yrow : y) {
        for (int i = 0; i < 8; i++) {
          copy[i] = Arrays.copyOfRange(original[i + Xrow], Yrow, Yrow + 8);
        }
        int buffer = Calculation(copy, sb);

        if (answer > buffer) {
          answer = buffer;
        }
      }
    }

    sb.append(answer);

    System.out.print(sb);
  }
}