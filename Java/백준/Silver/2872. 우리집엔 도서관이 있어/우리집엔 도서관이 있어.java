import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] input = new int[N + 1];
        boolean flag = true;

        int floor = 0;

        for (int i = 1; i <= N; i++) {
            input[i] = Integer.parseInt(br.readLine());

            if(input[i] == N) {
                floor = i;
            }
            if (flag && input[i] != i) {
                flag = false;
            }
        }

        if (flag) {
            System.out.print(0);
            return;
        }

        int answer = N - floor;


        int buffer = N;

        for (int i = floor; i >= 1; i--) {
            if (input[i] == buffer) {
                buffer--;
            } else {
                answer++;

            }
        }

        System.out.print(answer);
    }
}