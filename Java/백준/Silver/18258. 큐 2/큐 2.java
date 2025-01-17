import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Objects;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine());
    ArrayDeque<Integer> list = new ArrayDeque<>();
    for (int i = 1; i <= N; i++) {
      String[] s = br.readLine().split(" ");

      if (Objects.equals(s[0], "push")) {
        // 정수 X를 큐에 넣는 연산이다.
        list.add(Integer.parseInt(s[1]));
      } else if (Objects.equals(s[0], "empty")) {
        // 큐가 비어있으면 1, 아니면 0을 출력한다.
        if (list.isEmpty()) {
          sb.append(1).append("\n");
        } else {
          sb.append(0).append("\n");
        }
      } else if (Objects.equals(s[0], "size")) {
        // 큐에 들어있는 정수의 개수를 출력한다.
        sb.append(list.size()).append("\n");
      } else if (Objects.equals(s[0], "pop")) {
        // 큐에서 가장 앞에 있는 정수를 빼고, 그 수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
        if (list.isEmpty()) {
          sb.append(-1).append("\n");
        } else {
          sb.append(list.removeFirst()).append("\n");
        }
      } else if (Objects.equals(s[0], "front")) {
        // 큐의 가장 앞에 있는 정수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
        if (list.isEmpty()) {
          sb.append(-1).append("\n");
        } else {
          sb.append(list.getFirst()).append("\n");
        }
      } else {
        // 큐의 가장 뒤에 있는 정수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
        if (list.isEmpty()) {
          sb.append(-1).append("\n");
        } else {
          sb.append(list.getLast()).append("\n");
        }
      }

    }

    if (sb.length() > 0) {
      sb.deleteCharAt(sb.length() - 1);
    }
    System.out.print(sb);
  }
}