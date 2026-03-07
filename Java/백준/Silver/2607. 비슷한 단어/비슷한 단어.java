import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int count = Integer.parseInt(br.readLine());

    String[] str = new String[count - 1];

    char[] first = br.readLine().toCharArray();

    for (int i = 0; i < count - 1; i++) {
      str[i] = br.readLine();
    }

    int[] base = getInts(first);

    int answer = 0;
    for (String s : str) {
      char[] chars = s.toCharArray();

      if (Math.abs(first.length - chars.length) > 1) {
        continue;
      }

      int[] cur = getInts(chars);

      int diffCount = 0;

      for (int i = 0; i < 26; i++) {
        diffCount += Math.abs(cur[i] - base[i]);
      }

      if (diffCount <= 2) {
        answer++;
      }
    }

    System.out.print(answer);

  }

  private static int[] getInts(char[] first) {
    int[] base = new int[26];

    for (char c : first) {
      base[c - 65]++;
    }
    return base;
  }
}