import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    static int WORD_COUNT, MUST_TEACH;
    static int[] words;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        WORD_COUNT = Integer.parseInt(st.nextToken());
        int postMustTeach = Integer.parseInt(st.nextToken());

        if (postMustTeach < 5) {
            System.out.print(0);
            return;
        }

        if (postMustTeach == 26) {
            System.out.print(WORD_COUNT);
            return;
        }

        Set<Character> offset = new HashSet<>();
        offset.add('a');
        offset.add('n');
        offset.add('t');
        offset.add('i');
        offset.add('c');

        MUST_TEACH = postMustTeach - 5;

        List<Set<Character>> list = new ArrayList<>();
        int answer = 0;

        for (int i = 0; i < WORD_COUNT; i++) {
            char[] chars = br.readLine().toCharArray();

            list.add(new HashSet<>());
            for (char c: chars) {
                if (!offset.contains(c)) {
                    list.get(i).add(c);
                }
            }

            if(list.get(i).size() == 0) {
                answer++;
            }
        }

        words = new int[WORD_COUNT];

        for (int i = 0; i < WORD_COUNT; i++) {
            int cur = 0;

            for (char c: list.get(i)) {
                cur += Math.pow(2, c - 'a');
            }

            words[i] = cur;
        }

        for (int i = 0; i < 26; i++) {
            int key = back((int) Math.pow(2, i), i + 1, 1);

            answer = Math.max(answer, key);
        }

        System.out.print(answer);
        // antic
    }

    static int back(int curValue, int start, int count) {
        int result = 0;

        if (count == MUST_TEACH) {
            for (int w: words) {
                if ((w | curValue) == curValue) {
                    result++;
                }
            }
        }
        
        if (count < MUST_TEACH) {
            for (int i = start; i < 26; i++) {
                 int buffer = (int) Math.pow(2, i);
     
                result = Math.max(result, back(curValue + buffer, i + 1, count + 1));
            }
        }

        return result;
    }

}