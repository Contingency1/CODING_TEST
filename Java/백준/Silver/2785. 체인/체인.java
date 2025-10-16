import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Main {

    static int CHAIN_COUNT;
    static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        init(br);

        if (list.size() == 2) {
            System.out.print(1);
            return;
        }

        list.sort(null);

        int answer = 0;

        while (true) {
            if(answer >= list.size() - 1) {
                break;
            }

            if (list.get(0) > 1) {
                list.set(0, list.get(0) - 1);
                answer++;
            } else if (list.get(0) == 1) {
                list.remove(0);
                answer++;
            }

        }

        System.out.print(answer);
    }

    static void init(BufferedReader br) throws IOException {
        CHAIN_COUNT = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        list = new ArrayList<>();

        for (int i = 0; i < CHAIN_COUNT; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

    }

}