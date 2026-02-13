import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;


// AI 도움을 받았씁니다..
public class Main {

  static int input;
  static List<StringBuilder> list = new ArrayList<>(3073);

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    input = Integer.parseInt(br.readLine());

    for (int i = 0; i <= input; i++) {
      list.add(new StringBuilder());
    }

    solve(input);

    StringBuilder answer = new StringBuilder();

    for (int i = 1; i <= input; i++) {
      answer.append(list.get(i)).append("\n");
    }

    answer.deleteCharAt(answer.length() - 1);
    System.out.print(answer);
  }


  static void solve(int n) {
    if (n == 3) {
        list.get(1).append("  *  ");
        list.get(2).append(" * * ");
        list.get(3).append("*****");
        return;
    }

    solve(n / 2);

    for (int i = 1; i <= n / 2; i++) {
      StringBuilder cur = list.get(i);
      
      String top = cur.toString();

      cur.setLength(0);
      
      cur
        .append(" ".repeat(n / 2))
        .append(top)
        .append(" ".repeat(n / 2));
        
      list.get(i + n / 2)
        .append(top)
        .append(" ")
        .append(top);
    }

  }
}