import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine());

    int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

    ArrayList<Integer[]> list = new ArrayList<>();

    for (int i = 1; i <= N; i++) {
      Integer[] arr = {i, input[i - 1]};
      list.add(arr);
    }

    int index = 0; // 현재 인덱스
    int target = list.remove(index)[1];

    sb.append(1).append(" ");

    if (!list.isEmpty()) {
      index = target > 0 ? index + target - 1 : index + target;

      while (true) {
        if (list.size() == 1) {
          sb.append(list.get(0)[0]);
          break;
        }

        if (index > list.size() - 1) {
          index = index % list.size();
        } else if (index < 0) {
          //(index % size + size) % size
          int size = list.size();
          index = (index % size + size) % size;
        }

        sb.append(list.get(index)[0]).append(" ");

        target = list.remove(index)[1];

        index = target > 0 ? index + target - 1 : index + target;

//        sb.append("size: ").append(list.size()).append("\n").append(index);
//        System.out.println(sb);
      }
    }

    System.out.print(sb);
  }
}