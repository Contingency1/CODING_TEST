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

        int area = Integer.parseInt(br.readLine()) * 100;

        int[][] all = new int[100][100];

        int answer = 0;

        for (int[] value : all) {
            Arrays.fill(value, 0);
        }

        int[][] base = br.lines().map(
            x -> Arrays.stream(x.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray())
            .toArray(int[][]::new);

        for (int[] element : base) {
            for(int i = element[0]; i <= element[0] + 9; i++) {
                for(int j = element[1]; j <= element[1] + 9; j++) {
                    all[i][j] = 1;
                }
            }
        }

        for (int[] element : all) {
            for (int i = element[0]; i < element.length; i++) {
                if(element[i] == 1){
                    answer += 1;
                }
            }
        }

        bw.write(String.valueOf(answer)+"\n");
        bw.flush();
        br.close();
        bw.close();
    }
}