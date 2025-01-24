import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {

  private static BigInteger factorial(BigInteger i) {

    if (i.equals(BigInteger.ZERO)) {
      return BigInteger.ONE;
    }
    if (i.compareTo(BigInteger.ONE) > 0) {

      return i.multiply(factorial(i.subtract(BigInteger.ONE)));
    } else {
      return i;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    sb.append(factorial(new BigInteger(br.readLine())));

    System.out.print(sb);
  }
}