import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

  static int[] parent;

  static int findParent(int x) {
    if (parent[x] != x) {
      parent[x] = findParent(parent[x]);
    }
    return parent[x];
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    StringTokenizer st = new StringTokenizer(br.readLine());
    final int NODE_COUNT = Integer.parseInt(st.nextToken());
    final int EDGE_COUNT = Integer.parseInt(st.nextToken());

    parent = new int[NODE_COUNT + 1];
    for (int i = 0; i <= NODE_COUNT; i++) {
      parent[i] = i;
    }

    PriorityQueue<NodeJS> pq = new PriorityQueue<>();

    for (int i = 0; i < EDGE_COUNT; i++) {
      st = new StringTokenizer(br.readLine());
      int to1 = Integer.parseInt(st.nextToken());
      int to2 = Integer.parseInt(st.nextToken());
      int weight = Integer.parseInt(st.nextToken());

      pq.add(new NodeJS(to1, to2, weight));
    }
    br.close();

    int answer = 0;
    while (!pq.isEmpty()) {
      NodeJS node = pq.poll();
      int curTo1 = node.to1;
      int curTo2 = node.to2;

      int parent1 = findParent(curTo1);
      int parent2 = findParent(curTo2);

      if (parent1 == parent2) {
        continue;
      }


      parent[parent2] = parent1;

      answer += node.weight;

      if (checkList()) {
        break;
      }
    }

    sb.append(answer);
    System.out.print(sb);
  }

  static boolean checkList() {
    int root = findParent(1);
    for (int i = 2; i < parent.length; i++) {
      if (findParent(i) != root) {
        return false;
      }
    }
    return true;
  }

  static class NodeJS implements Comparable<NodeJS> {

    int to1;
    int to2;
    int weight;

    public NodeJS(int to1, int to2, int weight) {
      this.to1 = to1;
      this.to2 = to2;
      this.weight = weight;
    }

    @Override
    public int compareTo(NodeJS other) {
      return this.weight - other.weight;
    }
  }

}
