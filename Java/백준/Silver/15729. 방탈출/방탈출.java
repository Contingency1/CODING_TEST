import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int BUTTON_COUNT;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        BUTTON_COUNT = Integer.parseInt(br.readLine());

        boolean[] button = new boolean[BUTTON_COUNT];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < BUTTON_COUNT; i++) {
            button[i] = Integer.parseInt(st.nextToken()) == 1;
        }

        int answer = 0;

        if (BUTTON_COUNT >= 3) {
            for (int i = 0; i < BUTTON_COUNT - 2; i++) {
                if (button[i]) {
                    for (int j = i; j < i + 3; j++) {
                        button[j] = !button[j];
                    }
                    answer++;
                }
            }
        }

        if (BUTTON_COUNT - 2 >= 0 && button[BUTTON_COUNT - 2]) {
            button[BUTTON_COUNT - 2] = false;
            button[BUTTON_COUNT - 1] = !button[BUTTON_COUNT - 1];
            answer++;
        }

        if (button[BUTTON_COUNT - 1]) {
            answer++;
        }

        System.out.print(answer);
    }
}