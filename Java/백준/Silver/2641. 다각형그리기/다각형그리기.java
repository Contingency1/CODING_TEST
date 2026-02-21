import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int length = Integer.parseInt(br.readLine());

    StringTokenizer st = new StringTokenizer(br.readLine());

    List<Direction> list = new ArrayList<>();

    for (int i = 0; i < length; i++) {
      list.add(Direction.from(st.nextToken().charAt(0)));
    }

    Number start = makeQueue(list, length);

    int targetCount = Integer.parseInt(br.readLine());

    List<List<Direction>> answer = new ArrayList<>();

    for (int i = 0; i < targetCount; i++) {
      st = new StringTokenizer(br.readLine());
      List<Direction> inputList = new ArrayList<>();

      for (int j = 0; j < length; j++) {
        inputList.add(Direction.from(st.nextToken().charAt(0)));
      }

      Number s = makeQueue(inputList, length);

      boolean isMatched = false;

      for (int j = 0; j < length; j++) {

        boolean forwardMatch = true;
        Number p1 = start;
        Number s1 = s;
        for (int k = 0; k < length; k++) {
          if (p1.direction != s1.direction) {
            forwardMatch = false;
            break;
          }
          p1 = p1.next;
          s1 = s1.next;
        }

        if (forwardMatch) {
          isMatched = true;
          break;
        }

        boolean reverseMatch = true;
        Number p2 = start;
        Number s2 = s;
        for (int k = 0; k < length; k++) {
          if (p2.direction != s2.direction.invert()) {
            reverseMatch = false;
            break;
          }
          p2 = p2.next;
          s2 = s2.prev;
        }

        if (reverseMatch) {
          isMatched = true;
          break;
        }

        s = s.next;
      }

      if (isMatched) {
        answer.add(inputList);
      }
    }

    System.out.println(answer.size());

    StringBuilder sb = new StringBuilder();

    for (List<Direction> ans : answer) {
      for (Direction a : ans) {
        sb.append(a).append(" ");
      }
      sb.deleteCharAt(sb.length() - 1).append("\n");
    }

    if (sb.length() != 0) {
      sb.deleteCharAt(sb.length() - 1);
    }

    System.out.print(sb);
  }

  private static Number makeQueue(List<Direction> inputList, int length) {
    Number s = new Number(inputList.get(0));
    Number b = s;

    for (int j = 1; j < length; j++) {
      Number cur = new Number(inputList.get(j));

      cur.prev = s;
      s.next = cur;

      s = cur;
    }

    s.next = b;
    b.prev = s;

    return b;
  }

  static class Number {

    Direction direction;
    Number prev;
    Number next;

    public Number(Direction direction) {
      this.direction = direction;
    }
  }

  enum Direction {

    RIGHT('1'), UP('2'), LEFT('3'), DOWN('4');

    final char number;

    Direction(char number) {
      this.number = number;
    }

    public static Direction from(char input) {
      for (Direction d : Direction.values()) {
        if (d.number == input) {
          return d;
        }
      }

      throw new RuntimeException("NOOO!!");
    }

    public Direction invert() {
      switch (this) {
        case RIGHT:
          return LEFT;
        case UP:
          return DOWN;
        case LEFT:
          return RIGHT;
        case DOWN:
          return UP;
        default:
          throw new RuntimeException("NOOO!!");
      }
    }

    @Override
    public String toString() {
      return String.valueOf(number);
    }
  }
}