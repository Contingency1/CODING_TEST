import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

    int start = input[0];
    int target = input[1];

    ArrayDeque<Node> queue = new ArrayDeque<>();
    boolean[] visited = new boolean[100_101];

    queue.add(new Node(start, 0, null));

    int answer = 0;
    while (!queue.isEmpty()) {
      Node cur = queue.poll();
      int curPoint = cur.point;
      int curCount = cur.count;

      if (curPoint == target) {
        answer = curCount;
        ArrayDeque<Integer> arrQueue = new ArrayDeque<>();
        arrQueue.add(curPoint);

        cur = cur.prev;

        while (cur != null) {
          arrQueue.add(cur.point);
          cur = cur.prev;
        }

        while (!arrQueue.isEmpty()) {
          sb.append(arrQueue.pollLast()).append(" ");
        }

        break;
      }

      if (visited[curPoint]) {
        continue;
      }

      visited[curPoint] = true;

      if (curPoint * 2 <= 100_101) {
        queue.add(new Node(curPoint * 2, curCount + 1, cur));
      }

      if (curPoint + 1 <= target) {
        queue.add(new Node(curPoint + 1, curCount + 1, cur));
      }

      if (curPoint - 1 >= 0) {
        queue.add(new Node(curPoint - 1, curCount + 1, cur));
      }


    }

    System.out.println(answer);

    sb.deleteCharAt(sb.length() - 1);

    System.out.print(sb);
  }

  static class Node {

    int point;
    int count;
    Node prev;

    Node(int point, int count, Node prev) {
      this.point = point;
      this.count = count;
      this.prev = prev;
    }

  }

}