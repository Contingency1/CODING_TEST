import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

  static int[] root;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    while (true) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int houseCount = Integer.parseInt(st.nextToken());
      int roadCount = Integer.parseInt(st.nextToken());
      if (houseCount == 0 && roadCount == 0) {
        break;
      }

      root = new int[houseCount];

      for (int i = 0; i < root.length; i++) {
        root[i] = i;
      }

      PriorityQueue<Distance> pq = new PriorityQueue<>();
      int sumDistance = 0;

      for (int i = 0; i < roadCount; i++) {
        st = new StringTokenizer(br.readLine());
        int house1 = Integer.parseInt(st.nextToken());
        int house2 = Integer.parseInt(st.nextToken());
        int distance = Integer.parseInt(st.nextToken());

        pq.add(new Distance(house1, house2, distance));
        sumDistance += distance;
      }

      int minusDistance = 0;
      int count = 0;
      while (!pq.isEmpty()) {
        Distance polled = pq.poll();
        int to = polled.to;
        int from = polled.from;
        int distance = polled.distance;

        if (count == houseCount - 1) {
          break;
        }

        if (find(to) == find(from)) {
          continue;
        }

        union(to, from);
        count++;
        minusDistance += distance;
      }

      System.out.println(sumDistance - minusDistance);
    }

  }

  static int find(int x) {
    if (x == root[x]) {
      return x;
    }

    return root[x] = find(root[x]);
  }

  static void union(int x, int y) {
    int xRoot = find(x);
    int yRoot = find(y);

    if (xRoot == yRoot) {
      return;
    }

    root[yRoot] = xRoot;
  }

  static class Distance implements Comparable<Distance> {

    int from;
    int to;
    int distance;

    public Distance(int from, int to, int distance) {
      this.from = from;
      this.to = to;
      this.distance = distance;
    }

    @Override
    public int compareTo(Distance o) {
      return this.distance - o.distance;
    }
  }
}