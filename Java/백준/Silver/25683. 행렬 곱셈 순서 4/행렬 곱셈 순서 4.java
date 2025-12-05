import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        long[] input = new long[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());

        input[0] = Integer.parseInt(st.nextToken());
        input[1] = Integer.parseInt(st.nextToken());

        int nextIdx = 2;

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            st.nextToken();
            long number = Long.parseLong(st.nextToken());
            input[nextIdx++] = number;
        }

        long answer = input[N] * input[N - 1] * input[N - 2];

        long last = input[N];
        int idx = N - 2;
        
        if (N == 2) {
            System.out.print(answer);
            return;
        }

        while (true) {
            long buffer = input[idx - 1] * input[idx];
         
            answer += buffer * last;

            if (idx <= 1) {
                break;
            }

            idx--;
        }

        System.out.print(answer);
    }

}