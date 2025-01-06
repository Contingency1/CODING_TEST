import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int input = Integer.parseInt(br.readLine());
        int base = 2;

        for (int i = 0; i < input; i++){
            base += (int) Math.pow(2, i);
        }

        bw.write(String.valueOf((int)Math.pow(base, 2)));

        bw.flush();
        br.close();
        bw.close();
    }
}