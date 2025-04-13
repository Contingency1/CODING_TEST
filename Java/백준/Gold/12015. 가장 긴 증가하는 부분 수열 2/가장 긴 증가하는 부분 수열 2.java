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

    int answer = 0;

    ArrayList<Integer> list = new ArrayList<>();
    list.add(input[0]);

    for (int j = 1; j < N; j++) {
      int buffer = list.get(list.size() - 1);

      if (input[j] > buffer) {
        list.add(input[j]);
      } else if (input[j] < buffer) {
        BS(list, input[j]);
      }
    }

    answer = list.size();

    sb.append(answer);

    System.out.print(sb);
  }

  static void BS(ArrayList<Integer> list, int n) {
    int L = 0;
    int R = list.size() - 1;

    int key = list.size();

    while (L <= R) {
      int MID = (L + R) / 2;

      if (n <= list.get(MID)) {
        key = MID;
        R = MID - 1;
      } else {
        L = MID + 1;
      }
    }

    if (key == list.size()) {
      list.add(n);
    } else {
      list.set(key, n);
    }
  }
}
