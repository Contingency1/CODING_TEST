import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

  static int[] parent;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    StringTokenizer st = new StringTokenizer(br.readLine());
    final int NODE_COUNT = Integer.parseInt(st.nextToken());
    final int EDGE_COUNT = Integer.parseInt(st.nextToken());
    parent = new int[NODE_COUNT + 1];

    for (int i = 1; i <= NODE_COUNT; i++) {
      parent[i] = i;
    }

    PriorityQueue<Status> pq = new PriorityQueue<>();

    for (int i = 0; i < EDGE_COUNT; i++) {
      st = new StringTokenizer(br.readLine());

      int to1 = Integer.parseInt(st.nextToken());
      int to2 = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());

      pq.add(new Status(to1, to2, cost));
    }
    br.close();

    int answer = 0;
    int max = -1;
    while (!pq.isEmpty()) {
      Status status = pq.poll();

      int curTo1 = status.to1;
      int curTo2 = status.to2;
      int curCost = status.cost;

      if (findRoot(curTo1) != findRoot(curTo2)) {
        union(curTo1, curTo2);
        answer += curCost;
        max = Math.max(max, curCost);
      }
    }

    sb.append(answer - max);
    System.out.print(sb);
  }

  private static void union(int to1, int to2) {
    int root1 = findRoot(to1);
    int root2 = findRoot(to2);

    if (root1 != root2) {
      parent[root2] = root1;
    }
  }

  private static int findRoot(int k) {
    if (parent[k] != k) {
      parent[k] = findRoot(parent[k]);
    }

    return parent[k];
  }

  static class Status implements Comparable<Status> {

    int to1;
    int to2;
    int cost;

    public Status(int to1, int to2, int cost) {
      this.to1 = to1;
      this.to2 = to2;
      this.cost = cost;
    }

    @Override
    public int compareTo(Status other) {
      return this.cost - other.cost;
    }
  }

}
