import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  private static boolean judge(char[] charArray) {
    for (int i = 0; i < charArray.length - 2; i++) {
      if (charArray[i] == '6' &&
          charArray[i] == charArray[i + 1] &&
          charArray[i] == charArray[i + 2]) {
        return true;
      }
    }

    return false;
  }


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int input = Integer.parseInt(br.readLine());
    int startIndex = 1;
    int start = 666;

    while (true) {
      // 문자열을 char[]로 변환
      char[] charArray = String.valueOf(start).toCharArray();

      if (judge(charArray)) {
        // 각 원소들중 연속해서 666이 한번만 나오면 되고

        if (startIndex == input) {
          // 그 중에서 startIndex가 input과 같으면 성공하면됨

          sb.append(start);
          break;
        } else {
          // startIndex가 input과 같지 않으면 startIndex++
          startIndex++;
        }
      }

      // 무조건 ++해서 1씩 더해서 검증하는걸로 하자.
      start++;
    }

    System.out.print(sb);
  }
}