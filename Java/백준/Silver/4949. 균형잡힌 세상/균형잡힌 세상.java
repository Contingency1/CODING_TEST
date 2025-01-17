import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    while (true) {
      char[] input = br.readLine().toCharArray();

      if (input[0] == '.') {
        break;
      }

      ArrayList<Character> charList = new ArrayList<>();

      for (char row : input) {
        if (row == '(' || row == ')' || row == '[' || row == ']') {
          charList.add(row);
        }
      }

      for (int i = 0; i < charList.size() - 1; i++) {
        char c = charList.get(i);
        boolean flag = false;
        if (c == '(') {
          if (charList.get(i + 1) == ')') {
            charList.remove(i + 1);
            charList.remove(i);
            i = -1;
          }

        } else if (c == '[') {
          if (charList.get(i + 1) == ']') {
            charList.remove(i + 1);
            charList.remove(i);
            i = -1;
          }
        }

      }

      if (!charList.isEmpty()) {
        sb.append("no").append("\n");
      } else {
        sb.append("yes").append("\n");
      }
    }

    sb.deleteCharAt(sb.length() - 1);

    System.out.print(sb);
  }
}