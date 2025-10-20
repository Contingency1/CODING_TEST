import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] input = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        long[][] dp = new long[N + 1][21];

        dp[1][input[1]] = 1;

        for (int i = 2; i < N; i++) {
            for (int j = 0; j <= 20; j++) {
                if (dp[i - 1][j] != 0) {
                    if (input[i] + j <= 20) {
                        dp[i][input[i] + j] += dp[i - 1][j]; 
                    }

                    if (j - input[i] >= 0) {
                        dp[i][j - input[i]] += dp[i - 1][j]; 
                    }
                }
            }
        }
        System.out.print(dp[N - 1][input[N]]);

    }
}