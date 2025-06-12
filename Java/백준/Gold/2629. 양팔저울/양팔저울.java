import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    final int WEIGHT_COUNT = Integer.parseInt(br.readLine());
    int[] weights = new int[WEIGHT_COUNT + 1];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= WEIGHT_COUNT; i++) {
      weights[i] = Integer.parseInt(st.nextToken());
    }

    final int MARBLE_COUNT = Integer.parseInt(br.readLine());
    int[] marbles = new int[MARBLE_COUNT + 1];
    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= MARBLE_COUNT; i++) {
      marbles[i] = Integer.parseInt(st.nextToken());
    }

    br.close();

    HashSet<Integer> set = new HashSet<>();
    set.add(0);

    for (int weight : weights) {
      HashSet<Integer> buffer = new HashSet<>(set);

      for (Integer i : set) {
        buffer.add(i + weight);
        buffer.add(Math.abs(i - weight));
      }
      set = buffer;
    }

    for (int i = 1; i < marbles.length; i++) {
      if (set.contains(marbles[i])) {
        sb.append("Y").append(" ");
      } else {
        sb.append("N").append(" ");
      }
    }

    sb.deleteCharAt(sb.length() - 1);
    System.out.print(sb);
  }


}