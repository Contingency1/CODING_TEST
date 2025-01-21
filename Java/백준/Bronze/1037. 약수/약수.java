import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine());

    int[] arr = Arrays.stream(br.readLine().split(" "))
        .mapToInt(Integer::parseInt).toArray();

    int MAX = 0;
    int MIN = Integer.MAX_VALUE;

    for (int i = 0; i < N; i++) {
      MAX = Math.max(arr[i], MAX);
    }

    for (int i = 0; i < N; i++) {
      MIN = Math.min(arr[i], MIN);
    }

    sb.append(MAX * MIN);

    System.out.print(sb);
  }
}