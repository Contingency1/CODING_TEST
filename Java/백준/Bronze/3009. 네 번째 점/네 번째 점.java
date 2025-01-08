import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int[][] s = br.lines().map(x -> Arrays.stream(x.split(" "))
        .mapToInt(Integer::parseInt).toArray()).toArray(int[][]::new);

    int[] answer = {1, 1};

    Arrays.sort(s, Comparator.comparingInt(a -> a[0]));

    if (s[0][0] == s[1][0]) {
      answer[0] = s[2][0];
    } else {
      answer[0] = s[0][0];
    }

    Arrays.sort(s, Comparator.comparingInt(a -> a[1]));

    if (s[0][1] == s[1][1]) {
      answer[1] = s[2][1];
    } else {
      answer[1] = s[0][1];
    }

    bw.write(answer[0] + " " + answer[1]);

    bw.flush();
    br.close();
    bw.close();
  }
}
