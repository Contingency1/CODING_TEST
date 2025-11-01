import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int MINE_COUNT;
    static int[] mine;
    static boolean[] isBombed;
    static List<Integer> answer = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        MINE_COUNT = Integer.parseInt(br.readLine());

        mine = new int[MINE_COUNT + 1];
        isBombed = new boolean[MINE_COUNT + 1];

        for (int i = 1; i <= MINE_COUNT; i++) {
            mine[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i < MINE_COUNT; i++) {
            if (isBombed[i]) {
                continue;
            }

            if (mine[i] >= mine[i + 1]) {
                bombing(i);
                answer.add(i);
            }
        }

        if (!isBombed[MINE_COUNT]) {
            answer.add(MINE_COUNT);
        }

        answer.sort(null);

        for (int a: answer) {
            System.out.println(a);
        }
    }

    static void bombing(int idx) {
        isBombed[idx] = true;

        if (idx - 1 >= 1 && mine[idx] > mine[idx - 1] && !isBombed[idx - 1]) {
            bombing(idx - 1);
        }

        if (idx + 1 <= MINE_COUNT && mine[idx] > mine[idx + 1] && !isBombed[idx + 1]) {
            bombing(idx + 1);
        }
    }


}