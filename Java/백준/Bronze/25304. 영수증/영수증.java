import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    final long TOTAL = Integer.parseInt(br.readLine());

    final int COUNT = Integer.parseInt(br.readLine());

    long answer = 0L;
    for (int i = 0; i < COUNT; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());

      int price = Integer.parseInt(st.nextToken());
      int amount = Integer.parseInt(st.nextToken());

      answer += (long) price * amount;
    }

    if (answer == TOTAL) {
      System.out.print("Yes");
      return;
    }

    System.out.print("No");
  }
}