import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  private static void Khan(StringBuilder answer, int startIdx, int endIdx, int spaceCount) {
    StringBuilder space = new StringBuilder();
    for (int i = 0; i < spaceCount; i++) {
      space.append(" ");
    }

    answer.replace(startIdx, endIdx, space.toString());

    if (startIdx + 1 == endIdx) {
      answer.replace(startIdx, endIdx, space.toString());
    } else {
      Khan(answer, startIdx - 2 * spaceCount / 3, startIdx - spaceCount / 3, spaceCount / 3);
      Khan(answer, endIdx + spaceCount / 3, endIdx + 2 * spaceCount / 3, spaceCount / 3);
    }

  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    while (true) {
      StringBuilder answer = new StringBuilder();
      String line = br.readLine();
      int count;

      if (line == null) {
        break;
      } else if (line.equals("0")) {
        sb.append("-").append("\n");
        continue;
      } else {
        count = Integer.parseInt(line);
      }

      int multiple = (int) Math.pow(3, count);

      for (int i = 0; i < multiple; i++) {
        answer.append("-");

      }

      Khan(answer, multiple / 3, multiple / 3 * 2, answer.length() / 3);

      sb.append(answer).append("\n");
    }

    System.out.print(sb);
  }
}