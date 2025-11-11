import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

    static int COUNT;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        COUNT = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int answer = 0;

        int[] arrow = new int[1000001];

        for (int i = 0; i < COUNT; i++) {
            int h = Integer.parseInt(st.nextToken());

            if (arrow[h] > 0) {
                arrow[h]--;
            } else {
                answer++;
            }

            arrow[h - 1]++;
        }

        System.out.print(answer);
    }
}