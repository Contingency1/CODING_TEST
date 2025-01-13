import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine());
    String[] input = new String[N];

    for (int i = 0; i < N; i++) {
      input[i] = br.readLine();
    }
    input = Arrays.stream(input).distinct().toArray(String[]::new);

    Arrays.sort(input, (a, b) ->
        a.length() == b.length() ?
            a.compareTo(b) : a.length() - b.length());

    for (String row : input) {
      sb.append(row).append("\n");
    }

    sb.deleteCharAt(sb.length() - 1);

    System.out.print(sb);
  }
}