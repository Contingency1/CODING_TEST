import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine());

    ArrayDeque<Integer> deque = new ArrayDeque<>();

    for (int i = 0; i < N; i++) {

      String[] str = br.readLine().split(" ");
      int control = Integer.parseInt(str[0]);

      switch (control) {
        case 1: // 정수 X를 덱의 앞에 넣는다. (1 ≤ X ≤ 100,000)
          deque.addFirst(Integer.parseInt(str[1]));
          break;
        case 2: // 정수 X를 덱의 뒤에 넣는다. (1 ≤ X ≤ 100,000)
          deque.addLast(Integer.parseInt(str[1]));
          break;
        case 3: // 덱에 정수가 있다면 맨 앞의 정수를 빼고 출력한다. 없다면 -1을 대신 출력한다.
          sb.append(deque.isEmpty() ? -1 : deque.removeFirst()).append("\n");
          break;
        case 4: // 덱에 정수가 있다면 맨 뒤의 정수를 빼고 출력한다. 없다면 -1을 대신 출력한다.
          sb.append(deque.isEmpty() ? -1 : deque.removeLast()).append("\n");
          break;
        case 5: // 덱에 들어있는 정수의 개수를 출력한다.
          sb.append(deque.size()).append("\n");
          break;
        case 6: // 덱이 비어있으면 1, 아니면 0을 출력한다.
          sb.append(deque.isEmpty() ? 1 : 0).append("\n");
          break;
        case 7: // 덱에 정수가 있다면 맨 앞의 정수를 출력한다. 없다면 -1을 대신 출력한다.
          sb.append(deque.isEmpty() ? -1 : deque.getFirst()).append("\n");
          break;
        case 8: // 덱에 정수가 있다면 맨 뒤의 정수를 출력한다. 없다면 -1을 대신 출력한다.
          sb.append(deque.isEmpty() ? -1 : deque.getLast()).append("\n");
          break;

      }
    }

    sb.deleteCharAt(sb.length() - 1);

    System.out.print(sb);
  }
}