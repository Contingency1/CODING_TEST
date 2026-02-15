import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    final int start = Integer.parseInt(st.nextToken());
    final int end = Integer.parseInt(st.nextToken());

    List<Mass> list = new ArrayList<>();
    StringBuilder sb;

    for (int i = start; i <= end; i++) {
      sb = new StringBuilder();

      char[] cur = String.valueOf(i).toCharArray();

      for (char c : cur) {
        sb.append(Number.charToNumber(c)).append(" ");
      }

      list.add(new Mass(i, sb.toString()));
    }

    list.sort(null);

    sb = new StringBuilder();
    for (int i = 0; i < list.size(); i++) {
      if ((i + 1) % 10 == 0) {
        sb.append(list.get(i)).append("\n");
      } else {
        sb.append(list.get(i)).append(" ");
      }
    }

    sb.deleteCharAt(sb.length() - 1);

    System.out.print(sb);
  }

  static class Mass implements Comparable<Mass> {

    int number;
    String str;

    public Mass(int number, String str) {
      this.number = number;
      this.str = str;
    }

    @Override
    public String toString() {
      return String.valueOf(this.number);
    }

    @Override
    public int compareTo(Mass o) {
      return this.str.compareTo(o.str);
    }
  }

  enum Number {
    ONE("one", '1'), TWO("two", '2'), THREE("tree", '3'),
    FOUR("four", '4'), FIVE("five", '5'), SIX("six", '6'),
    SEVEN("seven", '7'), EIGHT("eight", '8'), NINE("nine", '9'),
    ZERO("zero", '0');

    String str;
    char number;

    Number(String str, char number) {
      this.str = str;
      this.number = number;
    }

    static String charToNumber(int input) {
      for (Number n : Number.values()) {
        if (n.number == input) {
          return n.str;
        }
      }
      throw new RuntimeException("NOOOHOOOOOOO~~~~~~");
    }
  }
}