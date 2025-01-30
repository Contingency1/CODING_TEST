import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int[] input = Arrays.stream(br.readLine().split(" "))
        .mapToInt(Integer::parseInt).toArray();

    int[] numbers = Arrays.stream(br.readLine().split(" "))
        .mapToInt(Integer::parseInt).toArray();

    int[] sums = new int[input[0]];

    sums[0] = numbers[0];

    for (int i = 1; i < input[0]; i++) {
      sums[i] = sums[i - 1] + numbers[i];
    }

    for (int i = 1; i <= input[1]; i++) {

      int[] question = Arrays.stream(br.readLine().split(" "))
          .mapToInt(Integer::parseInt).toArray();

      int minus = 0;

      if (question[0] != 1) {
        minus = sums[question[0] - 2];
      }
//      sb.append("minus: ").append(minus).append(" ");

      sb.append(sums[question[1] - 1] - minus).append("\n");
    }

    System.out.print(sb);
  }
}