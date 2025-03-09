import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    String s1 = br.readLine();
    String s2 = br.readLine();

    char[] input1 = new char[s1.length() + 1];
    char[] input2 = new char[s2.length() + 1];

    for (int i = 1; i <= s1.length(); i++) {
      input1[i] = s1.charAt(i - 1);
    }

    for (int i = 1; i <= s2.length(); i++) {
      input2[i] = s2.charAt(i - 1);
    }

    int[][] dp = new int[input1.length][input2.length];

    for (int i = 0; i < input1.length; i++) {
      for (int j = 0; j < input2.length; j++) {
        if (i == 0 || j == 0) {
          dp[i][j] = 0;
        } else if (input1[i] == input2[j]) {
          dp[i][j] = dp[i - 1][j - 1] + 1;
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }

    int answer = 0;
    for (int[] ints : dp) {
      for (int row : ints) {
        answer = Math.max(answer, row);
      }
    }

    sb.append(answer);

    System.out.print(sb);
  }
}
