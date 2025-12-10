    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.StringTokenizer;

    public class Main {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            StringTokenizer st = new StringTokenizer(br.readLine());

            final int N = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[] answer = new int[N + 1];

            for (int i = 1; i < N + 1; i++) {
                answer[i] = i;
            }

            if (k == 0) {
                print(answer);
                return;
            }

            answer = new int[N + 1];

            boolean[] used = new boolean[N + 1];

            int idx = 1;
            for (int i = N; i > 0; i--) {
                if (k == 0) {
                    break;
                }

                if (k >= i - 1) {
                    k -= (i - 1);
                    answer[idx++] = i;
                    used[i] = true;
                }
            }

            for (int i = 1; i < used.length; i++) {
                if (!used[i]) {
                    answer[idx++] = i;
                }
            }

            print(answer);
        }

        static void print(int[] answer) {
            StringBuilder sb = new StringBuilder();

            for (int i = 1; i < answer.length; i++) {
                sb.append(answer[i]).append(" ");
            }

            sb.deleteCharAt(sb.length() - 1);
            
            System.out.print(sb);
        }
    }