import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;

public class Main {
    static int DAY, TARGET_SUM, BEER_KIND;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        DAY = Integer.parseInt(st.nextToken());
        TARGET_SUM = Integer.parseInt(st.nextToken());
        BEER_KIND = Integer.parseInt(st.nextToken());

        List<Beer> list = new ArrayList<>();

        for (int i = 0; i < BEER_KIND; i++) {
            st = new StringTokenizer(br.readLine());

            list.add(new Beer(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        list.sort(null);

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        long sumLike = 0;

        for (Beer b: list) {
            sumLike += b.like;

            pq.add(b.like);

            if (pq.size() > DAY) {
                int m = pq.poll();
                sumLike -= m;
            }
            
            if (pq.size() == DAY && sumLike >= TARGET_SUM) {
                System.out.print(b.level);
                return;
            }
        }
        System.out.print(-1);
    }

    static class Beer implements Comparable<Beer>{
        int like;
        int level;

        @Override
        public int compareTo(Beer o) {
            return this.level - o.level;
        }

        Beer(int like, int level) {
            this.like = like;
            this.level = level;
        }
    }
}