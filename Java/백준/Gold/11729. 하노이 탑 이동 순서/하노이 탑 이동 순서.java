import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {

  private static void Compare(ArrayDeque<Integer> A, ArrayDeque<Integer> B, StringBuilder sb,
      int numberA, int numberB) {

    if (A.isEmpty()) {
      A.add(B.removeLast());
      sb.append(numberB).append(" ").append(numberA).append("\n");
    } else if (B.isEmpty()) {
      B.add(A.removeLast());
      sb.append(numberA).append(" ").append(numberB).append("\n");
    } else {
      if (A.peekLast() > B.peekLast()) {
        A.add(B.removeLast());
        sb.append(numberB).append(" ").append(numberA).append("\n");
      } else {
        B.add(A.removeLast());
        sb.append(numberA).append(" ").append(numberB).append("\n");
      }
    }
  }

  private static void moveOne(ArrayDeque<Integer> A, ArrayDeque<Integer> B, StringBuilder sb,
      int numberA, int numberB) {
    B.add(A.removeLast());
    sb.append(numberA).append(" ").append(numberB).append("\n");
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine());

    int MAX = (int) Math.pow(2, N) - 1;
    sb.append(MAX).append("\n");

    ArrayDeque<Integer> ONE = new ArrayDeque<>();
    ArrayDeque<Integer> TWO = new ArrayDeque<>();
    ArrayDeque<Integer> THREE = new ArrayDeque<>();

    for (int i = N; i >= 1; i--) {
      ONE.add(i);
    }

    int count = 0;
    short pointer = 0;

    boolean isEvenN = N % 2 == 0;

    while (count < Math.pow(2, N) - 1) {
      count++;

      if (isEvenN && count % 2 == 1) {
        if (!ONE.isEmpty() && ONE.peekLast() == 1) {
          moveOne(ONE, TWO, sb, 1, 2);
          pointer = 2;
        } else if (!TWO.isEmpty() && TWO.peekLast() == 1) {
          moveOne(TWO, THREE, sb, 2, 3);
          pointer = 3;
        } else {
          moveOne(THREE, ONE, sb, 3, 1);
          pointer = 1;
        }
      } else if (!isEvenN && count % 2 == 1) {
        if (!ONE.isEmpty() && ONE.peekLast() == 1) {
          moveOne(ONE, THREE, sb, 1, 3);
          pointer = 3;
        } else if (!TWO.isEmpty() && TWO.peekLast() == 1) {
          moveOne(TWO, ONE, sb, 2, 1);
          pointer = 1;
        } else {
          moveOne(THREE, TWO, sb, 3, 2);
          pointer = 2;
        }

      } else {
        if (pointer == 1) {
          Compare(TWO, THREE, sb, 2, 3);
        } else if (pointer == 2) {
          Compare(ONE, THREE, sb, 1, 3);
        } else {
          Compare(ONE, TWO, sb, 1, 2);
        }
      }
    }

    sb.deleteCharAt(sb.length() - 1);
    System.out.print(sb);
  }
}