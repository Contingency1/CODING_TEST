import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

  static List<List<NodeJS>> graph = new ArrayList<>();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    String[] input = br.readLine().split(" ");
    final int NODE_COUNT = Integer.parseInt(input[0]);
    final int EDGE_COUNT = Integer.parseInt(input[1]);

    if (NODE_COUNT == 1) {
      return;
    }

    for (int i = 0; i <= NODE_COUNT; i++) {
      graph.add(new ArrayList<>());
    }

    makeGraph(EDGE_COUNT, br);

    long[] distance = new long[NODE_COUNT + 1];
    Arrays.fill(distance, 600_000_000);
    distance[1] = 0;

    for (int i = 1; i < NODE_COUNT; i++) {
      for (int k = 1; k < graph.size(); k++) {
        List<NodeJS> list = graph.get(k);
        for (NodeJS node : list) {
          if (distance[k] == 600_000_000) {
            continue;
          }

          int curTo = node.to;
          long curWeight = node.weight + distance[k];

          if (distance[curTo] > curWeight) {
            distance[curTo] = curWeight;
          }
        }
      }
    }

    for (int k = 1; k < graph.size(); k++) {
      List<NodeJS> list = graph.get(k);
      for (NodeJS node : list) {
        if (distance[k] == 600_000_000) {
          continue;
        }

        int curTo = node.to;
        long curWeight = node.weight + distance[k];

        if (distance[curTo] > curWeight) {
          System.out.println(-1);
          return;
        }
      }
    }

    for (int i = 1; i < distance.length; i++) {
      if (distance[i] == 600_000_000) {
        distance[i] = -1;
      }
    }

    for (int i = 2; i < distance.length; i++) {
      sb.append(distance[i]).append("\n");
    }

    System.out.print(sb);
  }

  private static void makeGraph(int EDGE_COUNT, BufferedReader br) throws IOException {
    for (int i = 0; i < EDGE_COUNT; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int weight = Integer.parseInt(st.nextToken());

      graph.get(from).add(new NodeJS(to, weight));
    }
  }


  static class NodeJS {

    int to;
    int weight;

    public NodeJS(int to, int weight) {
      this.to = to;
      this.weight = weight;
    }
  }
}
