import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int COUNT = Integer.parseInt(br.readLine());
        
        if (COUNT == 1) {
            System.out.print(0);
            return;
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int i = 0; i < COUNT; i++) {
            queue.add(Integer.parseInt(br.readLine()));
        }

        int answer = 0;

        for (int i = 0; i < COUNT - 1; i++) {
            int buffer = queue.poll() + queue.poll();

            answer += buffer;
            queue.add(buffer);
        }

        System.out.println(answer);
    }
}