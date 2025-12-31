import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] chars = br.readLine().toCharArray();

        ArrayDeque<Character> queue = new ArrayDeque<>();

        for (int i = 0; i < chars.length; i++) {
            StringBuilder sb = new StringBuilder();

            for (char c: queue) {
                sb.append(c);
            }

            String s1 = sb.toString() + chars[i];
            String s2 = chars[i] + sb.toString();

            if (s1.compareTo(s2) <= 0) {
                queue.addLast(chars[i]);
            } else {
                queue.addFirst(chars[i]);
            }
        }

        StringBuilder sb = new StringBuilder();

        while (!queue.isEmpty()) {
            sb.append(queue.poll());
        }

        System.out.print(sb);
    }
}