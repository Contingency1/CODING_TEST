import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    final int NODE_COUNT = Integer.parseInt(br.readLine());

    List<List<NodeJS>> tree = new ArrayList<>();

    for (int i = 0; i <= NODE_COUNT; i++) {
      tree.add(new ArrayList<>());
    }

    for (int i = 1; i < NODE_COUNT; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());

      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());

      tree.get(from).add(new NodeJS(to, cost));
      tree.get(to).add(new NodeJS(from, cost));
    }
    br.close();

    ArrayDeque<CostInfo> queue = new ArrayDeque<>();
    queue.add(new CostInfo(0, 1, 0));

    int maxNode = 0;
    int maxCost = 0;

    while (!queue.isEmpty()) {
      CostInfo value = queue.poll();

      int prev = value.prev;
      int from = value.from;
      int totalCost = value.totalCost;

      if (maxCost < totalCost) {
        maxCost = totalCost;
        maxNode = from;
      }
      for (NodeJS node : tree.get(from)) {
        int cost = node.cost;
        int to = node.to;

        if (prev != to) {
          queue.add(new CostInfo(from, to, cost + totalCost));
        }
      }
    }

    queue.add(new CostInfo(0, maxNode, 0));

    maxCost = 0;

    while (!queue.isEmpty()) {
      CostInfo value = queue.poll();

      int prev = value.prev;
      int from = value.from;
      int totalCost = value.totalCost;

      maxCost = Math.max(maxCost, totalCost);

      for (NodeJS node : tree.get(from)) {
        int cost = node.cost;
        int to = node.to;

        if (prev != to) {
          queue.add(new CostInfo(from, to, cost + totalCost));
        }
      }
    }

    sb.append(maxCost);
    System.out.print(sb);
  }

  static class CostInfo {

    int prev;
    int from;
    int totalCost;

    public CostInfo(int prev, int from, int totalCost) {
      this.prev = prev;
      this.from = from;
      this.totalCost = totalCost;
    }
  }

  static class NodeJS {

    int to;
    int cost;

    public NodeJS(int to, int cost) {
      this.to = to;
      this.cost = cost;
    }
  }
}