import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine());
    int[] input = new int[N];

    for (int i = 0; i < N; i++) {
      input[i] = Integer.parseInt(br.readLine());
    }

    int MAX = 0;

    Map<Integer, Integer> obj = new HashMap<>();

    for (int row : input) {
      MAX = Math.max(MAX, row);
      if (obj.containsKey(row)) {
        obj.put(row, obj.get(row) + 1);
      } else {
        obj.put(row, 1);
      }
    }

    for (int i = 0; i <= MAX; i++) {
      if (obj.containsKey(i)) {
        for (int j = 1; j <= obj.get(i); j++) {
          sb.append(i).append("\n");
        }
      }
    }

    sb.deleteCharAt(sb.length() - 1);

    System.out.print(sb);
  }
}