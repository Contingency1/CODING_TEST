    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.StringTokenizer;
    import java.util.Arrays;
    import java.util.List;
    import java.util.ArrayList;

    public class Main {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());

            final int ARRAY_COUNT = Integer.parseInt(st.nextToken());
            final int NUMBER = Integer.parseInt(st.nextToken());

            boolean[] used = new boolean[ARRAY_COUNT + 1];

            st = new StringTokenizer(br.readLine());

            List<Integer> list = new ArrayList<>();

            for (int i = 1; i < ARRAY_COUNT + 1; i++) {
                int cur = Integer.parseInt(st.nextToken());

                if (cur > ARRAY_COUNT) {
                    System.out.print(0);
                    return;
                }

                if (!used[cur]) {
                    used[cur] = true;
                } else {
                    list.add(cur);
                }
            }

            list.sort(null);

            for (int i = 1; i < ARRAY_COUNT + 1; i++) {
                if (used[i]) {
                    continue;
                }

                int repeat = list.size();

                for (int j = 0; j < repeat; j++) {
                    int cur = list.get(j);

                    if (cur > i) {
                        System.out.print(0);
                        return;
                    }

                    int diff = i - cur;

                    if (diff % NUMBER != 0) {
                        continue;
                    }

                    used[i] = true;
                    list.remove(j);
                    break;
                }
            }

            for (int i = 1; i < ARRAY_COUNT + 1; i++) {
                if (!used[i]) {
                    System.out.print(0);
                    return;
                }
            }

            System.out.print(1);
        }
    }