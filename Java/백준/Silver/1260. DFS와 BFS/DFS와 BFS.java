import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.TreeSet;

public class Main {

  static int N;
  static boolean[] DFSvisited;

  private static void bfs(int start, Map<Integer, TreeSet<Integer>> map, StringBuilder sb) {
    Queue<Integer> queue = new ArrayDeque<>();
    boolean[] visited = new boolean[N + 1];

    sb.append(start).append(" ");

    queue.add(start);

    visited[start] = true;

    while (!queue.isEmpty()) {
      TreeSet<Integer> set = map.getOrDefault(queue.poll(), new TreeSet<>());

      int rotate = set.size();

      for (int i = 0; i < rotate; i++) {
        int node = set.pollFirst();

        if (!visited[node]) {
          queue.add(node);
          visited[node] = true;
          sb.append(node).append(" ");
        }
      }
    }
    sb.deleteCharAt(sb.length() - 1);
  }

  private static void dfs(int start, Map<Integer, TreeSet<Integer>> map, StringBuilder sb) {
    DFSvisited[start] = true;
    sb.append(start).append(" ");

    for (int node : map.getOrDefault(start, new TreeSet<>())) {
      if (!DFSvisited[node]) {
        dfs(node, map, sb);
      }
    }

  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    String[] input = br.readLine().split(" ");
    Map<Integer, TreeSet<Integer>> map = new HashMap<>();

    N = Integer.parseInt(input[0]);
    int M = Integer.parseInt(input[1]);
    int V = Integer.parseInt(input[2]);

    for (int i = 0; i < M; i++) {
      int[] ints = Arrays.stream(br.readLine().split(" "))
          .mapToInt(Integer::parseInt).toArray();

      map.computeIfAbsent(ints[0], k -> new TreeSet<>()).add(ints[1]);
      map.computeIfAbsent(ints[1], k -> new TreeSet<>()).add(ints[0]);
    }

//    for (TreeSet<Integer> set : map.values()) {
//      sb.append(set.toString()).append("\n");
//    }

    DFSvisited = new boolean[N + 1];

    dfs(V, map, sb);
    sb.deleteCharAt(sb.length() - 1);
    sb.append("\n");
    bfs(V, map, sb);

    System.out.print(sb);
  }
}