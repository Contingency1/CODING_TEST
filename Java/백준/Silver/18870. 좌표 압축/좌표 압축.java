import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine());

    int[] input = Arrays.stream(br.readLine().split(" "))
        .mapToInt(Integer::parseInt).toArray();

    Set<Integer> set = new HashSet<>();
    for (int row : input) {
      set.add(row);
    }

    int[] result = set.stream().mapToInt(Integer::intValue).toArray();

    Arrays.sort(result);

    Map<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < result.length; i++) {
      map.put(result[i], i);
    }

    for (int row : input) {
      sb.append(map.get(row)).append(" ");
    }

    System.out.print(sb);
  }
}