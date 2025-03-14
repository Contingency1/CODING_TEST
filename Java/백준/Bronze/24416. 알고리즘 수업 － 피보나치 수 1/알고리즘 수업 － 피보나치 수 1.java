import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  static int answer1 = 0;
  static int answer2 = 0;

  private static int fib(int n) {

    if (n == 1 || n == 2) {
      answer1++;
      return 1;
    } else {
      return fib(n - 1) + fib(n - 2);
    }
  }

  private static int fibonacci(int n) {

    int[] f = new int[n + 1];
    f[1] = 1;
    f[2] = 1;

    for (int i = 3; i <= n; i++) {
      answer2++;
      f[i] = f[i - 1] + f[i - 2];

    }

    return f[n];
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int input = Integer.parseInt(br.readLine());

    fib(input);
    fibonacci(input);

    sb.append(answer1).append(" ").append(answer2);

    System.out.print(sb);
  }
}
