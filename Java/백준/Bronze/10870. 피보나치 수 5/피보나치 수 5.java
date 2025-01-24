import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  private static int pibo(int n) {

    if (n == 0) {
      return 0;
    } else if (n == 1) {
      return 1;
    } else {
      return pibo(n - 1) + pibo(n - 2);
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    sb.append(pibo(Integer.parseInt(br.readLine())));

    System.out.print(sb);
  }
}