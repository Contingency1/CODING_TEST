import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

  private static long calculation(long a, long b) {

    while (true) {
      long r = a % b;

      if (r == 0) {
        return b;
      } else {
        a = b;
        b = r;
      }
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    long[] arr = Arrays.stream(br.readLine().split(" "))
        .mapToLong(Long::parseLong).toArray();

    sb.append(arr[0] * arr[1] / calculation(arr[0], arr[1]));

    System.out.print(sb);
  }
}