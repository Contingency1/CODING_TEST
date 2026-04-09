import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int[] numbers = new int[3];
        for (int i = 0; i < 3; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(numbers);
        
        StringBuilder sb = new StringBuilder();
        sb.append(numbers[0]).append(" ").append(numbers[1]).append(" ").append(numbers[2]);
        
        System.out.println(sb.toString());
    }
}