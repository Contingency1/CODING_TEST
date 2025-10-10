import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int ROW_COUNT, COL_COUNT;
    static int[][] input, target;
    static boolean[][] diff;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        init(br);

        if (isSame()) {
            System.out.print(0);
            return;
        }

        if (ROW_COUNT < 3 || COL_COUNT < 3) {
            System.out.print(-1);
            return;
        }

        int answer = 0;

        for (int i = 0; i < ROW_COUNT - 2; i++) {
            for (int j = 0; j < COL_COUNT - 2; j++) {
                if (diff[i][j]) {
                    invert(i, j);
                    answer++;
                }
            }
        }

        if (isSame()) {
            System.out.print(answer);
            return;
        }

        System.out.print(-1);
    }

    static void invert(int row, int col) {
        for (int i = row; i < row + 3; i++) {
            for (int j = col; j < col + 3; j++) {
                diff[i][j] = !diff[i][j];
            }
        }
    }

    static boolean isSame() {
        for (int i = 0; i < ROW_COUNT; i++) {
            for (int j = 0; j < COL_COUNT; j++) {
                if (diff[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        ROW_COUNT = Integer.parseInt(st.nextToken());
        COL_COUNT = Integer.parseInt(st.nextToken());

        input = new int[ROW_COUNT][COL_COUNT];
        target = new int[ROW_COUNT][COL_COUNT];
        diff = new boolean[ROW_COUNT][COL_COUNT];

        for (int i = 0; i < ROW_COUNT; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < COL_COUNT; j++) {
                input[i][j] = chars[j] - '0';
            }
        }

        for (int i = 0; i < ROW_COUNT; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < COL_COUNT; j++) {
                target[i][j] = chars[j] - '0';
            }
        }

        for (int i = 0; i < ROW_COUNT; i++) {
            for (int j = 0; j < COL_COUNT; j++) {
                if (input[i][j] != target[i][j]) {
                    diff[i][j] = true;
                }
            }
        }
    }

}