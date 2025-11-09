import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.PriorityQueue;

public class Main {

    static int CARD_COUNT, UNION_COUNT;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        CARD_COUNT = Integer.parseInt(st.nextToken());
        UNION_COUNT = Integer.parseInt(st.nextToken());

        Queue<Long> queue = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < CARD_COUNT; i++) {
            queue.add(Long.parseLong(st.nextToken()));
        }



        for (int i = 0; i < UNION_COUNT; i++) {
            long polled1 = queue.poll();
            long polled2 = queue.poll();

            long sum = polled1 + polled2;
            queue.add(sum);
            queue.add(sum);
        }

        long answer = 0;
        while (!queue.isEmpty()) {
            answer += queue.poll();
        }

        System.out.print(answer);
    }

}