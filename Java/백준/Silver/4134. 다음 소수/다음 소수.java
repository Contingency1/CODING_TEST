import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  private static long judge(long a, StringBuilder sb) {

    long i = 3;

    while (i * i <= a) {

      if (a % 6 == 1 || a % 6 == 5) { //a가 6k +- 1 에 해당하면
        if (a % i == 0) {
          a += 2;
          i = 3;
          continue;
        }

      } else {
        a += 2;
        i = 3;
        continue;
      }

      if (i % 6 == 1) {
        i += 4;
      } else {
        i += 2;
      }
    }

    return a;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine());
    long[] input = new long[N];

    for (int i = 0; i < N; i++) {
      input[i] = Long.parseLong(br.readLine());
    }

    for (long row : input) {
      if (row <= 3) {
        if (row == 3) {
          sb.append(3).append("\n");
        } else {
          sb.append(2).append("\n");
        }
        continue;
      } else if (row % 2 == 0) {
        row++;
      }

      sb.append(judge(row, sb)).append("\n");
    }

    sb.deleteCharAt(sb.length() - 1);

    System.out.print(sb);
  }
}