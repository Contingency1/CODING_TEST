import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    char[] chars = br.readLine().toCharArray();

    int[] count = new int[10];
    for (int i = 0; i < chars.length; i++) {
      if (chars[i] == '9') {
        chars[i] = '6';
      }
    }

    for (int i = 0; i < chars.length; i++) {
      count[chars[i] - '0']++;
    }

    int answer = 0;
    int six = 0;

    for (int i = 0; i < 10; i++) {
      if (i == 6) {
        six = count[i];
      } else {
        answer = Math.max(answer, count[i]);
      }
    }

    int one = six % 2 == 1 ? 1 : 0;

    answer = Math.max(answer, six / 2 + one);

    System.out.print(answer);
  }

}