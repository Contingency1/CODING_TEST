import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());


        int curPulse = m;
        int answer = 0;
        int time = 0;

        if (m + T > M) {
            System.out.print(-1);
            return;
        }

        while (answer != N) {
            if (curPulse + T <= M) {
                curPulse += T;
                answer++;
            } else {
                if (curPulse - R < m) {
                    curPulse = m;
                } else {
                    curPulse -= R;
                }
            }
            time++;
        }

        System.out.print(time);
    }
}