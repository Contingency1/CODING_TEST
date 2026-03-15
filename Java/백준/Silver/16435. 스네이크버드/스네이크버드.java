import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    final int COUNT = Integer.parseInt(st.nextToken());
    int length = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());

    int[] arr = new int[COUNT];
    for (int i = 0; i < COUNT; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(arr);

    for (int i : arr) {
      if (i <= length) {
        length++;
        continue;
      }
      break;
    }

    System.out.println(length);

  }

}