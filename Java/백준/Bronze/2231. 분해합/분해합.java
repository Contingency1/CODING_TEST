import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int input = Integer.parseInt(br.readLine());
    int answer = 1;

    if (input == 1) {
      System.out.print(0);
      return;
    }

    while (true) {
      int[] word = Arrays.stream(String.valueOf(answer).split(""))
          .mapToInt(Integer::parseInt)
          .toArray();

      int key = answer;

      for (int row : word) {
        key += row;
//        sb.append(row + " ");
      }

      if (key == input) {
        sb.append(answer);
        break;
      } else if (answer > input) {
        sb.append(0);
        break;
      }

      answer++;
    }

//    sb.append(input);

    System.out.print(sb);
  }
}