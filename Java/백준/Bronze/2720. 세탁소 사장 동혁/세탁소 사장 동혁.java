import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Integer.parseInt(br.readLine());
        int[] arr = br.lines().mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < arr.length; i++) {
            bw.write(arr[i] / 25 + " ");
            arr[i] %= 25;
            bw.write(arr[i] / 10 + " ");
            arr[i] %= 10;
            bw.write(arr[i] / 5 + " ");
            arr[i] %= 5;
            bw.write(arr[i] + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }
}