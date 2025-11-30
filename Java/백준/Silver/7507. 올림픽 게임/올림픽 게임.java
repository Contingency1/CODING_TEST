import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int COUNT;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        COUNT = Integer.parseInt(br.readLine());

        for (int i = 1; i <= COUNT; i++) {
            System.out.println("Scenario #" + i + ":");
            int gameCount = Integer.parseInt(br.readLine());

            List<Game> list = new ArrayList<>();

            for (int j = 0; j < gameCount; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                int day = Integer.parseInt(st.nextToken());
                int start = stringToTime(st.nextToken());
                int end = stringToTime(st.nextToken());

                list.add(new Game(day, start, end));
            }

            list.sort(null);

            int answer = 0;
            int curDay = 0;
            int lastEnd = 0;

            for (Game l: list) {
                int day = l.day;
                int start = l.start;
                int end = l.end;

                if (curDay != day) {
                    curDay = day;
                    lastEnd = end;
                    answer++;
                    continue;
                }

                if (start >= lastEnd) {
                    lastEnd = end;
                    answer++;
                    continue;
                }

            }

            System.out.println(answer);
            System.out.println();
        }
    }

    static int stringToTime(String str) {
        int hh = Integer.parseInt(str.substring(0, 2));
        int mm = Integer.parseInt(str.substring(2, 4));

        return hh * 60 + mm;
    }

    static class Game implements Comparable<Game>{
        int day;
        int start;
        int end;

        @Override
        public int compareTo(Game o) {
            if (this.day == o.day) {
                if (this.end == o.end) {
                    return this.start - o.start;
                }

                return this.end - o.end;
            }

            return this.day - o.day;
        }

        Game (int day, int start, int end) {
            this.day = day;
            this.start = start;
            this.end = end;
        }
    }
}