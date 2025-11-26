import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final int COUNT = Integer.parseInt(br.readLine());

        int[] input = new int[COUNT];

        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < COUNT; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(input);

        int l = 0;
        int r = input.length - 1;

        int answer = 0;

        while (l < r) {
            answer += 2 * input[r];
            
            l++;
            r--;
        }

        if (COUNT % 2 == 1) {
            answer += input[COUNT / 2];
        }

        System.out.print(answer);

    }
}