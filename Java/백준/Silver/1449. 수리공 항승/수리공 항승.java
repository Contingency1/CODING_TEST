import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

public class Main {

    static int TARGET_COUNT, TAPE_LENGTH;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        TARGET_COUNT = Integer.parseInt(st.nextToken());
        TAPE_LENGTH = Integer.parseInt(st.nextToken());

        List<Integer> list = new ArrayList<>();

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < TARGET_COUNT; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        list.sort(null);

        double r = list.get(0) - 0.5 + TAPE_LENGTH;

        int answer = 1;

        for (int i = 1; i < TARGET_COUNT; i++) {
            int cur = list.get(i);

            double newR = cur + 0.5;

            if (newR > r) {
                answer++;
                r = list.get(i) - 0.5 + TAPE_LENGTH;
            }
        }

        System.out.print(answer);


    }

}