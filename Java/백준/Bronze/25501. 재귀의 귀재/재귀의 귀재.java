import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  static int count = 0;

  public static int recursion(String s, int l, int r) {
    count++;

    if (l >= r) {
      return 1;
    } else if (s.charAt(l) != s.charAt(r)) {
      return 0;
    } else {
      return recursion(s, l + 1, r - 1);
    }
  }

  public static String[] isPalindrome(String s) {
    count = 0;

    int answer = recursion(s, 0, s.length() - 1);

    return new String[]{String.valueOf(count), String.valueOf(answer)};
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    int T = Integer.parseInt(br.readLine());
    for (int i = 0; i < T; i++) {
      String s = br.readLine();
      String[] answer = isPalindrome(s);
      sb.append(answer[1]).append(" ").append(answer[0]).append("\n");
    }

    sb.deleteCharAt(sb.length() - 1);
    System.out.print(sb);
  }
}