import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int[] N = Arrays.stream(br.readLine().split(""))
        .mapToInt(Integer::parseInt)
        .toArray();

    Arrays.sort(N);

    for (int i = N.length - 1; i >= 0; i--) {
      sb.append(N[i]);
    }

    System.out.print(sb);
  }
}