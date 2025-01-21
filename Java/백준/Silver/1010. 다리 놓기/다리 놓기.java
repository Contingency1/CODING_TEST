import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;

public class Main {

  private static BigInteger factorial(int n) {
    BigInteger answer = BigInteger.valueOf(1);

    for (int i = 1; i <= n; i++) {
      answer = answer.multiply(BigInteger.valueOf(i));
    }

    return answer;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int T = Integer.parseInt(br.readLine());

    for (int i = 1; i <= T; i++) {
      int[] input = Arrays.stream(br.readLine().split(" "))
          .mapToInt(Integer::parseInt).toArray();

      int r = input[0];
      int n = input[1];

//      sb.append("N: ").append(n).append(" ").append("R: ").append(r).append(" ");

      BigInteger answer =
          factorial(n).divide(factorial(r).multiply(factorial(n - r)));

      sb.append(answer).append("\n");
    }

    sb.deleteCharAt(sb.length() - 1);

    System.out.print(sb);
  }
}