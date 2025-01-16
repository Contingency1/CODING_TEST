import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine());

    for (int i = 0; i < N; i++) {
      char[] charArray = br.readLine().toCharArray();
      ArrayList<Character> List = new ArrayList<>();

      for (char c : charArray) {
        List.add(c);
      }

      for (int j = 0; j < List.size() - 1; j++) {
//        sb.append(List.get(j)).append(" ").append(List.get(j + 1)).append("\n");
//        sb.append(List.get(j) == '(').append(" ").append(List.get(j + 1) == ')').append("\n");
        if (List.get(j) == '(' && List.get(j + 1) == ')') {
          List.remove(j);
          List.remove(j);
          j = -1;
        }
      }

      if (List.isEmpty()) {
        sb.append("YES").append("\n");
      } else {
        sb.append("NO").append("\n");
      }
    }

    sb.deleteCharAt(sb.length() - 1);
    System.out.print(sb);
  }
}