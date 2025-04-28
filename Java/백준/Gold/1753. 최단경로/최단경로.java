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

    String[] input = br.readLine().split(" ");

    int nodeCount = Integer.parseInt(input[0]);
    int edgeCount = Integer.parseInt(input[1]);

    int startNode = Integer.parseInt(br.readLine());

    List<List<NodeJS>> graph = new ArrayList<>();

    graph.add(new ArrayList<>());

    for (int i = 1; i <= nodeCount; i++) {
      graph.add(new ArrayList<>());
    }

    for (int i = 0; i < edgeCount; i++) {
      String[] line = br.readLine().split(" ");

      int from = Integer.parseInt(line[0]);
      int to = Integer.parseInt(line[1]);
      int weight = Integer.parseInt(line[2]);

      graph.get(from).add(new NodeJS(to, weight));
    }

//    for (int i = 1; i <= nodeCount; i++) {
//      List<NodeJS> nodes = graph.get(i);
//      System.out.println("Case #" + i + ":");
//      for (NodeJS node : nodes) {
//        System.out.println("    node to: " + node.to + ", node weight: " + node.weight);
//      }
//    }

    int[] distance = new int[nodeCount + 1];
    boolean[] visited = new boolean[nodeCount + 1];
    PriorityQueue<NodeJS> pq = new PriorityQueue<>();

    Arrays.fill(distance, Integer.MAX_VALUE);

    pq.add(new NodeJS(startNode, 0));
    distance[startNode] = 0;

    while (!pq.isEmpty()) {
      NodeJS cur = pq.poll();
      int curTo = cur.to;

      if (visited[curTo]) {
        continue;
      }

      visited[curTo] = true;

      List<NodeJS> nearByNodes = graph.get(curTo);

      for (NodeJS nearNode : nearByNodes) {
        int nearNodeTo = nearNode.to;
        int nearNodeWeight = nearNode.weight;

        int finalPlus = distance[curTo] + nearNodeWeight;

        if (distance[nearNodeTo] > finalPlus) {
          distance[nearNodeTo] = finalPlus;
          pq.add(new NodeJS(nearNodeTo, finalPlus));
        }
      }
    }

    for (int i = 1; i <= nodeCount; i++) {
      sb.append(distance[i] == Integer.MAX_VALUE ? "INF" : distance[i]).append("\n");
    }

    System.out.print(sb);
  }

  static class NodeJS implements Comparable<NodeJS> {

    int to;
    int weight;

    NodeJS(int to, int weight) {
      this.to = to;
      this.weight = weight;
    }

    @Override
    public int compareTo(NodeJS other) {
      // 양수면 기존의 것을 우선순위 뒤로보냄 = 오름차순
      return this.weight - other.weight;
    }
  }

}