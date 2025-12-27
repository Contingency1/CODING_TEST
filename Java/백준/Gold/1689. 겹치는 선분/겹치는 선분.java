    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.StringTokenizer;
    import java.util.List;
    import java.util.PriorityQueue;
    import java.util.ArrayList;

    // AI 힌트를 통하여 풀었음.
    // 이렇게도 생각할수 있구나.....
    public class Main {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
            final int COUNT = Integer.parseInt(br.readLine());

            List<Line> list = new ArrayList<>();

            for (int i = 0; i < COUNT; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                int one = Integer.parseInt(st.nextToken());
                int two = Integer.parseInt(st.nextToken());
                
                list.add(new Line(one, two));
            }

            list.sort(null);

            PriorityQueue<Integer> queue = new PriorityQueue<>();

            int answer = 0;

            for (Line cur : list) {
                while (!queue.isEmpty() && queue.peek() <= cur.start) {
                    queue.poll();
                }
    
                queue.offer(cur.end);
                answer = Math.max(answer, queue.size());
            }
            
            System.out.print(answer);
        }

        static class Line implements Comparable<Line> {
            int start;
            int end;

            @Override
            public int compareTo(Line o) {
                return this.start - o.start;
            }

            Line(int start, int end) {
                this.start = start;
                this.end = end;
            }
        }
    }
    