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

            final int COUNT = Integer.parseInt(br.readLine());

            StringTokenizer st;

            List<Convention> list = new ArrayList<>();

            for (int i = 0; i < COUNT; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());

                list.add(new Convention(start, end));
            }

            list.sort(null);

            PriorityQueue<Integer> pq = new PriorityQueue<>();

            pq.add(list.get(0).end);

            int answer = 1;

            for (int i = 1; i < list.size(); i++) {
                Convention cur = list.get(i);

                int start = cur.start;
                int end = cur.end;
                
                int last = pq.peek();

                if (last > start) {
                    pq.add(end);
                } else {
                    pq.poll();
                    pq.add(end);
                }

                answer = Math.max(pq.size(), answer);
            }

            System.out.print(answer);
         }

         static class Convention implements Comparable<Convention> {
            int start, end;

            @Override
            public int compareTo(Convention o) {
                return this.start - o.start;
            }

            Convention(int start, int end) {
                this.start = start;
                this.end = end;
            }
         }
    }