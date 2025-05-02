import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    String[] firstLine = br.readLine().split(" ");

    // (1 ≤ MAN_COUNT ≤ 1,000)
    final int MAN_COUNT = Integer.parseInt(firstLine[0]);
    // (1 ≤ ROAD_COUNT ≤ 10,000)
    final int ROAD_COUNT = Integer.parseInt(firstLine[1]);
    // (1 ≤ X ≤ MAN_COUNT)
    final int TARGET = Integer.parseInt(firstLine[2]);

    List<List<NodeJS>> nodes = new ArrayList<>();

    for (int i = 0; i <= MAN_COUNT; i++) {
      nodes.add(new ArrayList<>());
    }

    for (int i = 0; i < ROAD_COUNT; i++) {
      String[] str = br.readLine().split(" ");
      int from = Integer.parseInt(str[0]);
      int to = Integer.parseInt(str[1]);
      int cost = Integer.parseInt(str[2]);

      nodes.get(from).add(new NodeJS(to, cost));
    }

    int answer = 0;

    for (int i = 1; i <= MAN_COUNT; i++) {
      boolean[] visited = new boolean[MAN_COUNT + 1];
      PriorityQueue<NodeJS> pq = new PriorityQueue<>();
      pq.add(new NodeJS(i, 0));

      while (!pq.isEmpty()) {
        NodeJS node = pq.poll();
        int to = node.to;
        int cost = node.cost;

        if (to == TARGET) {
          pq.clear();
          Arrays.fill(visited, false);
          pq.add(new NodeJS(to, cost));
          break;
        }

        if (visited[to]) {
          continue;
        }

        visited[to] = true;

        for (NodeJS n : nodes.get(to)) {
          int newCost = n.cost + cost;

          pq.add(new NodeJS(n.to, newCost));
        }
      }

      while (!pq.isEmpty()) {
        NodeJS node = pq.poll();
        int to = node.to;
        int cost = node.cost;

        if (to == i) {
          answer = Math.max(answer, cost);
          break;
        }
        if (visited[to]) {
          continue;
        }

        visited[to] = true;

        for (NodeJS n : nodes.get(to)) {
          int newCost = n.cost + cost;

          pq.add(new NodeJS(n.to, newCost));
        }

      }
    }

    sb.append(answer);

    System.out.print(sb);
  }

  static class NodeJS implements Comparable<NodeJS> {

    int to;
    int cost;

    NodeJS(int to, int cost) {
      this.to = to;
      this.cost = cost;
    }

    @Override
    public int compareTo(NodeJS other) {
      // 양수일 경우, this를 후순위로 보냄
      return this.cost - other.cost;
    }

  }

}