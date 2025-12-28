import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        final int FRIEND_COUNT = Integer.parseInt(st.nextToken());
        final int MATCH_COUNT = Integer.parseInt(st.nextToken());

        int[] arr = new int[FRIEND_COUNT];

        for (int i = 0; i < FRIEND_COUNT; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int[] diff = new int[FRIEND_COUNT - 1];

        for (int i = 0; i < FRIEND_COUNT - 1; i++) {
            diff[i] = arr[i + 1] - arr[i] - 1;
        }

        if (MATCH_COUNT == 1) {
            System.out.print(arr[FRIEND_COUNT - 1] - arr[0] + 1);
            return;
        } else if(MATCH_COUNT == FRIEND_COUNT) {
            System.out.print(FRIEND_COUNT);
            return;
        }

        Arrays.sort(diff);

        int answer = FRIEND_COUNT; 

        for (int i = 0; i < FRIEND_COUNT - MATCH_COUNT; i++) {
            answer += diff[i];
        }

        System.out.print(answer);
    }
}