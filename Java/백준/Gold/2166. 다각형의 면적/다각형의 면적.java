import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    final int COUNT = Integer.parseInt(br.readLine());
    long[][] nodes = new long[COUNT][2];

    for (int i = 0; i < COUNT; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      nodes[i][0] = Long.parseLong(st.nextToken());
      nodes[i][1] = Long.parseLong(st.nextToken());
    }
    br.close();

    long buffer1 = 0;
    for (int i = 0; i < COUNT; i++) {
      if (i == COUNT - 1) {
        buffer1 += nodes[i][0] * nodes[0][1];
        break;
      }
      buffer1 += nodes[i][0] * nodes[i + 1][1];
    }

    long buffer2 = 0;
    for (int i = 0; i < COUNT; i++) {
      if (i == COUNT - 1) {
        buffer2 += nodes[i][1] * nodes[0][0];
        break;
      }
      buffer2 += nodes[i][1] * nodes[i + 1][0];
    }

    double answer = (double) Math.abs(buffer1 - buffer2) / 2;

    System.out.printf("%.1f", Math.round(answer * 10.0) / 10.0);
  }

}
