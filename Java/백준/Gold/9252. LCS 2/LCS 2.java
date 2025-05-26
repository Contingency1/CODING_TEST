import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    char[] input1 = br.readLine().toCharArray();
    char[] input2 = br.readLine().toCharArray();
    br.close();

    char[] chars1 = new char[input1.length + 1];
    char[] chars2 = new char[input2.length + 1];

    System.arraycopy(input1, 0, chars1, 1, input1.length);
    System.arraycopy(input2, 0, chars2, 1, input2.length);

    int[][] arr = new int[chars1.length][chars2.length];

    for (int i = 0; i < chars1.length; i++) {
      for (int j = 0; j < chars2.length; j++) {
        if (i == 0 || j == 0) {
          arr[i][j] = 0;
          continue;
        }

        if (chars1[i] == chars2[j]) {
          arr[i][j] = arr[i - 1][j - 1] + 1;
          continue;
        }

        arr[i][j] = Math.max(arr[i - 1][j], arr[i][j - 1]);
      }
    }

    int i = arr.length - 1;
    int j = arr[0].length - 1;
    sb.append(arr[i][j]).append("\n");
    char[] answer = new char[arr[i][j]];
    int answerIndex = 0;

    while (true) {
      if (arr[i][j] == 0) {
        break;
      }

      if (arr[i - 1][j] == arr[i][j]) {
        i--;
        continue;
      }

      if (arr[i][j - 1] == arr[i][j]) {
        j--;
        continue;
      }

      answer[answerIndex++] = chars1[i];
      i--;
      j--;
    }
    for (int k = answer.length - 1; k >= 0; k--) {
      sb.append(answer[k]);
    }
    System.out.print(sb);
  }

}
