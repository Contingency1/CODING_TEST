import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

    int N = input[0];
    int M = input[1];

    HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

    for (int i = 0; i < M; i++) {
      int[] ints = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

      ArrayList<Integer> buffer1 = map.getOrDefault(ints[0], new ArrayList<>());
      ArrayList<Integer> buffer2 = map.getOrDefault(ints[1], new ArrayList<>());

      buffer1.add(ints[1]);
      buffer2.add(ints[0]);

      map.put(ints[0], buffer1);
      map.put(ints[1], buffer2);

    }

    Deque<Integer> queue = new ArrayDeque<>();
    int[] visited = new int[N + 1];

    int answer = 0;

    for (int i = 1; i <= N; i++) {
      if (visited[i] == 1) {
        continue;
      }

      answer++;
      queue.add(i);
      visited[i] = 1;

      while (!queue.isEmpty()) {
        int polled = queue.poll();

        if (map.containsKey(polled)) {
          ArrayList<Integer> buffer = map.get(polled);

          for (int j = 0; j < buffer.size(); j++) {
            int key = buffer.get(j);
            if (visited[key] == 0) {
              visited[key] = 1;
              queue.add(key);
            }
          }
        }
      }
    }

    sb.append(answer);

    System.out.print(sb);
  }

}
