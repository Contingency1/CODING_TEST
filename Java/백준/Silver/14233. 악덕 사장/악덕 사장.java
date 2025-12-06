    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.StringTokenizer;
    import java.util.Arrays;

    public class Main {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            final int N = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());

            long[] input = new long[N + 1];

            for (int i = 1; i < N + 1; i++) {
                input[i] = Long.parseLong(st.nextToken());
            }

            Arrays.sort(input);

            long answer = input[1];

            for (int i = 2; i < N + 1; i++) {
                long buffer = input[i] / i;

                if (answer > buffer) {
                    answer = buffer;
                }
            }

            System.out.print(answer);

        }

    }