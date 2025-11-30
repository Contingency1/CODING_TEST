import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// AI 도움받음
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String s = br.readLine();
        int current = 0; 
        int answer = 0; 

        for (int i = 0; i < N; i++) {
            char c = s.charAt(i);

            if (c == '(') {
                current++; 
            } else {
                current--;
            }

            answer = Math.max(answer, Math.abs(current));
        }

        if (current != 0) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }
}