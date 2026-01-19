import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int CASE = Integer.parseInt(br.readLine());

    for (int i = 0; i < CASE; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());

      int length = Integer.parseInt(st.nextToken());
      int count = Integer.parseInt(st.nextToken());
      
      int[] arr = new int[count];

      for (int j = 0; j < count; j++) {
        arr[j] = Integer.parseInt(br.readLine());
      }

      long min = 0;
      long max = 0;

      for (int a: arr) {
        min = Math.max(min, Math.min(length - a, a));

        max = Math.max(max, a);
        max = Math.max(max, length - a);
      }

      System.out.println(min + " " + max);
    }

  }
}