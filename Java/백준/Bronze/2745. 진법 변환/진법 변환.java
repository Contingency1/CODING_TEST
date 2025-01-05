import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
    private static boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] area = br.readLine().split(" ");

        int[] number = Arrays
            .stream(area[0].split(""))
            .mapToInt(x -> isNumber(x) ? Integer.parseInt(x) : x.charAt(0) - 55)
            .toArray();
        
        int formation = Integer.parseInt(area[1]);
        int answer = 0;

        // ASCII A = 65 - 55(중요) = 10
        // 수 * 진수 ** (배열 길이 - 1- 현재 인덱스) = 10진수
        for (int i = 0; i < number.length; i++) {
            answer += number[i] * Math.pow(formation, (number.length - 1 - i));
        }

        bw.write(String.valueOf(answer));

        bw.flush();
        br.close();
        bw.close();
    }
}