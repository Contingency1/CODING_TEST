import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine());
    if (N == 1) {
      sb.append("1");
      System.out.print(sb);
      return;
    }
    ArrayDeque<Integer> list = new ArrayDeque<>();

    for (int i = 1; i <= N; i++) {
      list.add(i);
    }

    while (true) {
      list.removeFirst();
      list.add(list.removeFirst());
      if (list.size() == 1) {
        break;
      }
    }

    sb.append(list.getFirst());
    System.out.print(sb);
  }
}