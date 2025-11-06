import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        final int COUNT = Integer.parseInt(st.nextToken());
        final int ACTIVE_COUNT = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < COUNT; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        list.sort(null);
        
        long curActiveCount = 1;

        BigInteger answer = new BigInteger("0");

        for (int i = 1; i < list.size(); i++) {
            long cur = list.get(i);

            answer = answer.add(BigInteger.valueOf(cur * curActiveCount));

            if (curActiveCount < ACTIVE_COUNT) {
                curActiveCount++;
            }
        }

        System.out.print(answer);
    }

}