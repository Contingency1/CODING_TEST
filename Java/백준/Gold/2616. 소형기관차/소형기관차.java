import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.List;

// AI의 도움을 받았음. 쉽지 않다...
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int COUNT = Integer.parseInt(br.readLine());
        int[] train = new int[COUNT + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= COUNT; i++) {
            train[i] = Integer.parseInt(st.nextToken());
        }

        final int AMOUNT = Integer.parseInt(br.readLine());

        if (AMOUNT == 1) {
            List<Integer> list = Arrays.stream(train)
                                          .boxed() 
                                          .collect(Collectors.toList());

            list.sort(null);
            System.out.print(list.get(list.size() - 1) + list.get(list.size() - 2) + list.get(list.size() - 3));            
            return;
        }

        int[] sum = new int[COUNT + 1];
        for (int i = 1; i <= COUNT; i++) {
            sum[i] = sum[i - 1] + train[i];
        }

        int[][] dp = new int[4][COUNT + 1];

        for (int i = 1; i < 4; i++) {
            for (int j = i * AMOUNT; j <= COUNT; j++) {
                int case1 = dp[i][j - 1];

                int buffer = sum[j] - sum[j - AMOUNT];
                int case2 = dp[i - 1][j - AMOUNT] + buffer;

                dp[i][j] = Math.max(case1, case2);
            }
        }

        System.out.print(dp[3][COUNT]);
    }
}