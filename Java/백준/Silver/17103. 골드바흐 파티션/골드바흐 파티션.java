import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {


  private static boolean isP(int a) {

    if (a % 3 == 0) {
      return false;
    }

    for (int j = 5; j * j <= a; j += 2) { // 소수인지 직접 판별
      if (a % j == 0) { // 하나라도 아니면 false
        return false;
      }
    }

    return true;
  }


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine());
    int[] input = new int[N];

    for (int i = 0; i < N; i++) {
      input[i] = Integer.parseInt(br.readLine());
    }

    int MAX = 0;

    for (int row : input) {
      MAX = Math.max(row, MAX);
    }
    HashSet<Integer> set = new HashSet<>();
    set.add(2);
    set.add(3);
    int key = 5;

    while (key <= MAX) {
      if (isP(key)) {
        set.add(key);
      }

      if (key % 6 == 1) {
        key += 4;
      } else {
        key += 2;
      }
    }

    for (int row : input) {
      int count = 0;
      if (set.contains(row - 2)) {
        count++;
//        sb.append(row).append(": ").append(2).append(" ").append(row - 2).append("\n");
      }

      for (int i = 3; i <= row / 2; i += 2) {
//        sb.append(row).append(i).append("\n");
        if (set.contains(i) && set.contains(row - i)) {
          count++;
//          sb.append(row).append(": ").append(i).append(" ").append(row - i).append("\n");
        }
      }

      sb.append(count).append("\n");
    }

    sb.deleteCharAt(sb.length() - 1);

    System.out.print(sb);
  }
}