import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int[] input = Arrays.stream(br.readLine().split(" "))
        .mapToInt(Integer::parseInt).toArray();

    int N = input[0];
    int K = input[1];

    ArrayList<Integer> list = new ArrayList<>();

    for (int i = 1; i <= N; i++) {
      list.add(i);
    }

    int key = K - 1;

    sb.append("<");

    while (!list.isEmpty()) {
//      sb.append("key : ").append(key).append("|");
      if (list.size() == 1) {
        sb.append(list.remove(0)).append(", ");
        break;
      }
      
      sb.append(list.remove(key)).append(", ");
//      list.remove(key);
      key += K - 1;

      while (key > list.size() - 1) {
        key = key - list.size();
      }

//      sb.append("key: ").append(key).append(" listSize: ").append(list.size()).append("||");

//      System.out.println(sb);
    }

    sb.deleteCharAt(sb.length() - 1).deleteCharAt(sb.length() - 1).append(">");

    System.out.print(sb);
  }
}