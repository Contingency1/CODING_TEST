import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

public class Main {

    static int WORD_COUNT, TARGET_LENGTH;
    static int CUR_LENGTH = 0;
    static List<String> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        init(br);

        int diff = TARGET_LENGTH - CUR_LENGTH;
        int repeatCount = diff / (WORD_COUNT - 1);
        int leftCount = diff % (WORD_COUNT - 1);

        for (int i = 0; i < repeatCount; i++) {
            for (int j = 1; j < list.size(); j += 2) {
                String cur = list.get(j);
                list.set(j, cur + "_");
            }
        }

        CUR_LENGTH += repeatCount * (WORD_COUNT - 1);

        for (int i = 1; i < list.size() && leftCount > 0; i += 2) {
            char nextWordStart = list.get(i + 1).charAt(0);
            
            if (nextWordStart >= 'a' && nextWordStart <= 'z') {
                String cur = list.get(i);
                list.set(i, cur + "_");
                leftCount--; 
                CUR_LENGTH++; 
            }
        }

        for (int i = list.size() - 2; i >= 1 && leftCount > 0; i -= 2) {
            char nextWordStart = list.get(i + 1).charAt(0);
            
            if (nextWordStart >= 'A' && nextWordStart <= 'Z') {
                String cur = list.get(i);
                list.set(i, cur + "_");
                leftCount--;
                CUR_LENGTH++;
            }
        }

        StringBuilder sb = new StringBuilder();

        for (String o: list) {
            sb.append(o);
        }

        System.out.print(sb);
    }

    static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        WORD_COUNT = Integer.parseInt(st.nextToken());
        TARGET_LENGTH = Integer.parseInt(st.nextToken());

        for (int i = 0; i < WORD_COUNT - 1; i++) {
            String cur = br.readLine();
            list.add(cur);
            list.add("_");
            CUR_LENGTH += cur.length() + 1;
        }

        String cur = br.readLine();

        list.add(cur);
        CUR_LENGTH += cur.length();
    }

}