import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
//    push X: 정수 X를 스택에 넣는 연산이다.
//        pop: 스택에서 가장 위에 있는 정수를 빼고, 그 수를 출력한다. 만약 스택에 들어있는 정수가 없는 경우에는 -1을 출력한다.
//    size: 스택에 들어있는 정수의 개수를 출력한다.
//        empty: 스택이 비어있으면 1, 아니면 0을 출력한다.
//    top: 스택의 가장 위에 있는 정수를 출력한다. 만약 스택에 들어있는 정수가 없는 경우에는 -1을 출력한다.
    int N = Integer.parseInt(br.readLine());

    ArrayList<Integer> list = new ArrayList<Integer>();

    for (int i = 1; i <= N; i++) {
      String s = br.readLine();

      if (s.split(" ")[0].equals("push")) {
        list.add(Integer.parseInt(s.split(" ")[1]));
      } else if (s.equals("pop")) {
        if (list.isEmpty()) {
          sb.append("-1").append("\n");
        } else {
          sb.append(list.remove(list.size() - 1)).append("\n");
        }
      } else if (s.equals("size")) {
        sb.append(list.size()).append("\n");
      } else if (s.equals("empty")) {

        if (list.isEmpty()) {
          sb.append("1").append("\n");
        } else {
          sb.append("0").append("\n");
        }
      } else {
        if (list.isEmpty()) {
          sb.append("-1").append("\n");
        } else {
          sb.append(list.get(list.size() - 1)).append("\n");
        }
      }

    }

    System.out.print(sb);
  }
}
