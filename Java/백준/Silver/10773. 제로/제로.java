import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine());

    ArrayList<Long> List = new ArrayList<>();

    for (int i = 0; i < N; i++) {
      long number = Integer.parseInt(br.readLine());
      if (number == 0) {
        List.remove(List.size() - 1);
      } else {
        List.add(number);
      }
    }

    long answer = 0;

    for (Long row : List) {
      answer += row;
    }

    sb.append(answer);
    System.out.print(sb);
  }
}