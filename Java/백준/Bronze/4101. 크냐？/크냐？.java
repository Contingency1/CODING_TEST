import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int one = Integer.parseInt(st.nextToken());
            int two = Integer.parseInt(st.nextToken());

            if (one == 0 && two == 0) {
                break;
            }

            if (one > two) {
                System.out.println("Yes");
                continue;
            }
            System.out.println("No");
        }

    }
}