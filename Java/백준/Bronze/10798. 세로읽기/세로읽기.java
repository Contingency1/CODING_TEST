import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] base = br.lines().toArray(String[]::new);

        char[][] charArray = Arrays.stream(base)
                .map(String::toCharArray)
                .toArray(char[][]::new);

        int maxLength = 0;

        for (int i = 0; i < charArray.length; i++) {
            if(charArray[i].length > maxLength) {
                maxLength = charArray[i].length;
            }
        }

        char[][] answer = new char[maxLength][charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            for(int j = 0; j < charArray[i].length; j++) {
                answer[j][i] = charArray[i][j];
            }
        }

        for (int i = 0; i < answer.length; i++) {
            for(int j = 0; j < answer[i].length; j++) {
                if(answer[i][j] !='\u0000') {
                    bw.write(answer[i][j]);

                }
            }
        }

        bw.flush();
        br.close();
        bw.close();
    }
}