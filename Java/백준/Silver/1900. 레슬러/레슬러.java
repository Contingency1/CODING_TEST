    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.StringTokenizer;
    import java.util.List;
    import java.util.ArrayList;

    public class Main {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            final int N = Integer.parseInt(br.readLine());

            List<Info> list = new ArrayList<>();
            list.add(new Info(0, -1, -1));

            StringTokenizer st;
            
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());

                list.add(new Info(
                    i,
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())));
            }

            list.sort(null);

            for (int i = 0; i < list.size() - 1; i++) {
                Info info = list.get(i);

                System.out.println(info.idx);
            }
        }

        static class Info implements Comparable<Info> {
            int idx;
            int strength;
            int ring;

            public String toString() {
                return idx + " " + strength + " " + ring;
            }

            @Override
            public int compareTo(Info o) {
                int thisPower = this.strength + this.ring * o.strength;
                int otherPower = o.strength + o.ring * this.strength;

                if (thisPower > otherPower) {
                    return -1;
                }

                return 1;
            }

            Info(int idx, int strength, int ring) {
                this.idx = idx;
                this.strength = strength;
                this.ring = ring;
            }
        }
    }