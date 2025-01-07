import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[][] input = br.lines()
            .map(x -> Arrays.stream(x.split(" "))
                .mapToInt(Integer::parseInt).toArray())
            .toArray(int[][]::new);

        List<Integer> factor = new ArrayList<>();

        for (int i = 1; i <= Math.sqrt(input[0][0]); i++) {
            if(input[0][0] % i == 0) {
              if(i == Math.sqrt(input[0][0])){
                factor.add(i);
              } else {
                factor.add(i);
                factor.add(input[0][0] / i);
              }
            }
        }

        factor.sort(null);

        if (input[0][1] > factor.size()) {
          bw.write(String.valueOf(0));
        } else {
          bw.write(String.valueOf(factor.get(input[0][1] - 1)));
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
