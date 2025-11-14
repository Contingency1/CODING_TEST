import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int PIECE_COUNT;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        PIECE_COUNT = Integer.parseInt(br.readLine());

        int[] length = new int[PIECE_COUNT];
        int[] time = new int[PIECE_COUNT];

            
        StringTokenizer st = new StringTokenizer(br.readLine());
        length[0] = Integer.parseInt(st.nextToken());
        time[0] = Integer.parseInt(st.nextToken());


        for (int i = 1; i < PIECE_COUNT; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            length[i] = length[i - 1] + l;
            time[i] = time[i - 1] + t;
        }

        int answer = time[0];

        for (int i = 1; i < PIECE_COUNT; i++) {
            answer = Math.max(answer, time[i] - length[i - 1]);
        }

        System.out.print(answer);

    }
}