import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    final int ROW_COUNT = Integer.parseInt(st.nextToken());
    final int COL_COUNT = Integer.parseInt(st.nextToken());

    if (COL_COUNT <= 2) {
      System.out.println(0);
      return;
    }

    int[] arr = new int[COL_COUNT];
    st = new StringTokenizer(br.readLine());

    for (int i = 0; i < COL_COUNT; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    int answer = 0;

    int l = 0;
    int r = 0;

    boolean isSpring = false;

    for (int i = 1; i < COL_COUNT; i++) {
      if (isSpring) {
        if (arr[l] <= arr[i]) {
          r = i;
          int buffer = Math.min(arr[l], arr[r]);

          for (int j = l + 1; j < r; j++) {
            int value = buffer - arr[j];
            if (value > 0) {
              answer += value;
            }
          }

          isSpring = false;
          l = i;
        }
      } else {
        if (arr[l] <= arr[i]) {
          l = i;
          r = i;
        } else {
          isSpring = true;
          r = i;
        }
      }
    }

    while (isSpring && l < COL_COUNT - 1) {
      r = l + 1;
      for (int i = l + 2; i < COL_COUNT; i++) {
        if (arr[i] > arr[r]) {
          r = i;
        }
      }

      int buffer = Math.min(arr[l], arr[r]);

      for (int j = l + 1; j < r; j++) {
        int value = buffer - arr[j];
        if (value > 0) {
          answer += value;
        }
      }

      l = r;
    }

    System.out.print(answer);
  }
}