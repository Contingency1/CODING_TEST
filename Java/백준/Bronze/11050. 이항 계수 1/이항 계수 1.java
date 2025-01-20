import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

  private static int factorial(int n) {
    int answer = 1;
    for (int i = 1; i <= n; i++) {
      answer *= i;
    }
    return answer;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int[] input = Arrays.stream(br.readLine().split(" "))
        .mapToInt(Integer::parseInt).toArray();

    sb.append(factorial(input[0]) / (factorial(input[1]) * factorial(input[0] - input[1])));

    System.out.print(sb);
  }
}