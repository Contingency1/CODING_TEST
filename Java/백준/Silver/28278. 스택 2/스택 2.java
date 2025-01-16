import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine());

    ArrayList<Integer> List = new ArrayList<>();

    for (int i = 0; i < N; i++) {
      String words = String.valueOf(br.readLine());

      int number = Integer.parseInt(words.split(" ")[0]);
      if (number == 1) { // 정수 X를 스택에 넣는다. (1 ≤ X ≤ 100,000)
        List.add(Integer.parseInt(words.split(" ")[1]));
      } else if (number == 2) { // 스택에 정수가 있다면 맨 위의 정수를 빼고 출력한다. 없다면 -1을 대신 출력한다.
        if (List.isEmpty()) {
          sb.append(-1).append("\n");
        } else {
          sb.append(List.get(List.size() - 1)).append("\n");
          List.remove(List.size() - 1);
        }
      } else if (number == 3) { // 스택에 들어있는 정수의 개수를 출력한다.
        sb.append(List.size()).append("\n");
      } else if (number == 4) { // 스택이 비어있으면 1, 아니면 0을 출력한다.
        if (List.isEmpty()) {
          sb.append(1).append("\n");
        } else {
          sb.append(0).append("\n");
        }
      } else { // 스택에 정수가 있다면 맨 위의 정수를 출력한다. 없다면 -1을 대신 출력한다.
        if (List.isEmpty()) {
          sb.append(-1).append("\n");
        } else {
          sb.append(List.get(List.size() - 1)).append("\n");
        }
      }
    }

    sb.deleteCharAt(sb.length() - 1);
    System.out.print(sb);
  }
}