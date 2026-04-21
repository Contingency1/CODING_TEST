import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final int COUNT = Integer.parseInt(br.readLine());

    int[] arr = new int[COUNT];

    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < COUNT; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    st = new StringTokenizer(br.readLine());

    int start = Integer.parseInt(st.nextToken());
    int end = Integer.parseInt(st.nextToken());

    boolean[] visited = new boolean[COUNT + 1];
    ArrayDeque<Node> queue = new ArrayDeque<>();

    visited[start] = true;
    queue.addLast(new Node(0, start));

    while (!queue.isEmpty()) {
      Node node = queue.pollFirst();

      int cur = node.cur;
      int count = node.count;

      if (cur == end) {
        System.out.print(count);
        return;
      }

      int plus = arr[cur - 1];

      for (int next = cur + plus; next <= COUNT; next += plus) {
        if (!visited[next]) {
          visited[next] = true;
          queue.addLast(new Node(count + 1, next));
        }
      }

      for (int next = cur - plus; next >= 1; next -= plus) {
        if (!visited[next]) {
          visited[next] = true;
          queue.addLast(new Node(count + 1, next));
        }
      }
    }

    System.out.println(-1);
  }

}

class Node {

  int count;
  int cur;

  public Node(int count, int cur) {
    this.count = count;
    this.cur = cur;
  }
}
