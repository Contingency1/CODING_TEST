import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    final int COUNT = Integer.parseInt(br.readLine());
    int[] arr = new int[COUNT];

    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < COUNT; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(arr);

    final int TARGET = Integer.parseInt(br.readLine());
    int answer = 0;

    for (int i = 0; i < COUNT - 1; i++) {
      for (int j = i + 1; j < COUNT; j++) {
        if (arr[i] + arr[j] == TARGET) {
          answer++;
          break;
        }

        if (arr[i] + arr[j] > TARGET) {
          break;
        }
      }
    }

    System.out.print(answer);
  }

}