import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

  private static void AtoB(int a, int b, StringBuilder sb) { // a - b 사이의 소수 출력

    for (int i = a; i <= b; i += 2) {
      if (i == 2) {
        sb.append(i).append("\n");
        i = 1;
        continue;
      }

      if (i == 3) {
        sb.append(i).append("\n");
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
        Boolean key = true;
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

      sb.append(i).append("\n");
    }

  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int[] input = Arrays.stream(br.readLine().split(" "))
        .mapToInt(Integer::parseInt).toArray();

    if (input[0] == 1) {
      input[0]++;
    }

    AtoB(input[0], input[1], sb);

    sb.deleteCharAt(sb.length() - 1);

    System.out.print(sb);
  }
}