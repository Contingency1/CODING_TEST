    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.StringTokenizer;

    public class Main {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            final int PRESENT_COUNT = Integer.parseInt(br.readLine());

            int[] count = new int[4];

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < PRESENT_COUNT; i++) {
                int cur = Integer.parseInt(st.nextToken());
                count[cur]++;
            }

            int answer = 0;

            for (int i = 0; i <= PRESENT_COUNT / 2; i++) {
                if (count[0] > 0 && count[3] > 0) {
                    answer += 3;
                    count[0]--;
                    count[3]--;
                    continue;
                }

                if (count[1] > 0 && count[2] > 0) {
                    answer += 3;    
                    count[1]--;
                    count[2]--;
                    continue;
                }

                if (count[0] > 0 && count[2] > 0) {
                    count[0]--;
                    count[2]--;
                    answer += 2;
                    continue;
                }
                
                if (count[1] > 0 && count[3] > 0) {
                    count[1]--;
                    count[3]--;
                    answer += 2;
                    continue;
                }                
                
                if (count[3] > 0 && count[2] > 0) {
                    count[3]--;
                    count[2]--;
                    answer += 1;
                    continue;
                }

                if (count[1] > 0 && count[0] > 0) {
                    count[1]--;
                    count[0]--;
                    answer += 1;
                    continue;
                }
                 
                if (count[0] > 1) {
                    count[0] -= 2;
                    continue;
                }

                if (count[3] > 1) {
                    count[3] -= 2;
                    continue;
                }
            }

            System.out.print(answer);
        }
    }