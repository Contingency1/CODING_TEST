import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

  static int M;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

    int N = input[0];
    M = input[1];

    int[] numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    ArrayList<Integer> base = new ArrayList<>();

    Arrays.sort(numbers);

    for (int in : numbers) {
      base.add(in);
    }
    back(base, new ArrayList<>(), sb);

    System.out.print(sb);
  }

  static void back(ArrayList<Integer> base, ArrayList<Integer> arr, StringBuilder sb) {
    if (arr.size() == M) {
      for (Integer i : arr) {
        sb.append(i).append(" ");
      }
      sb.deleteCharAt(sb.length() - 1);
      sb.append("\n");
      return;
    }

    for (int i = 0; i < base.size(); i++) {
      int removed = base.remove(i);
      arr.add(removed);
      back(base, arr, sb);
      arr.remove(arr.size() - 1);
      base.add(i, removed);
    }


  }


}
