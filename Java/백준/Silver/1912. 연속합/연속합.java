import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine());

    int[] input = Arrays.stream(br.readLine().split(" "))
        .mapToInt(Integer::parseInt).toArray();

    int MAX = Integer.MIN_VALUE;

    int currentSum = 0;

    for (int i = 0; i < N; i++) {

      currentSum += input[i];
      MAX = Integer.max(MAX, currentSum);

      if (currentSum < 0) {
        currentSum = 0;
        continue;
      }

      MAX = Integer.max(MAX, currentSum);

    }

    sb.append(MAX);

    System.out.print(sb);
  }
}
