import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

  static int[][] nodes;
  static int[] root;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    final int NODE_COUNT = Integer.parseInt(st.nextToken());
    final int ALREADY_CONNECTED_COUNT = Integer.parseInt(st.nextToken());

    nodes = new int[NODE_COUNT + 1][2];
    root = new int[NODE_COUNT + 1];
    init(NODE_COUNT, br);

    for (int i = 0; i < ALREADY_CONNECTED_COUNT; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());

      union(a, b);
    }

    Set<Integer> set = new HashSet<>();
    for (int i = 1; i < root.length; i++) {
      set.add(find(i));
    }

    int answerSize = set.size() - 1;

    PriorityQueue<Length> queue = new PriorityQueue<>();
    for (int i = 1; i < nodes.length - 1; i++) {
      for (int j = i + 1; j < nodes.length; j++) {
        if (find(i) == find(j)) {
          continue;
        }

        long xLength = nodes[i][0] - nodes[j][0];
        long yLength = nodes[i][1] - nodes[j][1];

        queue.add(new Length(i, j, xLength * xLength + yLength * yLength));
      }

    }

    int curSize = 0;
    double answer = 0;

    if (queue.isEmpty()) {
      System.out.println("0.00");
      return;
    }
    
    while (true) {
      if (queue.isEmpty()) {
        break;
      }
      Length length = queue.poll();
      int from = length.from;
      int to = length.to;
      long lengthValue = length.length;

      if (find(from) == find(to)) {
        continue;
      }

      answer += Math.sqrt(lengthValue) * 1000_00 / 1000_00.0;

      union(from, to);

      curSize++;
      if (curSize == answerSize) {
        break;
      }


    }

    System.out.printf("%.2f", answer);

  }

  private static int find(int x) {
    if (x == root[x]) {
      return x;
    }

    return root[x] = find(root[x]);
  }

  private static void union(int x, int y) {
    int xRoot = find(x);
    int yRoot = find(y);

    if (xRoot == yRoot) {
      return;
    }

    root[yRoot] = xRoot;
  }

  private static void init(int NODE_COUNT, BufferedReader br) throws IOException {
    StringTokenizer st;
    for (int i = 1; i < NODE_COUNT + 1; i++) {
      root[i] = i;
    }

    for (int i = 1; i < NODE_COUNT + 1; i++) {
      st = new StringTokenizer(br.readLine());
      nodes[i][0] = Integer.parseInt(st.nextToken());
      nodes[i][1] = Integer.parseInt(st.nextToken());
    }
  }

  static class Length implements Comparable<Length> {

    int from;
    int to;
    long length;

    public Length(int from, int to, long length) {
      this.from = from;
      this.to = to;
      this.length = length;
    }


    @Override
    public int compareTo(Length o) {
      return Long.compare(this.length, o.length);
    }
  }

}