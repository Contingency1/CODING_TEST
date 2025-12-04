import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        final int N = Integer.parseInt(st.nextToken());
        final int D = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[] parent = new int[N + 1];

        for (int i = 0; i < N; i++) {
            int cur = Integer.parseInt(st.nextToken());
            parent[cur]++;
        }

        long answer = 0;

        for (int i = 0; i <= N; i++) {
            if (parent[i] > D) {
                answer += (parent[i] - 2) / (D - 1);
            }
        }

        System.out.print(answer);
    }
}