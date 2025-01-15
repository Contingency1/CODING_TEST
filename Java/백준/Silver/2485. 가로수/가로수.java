import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

public class Main {

  private static int calculation(int a, int b) {

    while (b != 0) {
      int r = a % b;
      a = b;
      b = r;
    }

    return a;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine());
    ArrayList<Integer> input = new ArrayList<>();
    HashSet<Integer> set = new HashSet<>();

    for (int i = 0; i < N; i++) {
      input.add(Integer.parseInt(br.readLine()));
    }

    for (int i = 0; i < N - 1; i++) {
      set.add(input.get(i + 1) - input.get(i));
    }

    int[] key = new int[set.size()];

    int index = 0;

    for (int row : set) {
      key[index++] = row;
    }

    int GCD = key.length == 1 ? key[0] : calculation(key[0], key[1]);

    for (int i = 1; i < key.length - 1; i++) {
      GCD = calculation(GCD, key[i + 1]);
    }

    int answer = 0;

    for (int i = 0; i < input.size() - 1; i++) {
      answer += (input.get(i + 1) - input.get(i)) / GCD - 1;
    }

    sb.append(answer);
    System.out.print(sb);
  }
}