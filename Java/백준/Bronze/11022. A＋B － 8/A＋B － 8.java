import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final int COUNT = Integer.parseInt(br.readLine());

    for (int i = 1; i <= COUNT; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int one = Integer.parseInt(st.nextToken());
      int two = Integer.parseInt(st.nextToken());

      System.out.print("Case #" + i + ": ");
      System.out.print(one + " + " + two + " = ");
      System.out.print(one + two);
      System.out.println();
    }
  }

}

