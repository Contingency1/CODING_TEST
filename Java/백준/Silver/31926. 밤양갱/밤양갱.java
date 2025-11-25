import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int count = Integer.parseInt(br.readLine());

        int answer = 8;

        if (count == 1) {
            System.out.print(answer + 2);
            return;
        }

        int cur = 1;
        count--;

        while (count >= 1) {
            if (count - cur >= 0) {
                cur += cur;
                count -= cur;
                answer++;
                continue;
            }
            
            cur += count;
            count = 0;
            answer++;
        }

        System.out.print(answer + 2);
    }
}