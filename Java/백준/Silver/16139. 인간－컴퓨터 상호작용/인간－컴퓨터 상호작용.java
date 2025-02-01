import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    char[] chars = br.readLine().toCharArray();
    int count = Integer.parseInt(br.readLine());

    int[][] freq = new int[26][chars.length];

    freq[chars[0] - 'a'][0] = 1;

    for (int i = 1; i < chars.length; i++) {
      for (int j = 0; j < 26; j++) {
        freq[j][i] = freq[j][i - 1];
      }
      freq[chars[i] - 'a'][i]++;
    }

    for (int i = 0; i < count; i++) {
      String[] str = br.readLine().split(" ");
      char character = str[0].charAt(0);
      int number1 = Integer.parseInt(str[1]);
      int number2 = Integer.parseInt(str[2]);

      int charIndex = character - 'a';
      int minus = (number1 == 0) ? 0 : freq[charIndex][number1 - 1];
      int answer = freq[charIndex][number2];

      sb.append(answer - minus).append("\n");
    }

    System.out.print(sb);
  }
}
