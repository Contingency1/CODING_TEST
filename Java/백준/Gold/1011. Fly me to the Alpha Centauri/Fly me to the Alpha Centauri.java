import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int count = Integer.parseInt(br.readLine());

        for (int i = 0; i < count; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            int diff = to - from;

            if (diff <= 3) {
                System.out.println(diff);
                continue;
            }

            int answer = 2;
            boolean flag = false;
            diff -= 2;

            for (int j = 2; j < to; j++) {

                for (int k = 0; k < 2; k++) {
                    if (diff - j >= 0) {
                        diff -= j;
                        answer++;
                    } else if (diff > 0) {
                        answer++;
                        flag = true;
                        break;
                    } else {
                        flag = true;
                        break;
                    }
                }

                if (flag) {
                    break;
                }
            }
            System.out.println(answer);
        }
    }
}