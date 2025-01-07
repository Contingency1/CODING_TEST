import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int[] input = br.lines()
        .mapToInt(Integer::parseInt)
        .toArray();

    StringBuilder answer = new StringBuilder();

    for (int row : input) {
      List<Integer> factor = new ArrayList<>();

      if (row == -1) {
        break;
      }

      for (int i = 1; i * i <= row; i++) {
        if (row % i == 0) {
          if (i * i == row) {
            factor.add(i);
          } else {
            factor.add(i);
            factor.add(row / i);
          }
        }
      }

      int sum = 0;
      for (Integer FRow : factor) {
        if (row != FRow) {
          sum += FRow;
        }
      }

      if (row == sum) {

        StringBuilder sb = new StringBuilder();
        sb.append(sum + " = 1");
        factor.sort(null);

        for (Integer factors : factor) {
          if (factors != 1 && factors != sum) {
            sb.append(" + " + factors);
          }
        }
        answer.append(sb + "\n");
      } else {
        answer.append(row + " is NOT perfect." + "\n");
      }
    }

    answer.setLength(answer.length() - 1);
    bw.write(String.valueOf(answer));

    bw.flush();
    br.close();
    bw.close();
  }
}
