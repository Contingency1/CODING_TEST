    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;

    public class Main {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            char[] input = br.readLine().toCharArray();

            int p = 0;
            int k = 0;
            int pMax = 0;
            int kMax = 0;

            for (int i = 0; i < input.length; i++) {
                if (input[i] == 'K') {
                    if (p > 0) {
                        p--;
                        continue;
                    }
                    k++;

                    kMax = Math.max(kMax, k);
                } else {
                    if (k > 0) {
                        k--;
                        continue;
                    }
                    p++;

                    pMax = Math.max(pMax, p);
                }
            }

            System.out.print(pMax + kMax);
        }
    }