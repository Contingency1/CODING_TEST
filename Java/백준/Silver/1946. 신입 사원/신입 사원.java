import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final int TEST_CASE = Integer.parseInt(br.readLine());

        for (int i = 0; i < TEST_CASE; i++) {
            int people = Integer.parseInt(br.readLine());
            List<Wow> list = new ArrayList<>();

            for (int j = 0; j < people; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                list.add(new Wow(x, y));
            }
            list.sort(null);

            int pass = 1;
            int min = list.get(0).y;

            for (int j = 1; j < people; j++) {
                Wow wow = list.get(j);

                int y = wow.y;

                if (y < min) {
                    pass++;
                    min = y;
                }
            }

            System.out.println(pass);
        }

    }

    static class Wow implements Comparable<Wow> {
        int x;
        int y;

        @Override
        public int compareTo(Wow o) {
            return this.x - o.x;
        }

        Wow (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}