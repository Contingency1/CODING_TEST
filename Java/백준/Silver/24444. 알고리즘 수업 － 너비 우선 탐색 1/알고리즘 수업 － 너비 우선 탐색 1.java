import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Main {

  static int[] answer;
  static int count = 1;

  private static void addEdge(Map<Integer, PriorityQueue<Integer>> map, int x, int y) {
    map.computeIfAbsent(x, key -> new PriorityQueue<>()).add(y);
    map.computeIfAbsent(y, key -> new PriorityQueue<>()).add(x);
  }

  private static void bfs(Map<Integer, PriorityQueue<Integer>> map, int start) {
    Set<Integer> visited = new HashSet<>();
    Queue<Integer> queue = new ArrayDeque<>();

    visited.add(start);
    queue.add(start);

    while (!queue.isEmpty()) {
      int key = queue.poll();
      answer[key] = count++;

      PriorityQueue<Integer> pq = map.get(key);

      int rotate = pq.size();

      for (int i = 0; i < rotate; i++) {
        int row = pq.poll();
        if (!visited.contains(row)) {
          visited.add(row);
          queue.add(row);
        }
      }

    }

  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    String[] input = br.readLine().split(" ");

    int N = Integer.parseInt(input[0]);
    int M = Integer.parseInt(input[1]);
    int R = Integer.parseInt(input[2]);

    Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
    answer = new int[N + 1];

    for (int i = 0; i < M; i++) {
      int[] buffer = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
      addEdge(map, buffer[0], buffer[1]);
    }

    bfs(map, R);

    for (int i = 1; i < N + 1; i++) {
      sb.append(answer[i]).append("\n");
    }

    System.out.print(sb);
  }
}