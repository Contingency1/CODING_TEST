    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.StringTokenizer;
    import java.util.Arrays;

    public class Main {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            final int COUNT = Integer.parseInt(br.readLine());
            int[] input = new int[COUNT];

            StringTokenizer st = new StringTokenizer(br.readLine());
            
            for (int i = 0; i < COUNT; i++) {
                input[i] = Integer.parseInt(st.nextToken());
            }

            int prev = input[0];
            int buffer = 0;
            
            for (int i = 1; i < COUNT; i++) {
                if (input[i] < prev) {
                    buffer++;
                    if(buffer == 4) {
                        System.out.print("NO");
                        return;
                    }
                } else {
                    buffer = 0;
                }
            }

            System.out.println("YES");
         }
    }