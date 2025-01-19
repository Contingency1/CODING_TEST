import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int arrayLength = Integer.parseInt(br.readLine());

    int[] values = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

    ArrayDeque<Integer> stack = new ArrayDeque<>();
    int[] array = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

    for (int i = 0; i < arrayLength; i++) {
      if (values[i] == 0) {
        stack.add(array[i]);
      }
    }

    int inputLength = Integer.parseInt(br.readLine());

    int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    for (int i = 0; i < inputLength; i++) {
      stack.addFirst(input[i]);
      sb.append(stack.removeLast()).append(" ");
    }
    System.out.print(sb);
  }
}