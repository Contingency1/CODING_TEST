import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final int COUNT = Integer.parseInt(br.readLine());

    StringTokenizer st = new StringTokenizer(br.readLine());

    int[] arr = new int[COUNT];

    for (int i = 0; i < COUNT; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    int[][] buffer = new int[COUNT][COUNT];

    for (int i = 0; i < COUNT; i++) {
      int max = arr[i];
      int min = arr[i];

      for (int j = i + 1; j < COUNT; j++) {
        int cur = arr[j];

        if (cur < min) {
          min = cur;
        } else if(cur > max) {
          max = cur;
        }

        int diff = max - min;

        buffer[i][j] = diff;
      }
    }

    int[] d = new int[COUNT];
    d[1] = buffer[0][1];

    for (int i = 2; i < COUNT; i++) {
      for (int j = 1; j < i; j++) {
        d[i] = Math.max(d[i], d[j - 1] + buffer[j][i]);
      }
    }

    System.out.print(d[COUNT - 1]);
  }
}