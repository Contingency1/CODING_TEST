import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        List<String> max = new ArrayList<>();
        List<String> min = new ArrayList<>();

        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (char cur: input.toCharArray()) {
            if (cur == 'K') {
                sb1.append(cur);
                sb1.reverse();
                max.add(sb1.substring(0, sb1.length()));

                sb1 = new StringBuilder();

                if (sb2.length() != 0) {
                    min.add(sb2.substring(0, sb2.length()));
                }

                min.add("K");
                sb2 = new StringBuilder();
            } else {
                sb1.append(cur);
                sb2.append(cur);
            }
        }

        if (sb1.length() != 0) {
            for (int i = 0; i < sb1.length(); i++) {
                max.add("M");
            }
        }

        if (sb2.length() != 0) {
            min.add(sb2.substring(0, sb2.length()));
        }

        StringBuilder answer = new StringBuilder();

        makeString(answer, max);
        answer.append("\n");
        makeString(answer, min);
        
        System.out.print(answer);
    }


    static void makeString(StringBuilder answer, List<String> buffer) {
        for (String m: buffer) {
            char[] ch = m.toCharArray();

            if (ch[0] == 'K') {
                answer.append(5);
            } else {
                answer.append(1);
            }

            for (int i = 1; i < ch.length; i++) {
                answer.append(0);
            }
        }
    }
}