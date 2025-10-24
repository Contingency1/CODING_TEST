import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.ArrayList;

public class Main {

    static int input;
    static List<Long> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        input = Integer.parseInt(br.readLine());

        if (input > 1022) {
            System.out.print(-1);
            return;
        }

        bfs();

        list.sort(null);
        
        System.out.print(list.get(input));
    }

    static void bfs() {

        Queue<Long> queue = new ArrayDeque<>();

        for (long i = 0; i <= 9; i++) {
            queue.add(i);
        }

        while (!queue.isEmpty()) {
            long cur = queue.poll();
            list.add(cur);

            for (long i = cur % 10 - 1; i >= 0; i--) {
                long newValue = cur * 10  + i;

                queue.add(newValue);
            }

        }
    }
    
}