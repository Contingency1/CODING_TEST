import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  static int[] root;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    final int AGGREGATION_COUNT = Integer.parseInt(st.nextToken());
    final int CALCULATION_COUNT = Integer.parseInt(st.nextToken());

    initRoot(AGGREGATION_COUNT);

    for (int i = 0; i < CALCULATION_COUNT; i++) {
      st = new StringTokenizer(br.readLine());
      boolean isOne = Integer.parseInt(st.nextToken()) == 1;
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      if (isOne) {
        if (find(x) == find(y)) {
          System.out.println("YES");
        } else {

          System.out.println("NO");
        }

        continue;
      }

      union(x, y);

    }
  }

  private static void initRoot(int AGGREGATION_COUNT) {
    root = new int[AGGREGATION_COUNT + 1];

    for (int i = 0; i < AGGREGATION_COUNT + 1; i++) {
      root[i] = i;
    }
  }

  static int find(int child) {
    if (root[child] == child) {
      return child;
    }

    return root[child] = find(root[child]);
  }

  static void union(int x, int y) {
    int rootX = find(x);
    int rootY = find(y);

    if (rootX == rootY) {
      return;
    }

    root[rootY] = rootX;
  }

}