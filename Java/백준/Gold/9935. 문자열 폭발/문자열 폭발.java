import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    // 1보다 크거나 같고, 1,000,000보다 작거나 같다.
    char[] input = br.readLine().toCharArray();
    // 1보다 크거나 같고, 36보다 작거나 같다.
    char[] target = br.readLine().toCharArray();

    ArrayList<Character> temp = new ArrayList<>();

    int pointer = 0;

    for (char c : input) {
      temp.add(c);

      if (temp.size() >= target.length && temp.get(temp.size() - 1) == c) {

        // 1, 0
        for (int i = target.length - 1; i >= 0; i--) {
          if (temp.get(temp.size() - (target.length - i)) != target[i]) {
            break;
          }

          if (i == 0) {
            for (int j = 0; j < target.length; j++) {
              temp.remove(temp.size() - 1);
            }
          }
        }
      }

    }

    if (temp.isEmpty()) {
      sb.append("FRULA");
    } else {
      for (Character character : temp) {
        sb.append(character);
      }
    }

    System.out.print(sb);
  }

}
