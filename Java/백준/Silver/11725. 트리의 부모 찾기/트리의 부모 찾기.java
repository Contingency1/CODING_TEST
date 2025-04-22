import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine());

    HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

    for (int i = 0; i < N - 1; i++) {
      int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

      ArrayList<Integer> arr1 = map.getOrDefault(line[0], new ArrayList<Integer>());
      ArrayList<Integer> arr2 = map.getOrDefault(line[1], new ArrayList<Integer>());

      arr1.add(line[1]);
      arr2.add(line[0]);

      map.put(line[0], arr1);
      map.put(line[1], arr2);
    }

    HashMap<Integer, Integer> answer = new HashMap<>();

    ArrayDeque<Integer> queue = new ArrayDeque<>();
    int[] visited = new int[N + 1];

    queue.add(1);
    visited[1] = 1;

    while (!queue.isEmpty()) {
      int polled = queue.poll();

      ArrayList<Integer> buffer = map.get(polled);

      for (Integer i : buffer) {
        if (visited[i] == 0) {
          visited[i] = 1;
          queue.add(i);
          answer.put(i, polled);
        }
      }
    }

    for (int i = 2; i <= N; i++) {
      sb.append(answer.get(i)).append("\n");
    }

    System.out.print(sb);
  }

}
