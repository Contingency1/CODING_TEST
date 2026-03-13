import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Comparator;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    final int count = Integer.parseInt(br.readLine());

    StringTokenizer st = new StringTokenizer(br.readLine());

    List<Integer> arr = new ArrayList<>(count);

    for (int i = 0; i < count; i++) {
      arr.add( Integer.parseInt(st.nextToken()));
    }

    arr.sort(Comparator.reverseOrder());

    int answer = 0;

    for (int i = 0; i < count; i++) {
      int buffer = i + arr.get(i) + 1;

      answer = Math.max(answer, buffer);
    }

    System.out.println(answer + 1);
  }
}
