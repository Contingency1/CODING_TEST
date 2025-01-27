import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  private static void check(int N, int x, int y, StringBuilder[] sb) {
    if (N == 3) {
      if (x % 3 == 2 && y % 3 == 2) {
        sb[y - 1].append(" ");
      } else {
        sb[y - 1].append("*");
      }
    } else {
      if (x % N >= N / 3 + 1 && x % N <= 2 * N / 3 && y % N >= N / 3 + 1 && y % N <= 2 * N / 3) {
        sb[y - 1].append(" ");
      } else {
        check(N / 3, x, y, sb);
      }
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//    StringBuilder sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine());

    StringBuilder[] buffers = new StringBuilder[N];

    for (int i = 0; i < N; i++) {
      buffers[i] = new StringBuilder();
    }

    for (int y = 1; y <= N; y++) {
      for (int x = 1; x <= N; x++) {
        check(N, x, y, buffers);
      }
    }

    for (int i = 0; i < buffers.length; i++) {
      if (i != buffers.length - 1) {
        System.out.println(buffers[i].toString());
      } else {
        System.out.print(buffers[i].toString());
      }

    }

  }
}