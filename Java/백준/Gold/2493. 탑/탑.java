import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.ArrayDeque;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int count = Integer.parseInt(br.readLine());

    StringTokenizer st = new StringTokenizer(br.readLine());

    ArrayDeque<Number> queue = new ArrayDeque<>();

    int[] answer = new int[count];

    for (int i = 1; i <= count; i++) {
      int cur = Integer.parseInt(st.nextToken());

      Number N = new Number(i, cur);

      if (queue.isEmpty()) {
        answer[i - 1] = 0;
        queue.addLast(N);
        continue;
      }

      while (!queue.isEmpty() && queue.peekLast().value < cur) {
        queue.pollLast();
      }

      if (queue.isEmpty()) {
        answer[i - 1] = 0;
        queue.addLast(N);
        continue;
      }

      answer[i - 1] = queue.peekLast().idx;
      queue.addLast(N);
    }

    for (int i: answer) {
      System.out.println(i);
    }
  }

  static class Number {
    int idx;
    int value;

    Number(int idx, int value) {
      this.idx = idx;
      this.value = value;
    }
  }
}