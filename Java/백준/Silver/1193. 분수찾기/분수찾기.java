import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int goal = Integer.parseInt(br.readLine());
        int x = 1;
        int y = 1;
        int layer = 1;
        int index = 1;
        char word = 'y';

        if(goal == 1) {
            bw.write(String.valueOf(x) + "/" + String.valueOf(y));
            bw.flush();
            br.close();
            bw.close();

            return;
        }

        while (true) {
            index++;

            if (index - 1 == (layer * (layer + 1)) / 2)
            {
                layer++;
                if (word == 'y') {
                    word = 'x';
                    y++;
                } else {
                    word = 'y';
                    x++;
                }
            } else {
                if (word == 'y'){
                    x--;
                    y++;
                } else {
                    x++;
                    y--;
                }
            }


            if (index == goal) {
                bw.write(String.valueOf(x) + "/" + String.valueOf(y));
                break;
            }
        }

        bw.flush();
        br.close();
        bw.close();
    }
}