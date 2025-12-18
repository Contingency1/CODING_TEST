    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;

    public class Main {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String source = br.readLine();
            String target = br.readLine();

            int length = Math.min(source.length(), target.length());

            int idx = 0;
            int answer = 0;

            while (idx <= target.length() - 1) {

                for (int j = length; j >= 1; j--) {
                    if (idx + j > target.length()) {
                        continue;
                    }
                    String buffer = target.substring(idx, idx + j);

                    if (source.contains(buffer)) {
                        idx += j;

                        answer++;
                        break;
                    }
                }
            }

            System.out.print(answer);
         }
    }