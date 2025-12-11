    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.StringTokenizer;

    public class Main {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());

            final int STR_LENGTH = Integer.parseInt(st.nextToken());
            final int VALUE = Integer.parseInt(st.nextToken());

            int min = STR_LENGTH;
            int max = STR_LENGTH * 26;
            
            if (VALUE < min || VALUE > max) {
                System.out.print("!");
                return;
            }

            int left = VALUE - STR_LENGTH;

            int aCount = STR_LENGTH;
            int zCount = left / 25;

            aCount -= zCount;

            int leftNumber = left % 25;

            int mid = 0;

            if (leftNumber > 0) {
                mid = 1 + leftNumber;
                aCount--;
            }

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < aCount; i++) {
                sb.append("A");
            }

            if (mid != 0) {
                sb.append(String.format("%c", 64 + mid));
            }

            for (int i = 0; i < zCount; i++) {
                sb.append("Z");
            }

            System.out.print(sb);
        }
    }