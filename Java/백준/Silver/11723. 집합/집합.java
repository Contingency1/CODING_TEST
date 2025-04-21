import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int M = Integer.parseInt(br.readLine());

    int answer = 0;

    for (int i = 0; i < M; i++) {
      String[] s = br.readLine().split(" ");
      String command = s[0];
      int number = s.length > 1 ? Integer.parseInt(s[1]) - 1 : 0;

      switch (command) {
        case "add":
          answer = answer | (1 << number);
          break;
        case "remove":
          answer = answer & ~(1 << number);
          break;
        case "check":
          if ((answer & (1 << number)) > 0) {
            sb.append(1).append("\n");
          } else {
            sb.append(0).append("\n");
          }
          break;
        case "toggle":
          answer = answer ^ (1 << number);
          break;
        case "all":
          for (int j = 0; j <= 19; j++) {
            answer = answer | (1 << j);
          }
          break;
        case "empty":
          answer = 0;
      }
    }

    System.out.print(sb);
  }

}
