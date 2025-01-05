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

        int[] base = Arrays.stream(br.readLine().split(" "))
            .mapToInt(x -> Integer.parseInt(x))
            .toArray();

        List<Integer> answer = new ArrayList<>();

        while (true) {
            if (base[0] < base[1]) {
                answer.add(base[0]);
                break;
            }
            answer.add(base[0] % base[1]);
            base[0] /= base[1];
        }

      for (int i = answer.size() - 1; i >= 0; i--) {
        if (answer.get(i) > 9){
          int j = answer.get(i) + 55;
          char word = (char)j;
          bw.write(word);
        } else {
          bw.write(String.valueOf(answer.get(i)));
        }

      }

        bw.flush();
        br.close();
        bw.close();
    }
}