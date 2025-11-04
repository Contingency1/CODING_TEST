import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

public class Main {

    static int BREAD_COUNT, CUT_COUNT;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        BREAD_COUNT = Integer.parseInt(st.nextToken());
        CUT_COUNT = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < BREAD_COUNT; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        int answer = 0;

        for (int i = 0; i < list.size(); i++) {
            int curValue = list.get(i);

            if (curValue == 10) {
                list.remove(i--);
                answer++;
            } else if (curValue < 10) {
                list.remove(i--);
            }
        }

        List<Integer> buffer = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            int curValue = list.get(i);

            if (curValue % 10 == 0) {
                buffer.add(curValue);
                list.remove(i--);
            } 
        }

        int cut = 0;

        buffer.sort(null);

        for (int i = 0; i < buffer.size(); i++) {
            int curValue = buffer.get(i);

            if (cut == CUT_COUNT) {
                break;
            }

            if (curValue == 10) {
                answer++;
                buffer.remove(i--);
                continue;
            }

            if (curValue - 10 >= 10) {
                buffer.set(i--, curValue - 10);
            } else {
                buffer.remove(i--);
            }

            answer++;
            cut++;
        }

        for (int l: buffer) {
            if (l == 10) {
                answer++;
            }
        }

        for (int i = 0; i < list.size(); i++) {
            int curValue = list.get(i);
            
            if (cut == CUT_COUNT) {
                break;
            }

            if (curValue == 10) {
                answer++;
                list.remove(i--);
                continue;
            }

            if (curValue - 10 >= 10) {
                list.set(i--, curValue - 10);
            } else {
                list.remove(i--);
            }

            answer++;
            cut++;
        }

        for (int l: list) {
            if (l == 10) {
                answer++;
            }
        }

        System.out.print(answer);
    }
}