import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {

    static int COUNT, POWER;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        init(br);

        if (COUNT < 2) {
            System.out.print(0);
            return;
        }

        int[] input = new int[COUNT];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < COUNT; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(input);

        int start = 0;
        int end = input.length - 1;

        int answer = 0;
        while (start < end) {
            int s = input[start];
            int e = input[end];

            int buffer = s + e;

            if (buffer >= POWER) {
                answer++;
            } else {
                start++;
                while (start < end) {
                    buffer += input[start];

                    if(buffer >= POWER) {
                        answer++;
                        break;
                    }
                    start++;
                }
            }
        
            start++;
            end--;
        }

        System.out.print(answer);

    }

    static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        COUNT = Integer.parseInt(st.nextToken());
        POWER = Integer.parseInt(st.nextToken());
    }
}