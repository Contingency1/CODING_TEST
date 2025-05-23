import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  static int[] root;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    StringTokenizer st = new StringTokenizer(br.readLine());

    // 3 <= POINT_COUNT <= 500_000
    final int POINT_COUNT = Integer.parseInt(st.nextToken());

    // 3 <= QUERY_COUNT <= 1_000_000
    final int QUERY_COUNT = Integer.parseInt(st.nextToken());

    int[][] queries = new int[QUERY_COUNT][2];
    for (int i = 0; i < QUERY_COUNT; i++) {
      st = new StringTokenizer(br.readLine());

      int first = Integer.parseInt(st.nextToken());
      int second = Integer.parseInt(st.nextToken());

      queries[i][0] = first;
      queries[i][1] = second;
    }
    br.close();

    root = new int[POINT_COUNT];
    for (int i = 0; i < POINT_COUNT; i++) {
      root[i] = i;
    }

    int answer = 0;
    for (int i = 0; i < QUERY_COUNT; i++) {
      int first = queries[i][0];
      int second = queries[i][1];

      if (union(first, second)) {
        answer = i + 1;
        break;
      }
    }

    sb.append(answer);
    System.out.print(sb);
  }

  private static int find(int x) {
    if (root[x] == x) {
      return x;
    }

    root[x] = find(root[x]);
    return root[x];
  }

  private static boolean union(int x, int y) {
    int xRoot = find(x);
    int yRoot = find(y);

    if (xRoot == yRoot) {
      return true;
    }

    root[yRoot] = xRoot;
    return false;
  }
}
