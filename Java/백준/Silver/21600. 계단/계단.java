import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] input = new int[N];

        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 1;
        int cur = 1;
        
        for (int i = 0; i < N; i++) {
            if (input[i] >= cur + 1) {
                cur++;
            } else {
                cur = input[i];
            }

            answer = Math.max(answer, cur);
        }

        System.out.print(answer);
    }
}