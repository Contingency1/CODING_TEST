import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {

    static int PRESENT_COUNT, BUDGET, SALE_COUNT;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        PRESENT_COUNT = Integer.parseInt(st.nextToken());
        BUDGET = Integer.parseInt(st.nextToken());
        SALE_COUNT = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());


        long[] arr = new long[PRESENT_COUNT];

        for (int i = 0; i < PRESENT_COUNT; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);

        long[] sum = new long[PRESENT_COUNT];

        sum[0] = arr[0];
        for (int i = 1; i < PRESENT_COUNT; i++) {
            sum[i] = arr[i] + sum[i - 1];
        }

        int answer = 0;

        for (int i = 0; i < PRESENT_COUNT; i++) {
            long cur;

            if (i < SALE_COUNT) {
                cur = sum[i] / 2;
            } else {
                cur = sum[i - SALE_COUNT] + (sum[i] - sum[i - SALE_COUNT]) / 2;
            }

            if (cur <= BUDGET) {
                answer = i + 1;
            } else {
                break;
            }
        }

        System.out.print(answer);
    }
}