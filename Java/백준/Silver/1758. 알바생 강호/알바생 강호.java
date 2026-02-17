import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int count = Integer.parseInt(br.readLine());

    int[] arr = new int[count];

    for (int i = 0; i < count; i++) {
      arr[i] = Integer.parseInt(br.readLine());
    }

    Arrays.sort(arr);

    int rank = 1;

    long answer = 0;

    for (int i = count - 1; i >= 0; i--) {
      int buffer = arr[i] - (rank++ - 1);

      if (buffer < 0) {
        break;
      }

      answer += buffer;
    }

    System.out.print(answer);
  }

}