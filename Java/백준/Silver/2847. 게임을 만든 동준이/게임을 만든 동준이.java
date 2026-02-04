import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int count = Integer.parseInt(br.readLine());

    List<Integer> list = new ArrayList<>();

    for (int i = 0; i < count; i++) {
      list.add(Integer.parseInt(br.readLine()));
    }

    int prev = list.get(count - 1);

    int answer = 0;

    for (int i = count - 2; i >= 0; i--) {
      int cur = list.get(i);

      if(cur >= prev) {
        prev = prev - 1;
        answer += cur - prev;
      } else {
        prev = cur;
      }
    }

    System.out.print(answer);
  }

}