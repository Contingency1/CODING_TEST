import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

  static List<List<NodeJS>> nodes = new ArrayList<>();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    String[] input1 = br.readLine().split(" ");

    final int NODE_COUNT = Integer.parseInt(input1[0]);
    final int EDGE_COUNT = Integer.parseInt(input1[1]);

    if (EDGE_COUNT == 0) {
      System.out.print(-1);
      return;
    }

    for (int i = 0; i <= NODE_COUNT; i++) {
      nodes.add(new ArrayList<>());
    }

    for (int i = 0; i < EDGE_COUNT; i++) {
      String[] input2 = br.readLine().split(" ");
      int from = Integer.parseInt(input2[0]);
      int to = Integer.parseInt(input2[1]);
      int distance = Integer.parseInt(input2[2]);

      nodes.get(from).add(new NodeJS(to, distance));
      nodes.get(to).add(new NodeJS(from, distance));
    }
    String[] input3 = br.readLine().split(" ");

    int target1 = Integer.parseInt(input3[0]);
    int target2 = Integer.parseInt(input3[1]);

    int buffer1 = dk(1, target1, 0);
    int buffer2 = dk(target1, target2, buffer1);
    int answer1 = dk(target2, NODE_COUNT, buffer2);

    int buffer3 = dk(1, target2, 0);
    int buffer4 = dk(target2, target1, buffer3);
    int answer2 = dk(target1, NODE_COUNT, buffer4);

    if (buffer1 == -1 || buffer2 == -1) {
      answer1 = -1;
    }

    if (buffer3 == -1 || buffer4 == -1) {
      answer2 = -1;
    }

    if (answer1 >= 0 && answer2 >= 0) {
      sb.append(Math.min(answer1, answer2));
    } else if (answer1 < 0) {
      sb.append(answer2);
    } else {
      sb.append(answer1);
    }

    System.out.print(sb);
  }

  private static int dk(int start, int end, int lastDistance) {
    PriorityQueue<State> pq = new PriorityQueue<>();
    pq.add(new State(start, lastDistance));

    boolean[] visited = new boolean[nodes.size() + 1];

    while (!pq.isEmpty()) {
      State state = pq.poll();
      int curNode = state.node;
      int curDistance = state.distance;

      if (visited[curNode]) {
        continue;
      }

      visited[curNode] = true;

      if (curNode == end) {
        return curDistance;
      }

      addQueue(pq, curNode, curDistance);
    }
    return -1;
  }

  private static void addQueue(PriorityQueue<State> pq, int from, int plus) {
    for (NodeJS node : nodes.get(from)) {
      pq.add(new State(node.to, node.distance + plus));
    }
  }


  static class State implements Comparable<State> {

    int node;
    int distance;

    public State(int node, int distance) {
      this.node = node;
      this.distance = distance;
    }

    @Override
    public int compareTo(State other) {
      return this.distance - other.distance;
    }
  }

  static class NodeJS {

    int to;
    int distance;

    NodeJS(int to, int distance) {
      this.to = to;
      this.distance = distance;
    }

  }
}
