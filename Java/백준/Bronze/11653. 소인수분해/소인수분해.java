import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int N = Integer.parseInt(br.readLine());

    StringBuilder sb = new StringBuilder();

    if (N == 1) {
      return;
    }

    while (true) {
      if (N % 2 == 0) {
        N /= 2;
        sb.append("2" + "\n");
        continue;
      }
      break;
    }

    for (int i = 3; i * i <= N; i += 2) {
      if (N % i == 0) {
        N /= i;
        sb.append(i + "\n");
        i -= 2;
      }
    }

    if (N > 1) {
      sb.append(N + "\n");
    }

    sb.deleteCharAt(sb.length() - 1);

    bw.write(String.valueOf(sb));

    bw.flush();
    br.close();
    bw.close();
  }
}
