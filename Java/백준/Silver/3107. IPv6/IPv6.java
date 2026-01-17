import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String input = br.readLine();

    Pattern pattern = Pattern.compile("[0-9a-f]{1,4}|::");
    Matcher matcher = pattern.matcher(input);

    List<String> list = new ArrayList<>();

    while (matcher.find()) {
      list.add(matcher.group());
    }

    int left = 8 - list.size();

    for (int i = 0; i < list.size(); i++) {
      String cur = list.get(i);

      if(cur.equals("::")) {
        list.remove(i);
        list.add(i, "0000");

        for (int j = 0; j < left; j++) {
          list.add(i, "0000");
        }

        break;
      }
    }

    for (int i = 0; i < list.size(); i++) {
      String cur = list.get(i);

      if(cur.length() != 4) {
        String newStr = String.format("%4s", cur).replace(' ', '0');
        list.set(i, newStr);
      }
    }

    StringBuilder sb = new StringBuilder();

    for (String l: list) {
      sb.append(l).append(":");
    }

    sb.deleteCharAt(sb.length() - 1);

    System.out.print(sb);
  }
}