import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String input = br.readLine();
    char[] sortedInput = input.toCharArray();

    Arrays.sort(sortedInput);

    List<Node> list = new ArrayList<>();

    for (char c : sortedInput) {
      list.add(new Node(c));
    }

    char[] str = br.readLine().toCharArray();

    int rowCount = str.length / sortedInput.length;

    int rowStart = 0;

    for (int i = 0; i < input.length(); i++) {
      Node cur = list.get(i);

      int end = rowStart + rowCount;

      for (int j = rowStart; j < end; j++) {
        cur.sb.append(str[j]);

        if (j == end - 1) {
          rowStart = j + 1;
        }
      }
    }

    char[] target = input.toCharArray();
    char[][] buffer = new char[input.length()][rowCount];

    for (int i = 0; i < input.length(); i++) {
      char cur = target[i];

      for (int j = 0; j < list.size(); j++) {
        if (list.get(j).start == cur) {
          buffer[i] = list.get(j).sb.toString().toCharArray();
          list.remove(j);
          break;
        }
      }
    }

    StringBuilder answer = new StringBuilder();

    for (int i = 0; i < buffer[0].length; i++) {

      for (int j = 0; j < buffer.length; j++) {
        answer.append(buffer[j][i]);
      }
    }

    System.out.print(answer);
  }

  static class Node {

    char start;
    StringBuilder sb = new StringBuilder();

    public Node(char start) {
      this.start = start;
    }
  }

}