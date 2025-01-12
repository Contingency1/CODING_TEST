import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int amount = Integer.parseInt(br.readLine());

    int[] arr = br.lines().mapToInt(Integer::parseInt).toArray();

    Arrays.sort(arr);
    for (int i = 0; i < amount; i++) {
      sb.append(arr[i]).append("\n");
    }
    System.out.print(sb);
  }
}