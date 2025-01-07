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

        int[][] input = br.lines()
            .map(line -> Arrays.stream(line.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray()
            ).toArray(int[][]::new);

        for (int[] line : input) {
//          bw.write(Arrays.toString(line) + "\n");

           if (line[0] == 0 && line[1] == 0) {
             bw.flush();
             br.close();
             bw.close();
             return;
           } else if (line[0] == 0 || line[1] == 0 || (line[0] % line[1] != 0 && line[1] % line[0] != 0)) {
               bw.write("neither" + "\n");
           } else if (line[0] % line[1] == 0) {
               bw.write("multiple" + "\n");
           } else if (line[1] % line[0] == 0) {
               bw.write("factor" + "\n");
           }

        }


    }
}
