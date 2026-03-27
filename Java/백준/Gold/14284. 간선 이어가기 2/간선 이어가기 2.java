import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    final int NODE_COUNT = Integer.parseInt(st.nextToken());
    final int EDGE_COUNT = Integer.parseInt(st.nextToken());

    Map<Integer, List<Edge>> map = new HashMap<>();

    for (int i = 1; i <= NODE_COUNT; i++) {
      map.put(i, new ArrayList<>());
    }

    for (int i = 0; i < EDGE_COUNT; i++) {
      st = new StringTokenizer(br.readLine());

      int node1 = Integer.parseInt(st.nextToken());
      int node2 = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());

      map.get(node1).add(new Edge(node2, cost));
      map.get(node2).add(new Edge(node1, cost));
    }

    int[] dp = new int[NODE_COUNT + 1];
    Arrays.fill(dp, Integer.MAX_VALUE);

    Queue<Edge> queue = new PriorityQueue<>();

    st = new StringTokenizer(br.readLine());
    int start = Integer.parseInt(st.nextToken());
    int end = Integer.parseInt(st.nextToken());

    dp[start] = 0;
    queue.add(new Edge(start, 0));

    while (!queue.isEmpty()) {
      Edge polled = queue.poll();

      int to = polled.to;
      int cost = polled.cost;

      if (cost > dp[to]) {
        continue;
      }

      for (Edge edge : map.get(to)) {
        if (dp[edge.to] > edge.cost + cost) {
          dp[edge.to] = edge.cost + cost;
          queue.add(new Edge(edge.to, dp[edge.to]));
        }
      }
    }

    System.out.print(dp[end]);
  }
}

class Edge implements Comparable<Edge> {

  final int to;
  final int cost;

  Edge(int to, int cost) {
    this.to = to;
    this.cost = cost;
  }

  @Override
  public int compareTo(Edge o) {
    return this.cost - o.cost;
  }
}
