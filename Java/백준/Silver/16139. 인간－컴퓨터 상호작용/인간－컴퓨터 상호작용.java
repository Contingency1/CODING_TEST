import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    char[] chars = br.readLine().toCharArray();

    int count = Integer.parseInt(br.readLine());

    for (int i = 1; i <= count; i++) {
      String[] str = br.readLine().split(" ");

      char character = str[0].charAt(0);
      int answer = 0;
      for (int j = Integer.parseInt(str[1]); j <= Integer.parseInt(str[2]); j++) {
//        sb.append("character: ").append(character).append("\n")
//            .append("chars[j]: ").append(chars[j]).append("\n");

        if (chars[j] == character) {
          answer++;
        }
      }
      sb.append(answer).append("\n");
    }

    System.out.print(sb);
  }
}