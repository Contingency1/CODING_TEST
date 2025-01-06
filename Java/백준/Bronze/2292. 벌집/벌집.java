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
        int base = 1;
        int i = 0;

        while(true) {
            base += 6 * i;
            if(base >= input){
                bw.write(String.valueOf(i + 1) + "\n");
                break;
            }
            i++;
        }

        bw.flush();
        br.close();
        bw.close();
    }
}