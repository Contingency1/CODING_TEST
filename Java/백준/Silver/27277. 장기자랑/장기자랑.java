import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int COUNT = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] input = new int[COUNT];
        
        for (int i = 0; i < COUNT; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(input);

        int start = 0;
        int end = COUNT - 1;

        int answer = 0;

        while (start < end) {
            answer += input[end] - input[start];
 
            start++;
            end--;
        }

        answer += input[end];

        System.out.print(answer);
    }
}