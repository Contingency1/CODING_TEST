import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayDeque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int idx = 1;
        while (true) {
            String input = br.readLine();
            if (input.charAt(0) == '-') {
                break;
            }

            System.out.print(idx++ + ". ");

            ArrayDeque<Character> queue = new ArrayDeque<>();

            char[] chars = input.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (queue.isEmpty()) {
                    queue.add(chars[i]);
                    continue;
                }

                if (queue.peekLast() == '{' && chars[i] == '}') {
                    queue.pollLast();
                    continue;
                } else {
                    queue.add(chars[i]);
                }


            }
                
            boolean isOpened = false;

            int answer = 0;
            int queueSize = queue.size();

            for (int i = 0; i < queueSize; i++) {
                char ch = queue.pollFirst();

                if (!isOpened) {
                    if (ch != '{') {
                        answer++;
                    }
                    isOpened = true;
                } else {
                    if (ch != '}') {
                        answer++;
                    }
                    isOpened = false;
                }
            }

            System.out.println(answer);
        }



    }

}