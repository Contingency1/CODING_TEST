import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

  private static void method(int number, int amount, int max, ArrayList<Integer> list,
      StringBuilder sb) {

    if (amount == 1) {
      for (int i = 0; i < list.size(); i++) {
        sb.append(list.get(i)).append(" ");
      }
      sb.append("\n");
    } else {
      for (int i = number; i <= max; i++) {
        list.add(i);
        method(i, amount - 1, max, list, sb);
        list.remove(list.size() - 1);
      }
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

    int number = input[0];
    int amount = input[1];

    for (int i = 1; i <= number; i++) {
      ArrayList<Integer> list = new ArrayList<>();
      list.add(i);
      method(i, amount, number, list, sb);
    }

    System.out.print(sb);
  }
}