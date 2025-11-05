import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;

public class Main {

    static int WORD_COUNT;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        WORD_COUNT = Integer.parseInt(br.readLine());

        List<Str> input = new ArrayList<>();

        for (int i = 0; i < WORD_COUNT; i++) {
            String cur = br.readLine();
            input.add(new Str(cur, cur.length()));
        }

        input.sort(null);

        int answer = 0;

        for (int i = 0; i < input.size(); i++) {
            char[] cur = input.get(i).str.toCharArray();

            boolean flag = false;

            
            for (int j = i + 1; j < input.size(); j++) {
                char[] compare = input.get(j).str.toCharArray();

                if (compare(cur, compare)) {
                    flag = true;
                    break;
                }
            }

            if (flag) {
                continue;
            }

            answer++;
        }

        System.out.print(answer);
    }

    static boolean compare(char[] one, char[] two) {
        for (int i = 0; i < one.length; i++) {
            if (one[i] != two[i]) {
                return false;
            }
        }

        return true;
    }

    static class Str implements Comparable<Str>{
        String str;
        int length;

        // @Override
        // public String toString() {
        //     return this.str + " " + this.length;
        // }

        @Override
        public int compareTo(Str o) {
            if (this.length == o.length) {
                return this.str.compareTo(o.str);
            }

            return this.length - o.length;
        }

        Str (String str, int length) {
            this.str = str;
            this.length = length;
        }
    }
}