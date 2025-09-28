import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final int COUNT = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] input = new int[COUNT];

        for (int i = 0; i < COUNT; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        long answer = 0;
        int start = 0;

        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < COUNT; i++) {
            while (set.contains(input[i])) {
                set.remove(input[start]);
                start++;
            }

            set.add(input[i]);

            answer += (i - start + 1);
        }

        System.out.print(answer);
    }

}