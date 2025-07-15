import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    final int BOOK_COUNT = Integer.parseInt(st.nextToken());
    final int CAN_HAVE = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());

    List<Integer> plus = new ArrayList<>();
    List<Integer> minus = new ArrayList<>();

    while (st.hasMoreTokens()) {
      int number = Integer.parseInt(st.nextToken());
      if (number >= 0) {
        plus.add(number);
        continue;
      }

      minus.add(number);
    }

    plus.sort(Comparator.comparingInt(a -> a));
    minus.sort(Comparator.reverseOrder());

    int answer = 0;
    int minusMax = minus.isEmpty() ? 0 : Math.abs(minus.get(minus.size() - 1));
    int plusMax = plus.isEmpty() ? 0 : plus.get(plus.size() - 1);

    answer -= Math.max(plusMax, minusMax);
    while (CAN_HAVE <= plus.size()) {
      answer += plus.get(plus.size() - 1) * 2;
      remove(plus, CAN_HAVE);
    }

    while (CAN_HAVE <= minus.size()) {
      answer += Math.abs(minus.get(minus.size() - 1)) * 2;
      remove(minus, CAN_HAVE);
    }

    if (!plus.isEmpty()) {
      answer += plus.get(plus.size() - 1) * 2;
    }
    if (!minus.isEmpty()) {
      answer += Math.abs(minus.get(minus.size() - 1)) * 2;
    }

    System.out.print(answer);

  }

  private static void remove(List<Integer> list, int CAN_HAVE) {
    for (int i = 0; i < CAN_HAVE; i++) {
      list.remove(list.size() - 1);
    }
  }
}

