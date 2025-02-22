import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
//    push X: 정수 X를 큐에 넣는 연산이다.
//    pop: 큐에서 가장 앞에 있는 정수를 빼고, 그 수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
//    size: 큐에 들어있는 정수의 개수를 출력한다.
//    empty: 큐가 비어있으면 1, 아니면 0을 출력한다.
//    front: 큐의 가장 앞에 있는 정수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
//    back: 큐의 가장 뒤에 있는 정수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.

    int x = Integer.parseInt(br.readLine());

    ArrayList<Integer> list = new ArrayList<Integer>();

    for (int i = 0; i < x; i++) {
      String[] s = br.readLine().split(" ");
      switch (s[0]) {
        case "push":
          list.add(Integer.parseInt(s[1]));
          break;
        case "pop":
          if (list.isEmpty()) {
            sb.append(-1).append("\n");
          } else {
            sb.append(list.remove(0)).append("\n");
          }
          break;

        case "size":
          sb.append(list.size()).append("\n");
          break;

        case "empty":
          if (list.isEmpty()) {
            sb.append(1).append("\n");
          } else {
            sb.append(0).append("\n");
          }
          break;

        case "front":
          if (list.isEmpty()) {
            sb.append(-1).append("\n");
          } else {
            sb.append(list.get(0)).append("\n");
          }
          break;
        case "back":
          if (list.isEmpty()) {
            sb.append(-1).append("\n");
          } else {
            sb.append(list.get(list.size() - 1)).append("\n");
          }
      }
    }

    System.out.print(sb);
  }
}
