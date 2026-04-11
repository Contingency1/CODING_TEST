import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int length = s.length();
        List<String> words = new ArrayList<>();

        for (int i = 1; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                StringBuilder sb1 = new StringBuilder(s.substring(0, i));
                StringBuilder sb2 = new StringBuilder(s.substring(i, j));
                StringBuilder sb3 = new StringBuilder(s.substring(j));

                String word = sb1.reverse().toString() + 
                              sb2.reverse().toString() + 
                              sb3.reverse().toString();
                words.add(word);
            }
        }

        words.sort(null);
        System.out.println(words.get(0));
    }
}