import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Objects;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    int CASE = Integer.parseInt(br.readLine());

    for (int i = 0; i < CASE; i++) {
      char[] charArray = br.readLine().toCharArray();
      int amount = Integer.parseInt(br.readLine());
      StringBuilder input = new StringBuilder(br.readLine());

      input.deleteCharAt(0);
      input.deleteCharAt(input.length() - 1);

      Deque<String> deque = new ArrayDeque<>(Arrays.asList(input.toString().split(",")));

      if (Objects.equals(deque.peekFirst(), "")) {
        deque.pollFirst();
      }
      boolean reverse = false;
      boolean flag = false;

      for (char c : charArray) {
        if (c == 'R') {
          reverse = !reverse;
        } else if (c == 'D') {
          if (deque.isEmpty()) {
            sb.append("error\n");
            flag = true;
            break;
          }
          if (!reverse) {
            deque.pollFirst();
          } else {
            deque.pollLast();
          }
        }
      }

      if (flag) {
        continue;
      }

      sb.append("[");
      while (!deque.isEmpty()) {
        sb.append(reverse ? deque.pollLast() : deque.pollFirst());
        if (!deque.isEmpty()) {
          sb.append(",");
        }
      }
      sb.append("]\n");
    }

    sb.deleteCharAt(sb.length() - 1);
    System.out.print(sb);
  }
}