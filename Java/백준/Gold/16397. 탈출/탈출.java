import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    final int CURRENT_NUMBER = Integer.parseInt(st.nextToken());
    final int MAX_CHANCE = Integer.parseInt(st.nextToken());
    final int GOAL = Integer.parseInt(st.nextToken());

    boolean[] visited = new boolean[100_000];

    visited[CURRENT_NUMBER] = true;

    ArrayDeque<Node> queue = new ArrayDeque<>();
    queue.add(new Node(CURRENT_NUMBER, 0));

    while (!queue.isEmpty()) {
      Node poll = queue.poll();

      int value = poll.value;
      int count = poll.count;

      if (value == GOAL) {
        System.out.print(count);
        return;
      }

      int valueA = value + 1;

      if (valueA <= 99_999 && !visited[valueA] && count + 1 <= MAX_CHANCE) {
        visited[valueA] = true;
        queue.add(new Node(valueA, count + 1));
      }

      if (value * 2 <= 99_999) {
        int valueB = value * 2;

        if (valueB != 0) {
          int places = (int) Math.log10(valueB);
          valueB -= (int) Math.pow(10, places);
        }

        if (valueB < 0) {
          valueB = 0;
        }

        if (valueB <= 99_999 && !visited[valueB] && count + 1 <= MAX_CHANCE) {
          visited[valueB] = true;
          queue.add(new Node(valueB, count + 1));
        }
      }
    }

    System.out.print("ANG");
  }
}


class Node {

  final int value;
  final int count;

  public Node(int value, int count) {
    this.value = value;
    this.count = count;
  }
}