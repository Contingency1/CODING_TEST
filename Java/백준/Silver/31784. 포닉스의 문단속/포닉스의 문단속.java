    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.StringTokenizer;
    import java.util.List;
    import java.util.ArrayList;

    public class Main {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());

            final int LENGTH = Integer.parseInt(st.nextToken());
            int workCount = Integer.parseInt(st.nextToken());

            char[] input = br.readLine().toCharArray();

            List<Char> list = new ArrayList<>();

            for (char i: input) {
                list.add(new Char(i));
            }
            
            for (int i = 0; i < list.size(); i++) {
                if (workCount == 0) {
                    break;
                }

                Char cur = list.get(i);
                int curValue = cur.getValue();

                if (curValue == 1) {
                    continue;
                }

                int diff = cur.toA();

                if (diff <= workCount) {
                    workCount -= diff;
                    cur.add(diff);
                }
            }

            list.get(list.size() - 1).add(workCount);

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < list.size(); i++) {
                sb.append(list.get(i).toString());
            }

            System.out.print(sb);
        }

        static class Char {
            private int value;

            @Override
            public String toString() {
                return (char) (this.value + 64) + "";
            }

            Char (char value) {
                this.value = value - 64;
            }

            public void add(int add) {
                this.value += add;

                if (this.value > 26) {
                    this.value %= 26;
                }
            }

            public int toA() {
                return 27 - this.value;
            }

            public int getValue() {
                return this.value; 
            }
        }
    }