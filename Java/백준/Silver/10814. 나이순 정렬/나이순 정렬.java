import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine());
    String[][] input = new String[N][3];

    for (int i = 0; i < N; i++) {
      String[] row = br.readLine().split(" ");
      System.arraycopy(row, 0, input[i], 0, row.length);
      input[i][2] = String.valueOf(i);
    }

    Arrays.sort(input, (a, b) ->
        Integer.parseInt(a[0]) == Integer.parseInt(b[0]) ?
            Integer.parseInt(a[2])
                - Integer.parseInt(b[2])
            : Integer.parseInt(a[0]) - Integer.parseInt(b[0])
    );

    for (String[] row : input) {
      sb.append(row[0]).append(" ").append(row[1]).append("\n");
    }

    sb.deleteCharAt(sb.length() - 1);

    System.out.print(sb);
  }
}