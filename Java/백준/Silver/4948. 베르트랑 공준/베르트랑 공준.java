import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

  private static int AtoB(int a, int b, StringBuilder sb) { // a - b 사이의 소수 출력
    int count = 0;

    for (int i = a + 1; i <= b; i += 2) {
      if (i == 2) {
        count++;
        i = 1;
        continue;
      }

      if (i == 3) {
        count++;
        continue;
      }

      if (i % 2 == 0) {
        i--;
        continue;
      }

      if (i % 3 == 0) {
        continue;
      }

      if (i % 6 == 1 || i % 6 == 5) { // 소수 조건 필터링
        boolean key = true;
        for (int j = 5; j * j <= i; j += 2) { // 소수인지 직접 판별
          if (i % j == 0) { // 하나라도 아니면 break
            key = false;
            break;
          }
        }
        if (!key) {
          continue;
        }
      }

      count++;

    }

    return count;

  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int value = 1;
    ArrayList<Integer> input = new ArrayList<>();
    while (true) {
      value = Integer.parseInt(br.readLine());

      if (value == 0) {
        break;
      }

      input.add(value);
    }

    for (int i = 0; i < input.size(); i++) {
      sb.append(AtoB(input.get(i), input.get(i) * 2, sb)).append("\n");
    }

    sb.deleteCharAt(sb.length() - 1);

    System.out.print(sb);
  }
}