    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.StringTokenizer;
    import java.util.List;
    import java.util.ArrayList;
    import java.util.PriorityQueue;

    public class Main {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            StringTokenizer st = new StringTokenizer(br.readLine());

            final int DEVICE_COUNT = Integer.parseInt(st.nextToken());
            final int CHARGER_COUNT = Integer.parseInt(st.nextToken());

            List<Integer> list = new ArrayList<>();

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < DEVICE_COUNT; i++) {
                list.add(Integer.parseInt(st.nextToken()));
            }

            list.sort(null);

            PriorityQueue<Integer> pq = new PriorityQueue<>();

            for (int i = 0; i < CHARGER_COUNT; i++) {
                pq.add(0);
            }

            for (int i = list.size() - 1; i >= 0; i--) {
                int cur = list.get(i);
                int prev = pq.poll();

                pq.add(prev + cur);
            }

            int answer = 0;

            while (!pq.isEmpty()) {
                answer = pq.poll();
            }

            System.out.print(answer);
         }
    }