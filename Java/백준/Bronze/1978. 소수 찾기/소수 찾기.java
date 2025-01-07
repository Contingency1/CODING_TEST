import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    br.readLine();
    int[] input = Arrays.stream(
        br.readLine().split(" ")
    ).mapToInt(Integer::parseInt).toArray();
    int answer = 0;

    for (int row : input) {
      boolean factor = false;

      if (row != 1) {
        for (int i = 2; i * i <= row; i++) {
          if (row % i == 0) {
            factor = true;
            break;
          }
        }

        if (!factor) {
          answer++;
        }
      }
    }

    bw.write(String.valueOf(answer));

    bw.flush();
    br.close();
    bw.close();
  }
}
