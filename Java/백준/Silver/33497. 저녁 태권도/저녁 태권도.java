    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.StringTokenizer;
    import java.util.Arrays;

    public class Main {
        static int STUDENT_COUNT, DAYS;

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());

            STUDENT_COUNT = Integer.parseInt(st.nextToken());
            DAYS = Integer.parseInt(st.nextToken());

            // -1: -, 0: X, 1: +

            int[][] days = new int[DAYS + 1][3];

            int restCount = 0;

            for (int i = 1; i <= DAYS; i++) {
                st = new StringTokenizer(br.readLine());

                days[i][0] = Integer.parseInt(st.nextToken());
                days[i][1] = Integer.parseInt(st.nextToken());
                days[i][2] = days[i][0] + days[i][1];

                if (days[i][2] > STUDENT_COUNT) {
                    System.out.print("NO");
                    return;
                }

                restCount += STUDENT_COUNT - days[i][2];
            }

            if (restCount < STUDENT_COUNT) {
                System.out.print("NO");
                return;
            } else if (restCount > STUDENT_COUNT) {
                restCount = STUDENT_COUNT;
            }

            int[][] student = new int[DAYS + 1][STUDENT_COUNT + 1];
            int restIdx = 1;

            for (int i = 1; i < DAYS + 1; i++) {
                Arrays.fill(student[i], 1);
            }

            for (int i = 1; i <= DAYS; i++) {
                int morning = days[i][0];
                int dinner = days[i][1];
                int sum = days[i][2];

                for (int j = 1; j < restIdx; j++) {
                    if (morning > 0) {
                        morning--;
                        student[i][j] = 1;
                        continue;
                    }

                    if (dinner > 0) {
                        dinner--;
                        student[i][j] = -1;
                    }
                }

                if (restCount > 0 && sum < STUDENT_COUNT) {
                    int canRest = STUDENT_COUNT - sum;

                    if (canRest > restCount) {
                        canRest = restCount;
                    }

                    for (int j = 0; j < canRest; j++) {
                        student[i][restIdx++] = 0;
                        restCount--;
                    }
                }

                for (int j = restIdx; j < student[0].length; j++) {
                    if (morning > 0) {
                        morning--;
                        student[i][j] = 1;
                        continue;
                    }

                    if (dinner > 0) {
                        dinner--;
                        student[i][j] = -1;
                    }
                }
            }

            StringBuilder sb = new StringBuilder();

            for (int j = 1; j < STUDENT_COUNT + 1; j++) {
                for (int i = 1; i < DAYS + 1; i++) {
                    if (student[i][j] == 0) {
                        sb.append("X");
                    } else if (student[i][j] == 1) {
                        sb.append("+");
                    } else {
                        sb.append("-");
                    }
                }
                sb.append("\n");
            }

            sb.deleteCharAt(sb.length() - 1);

            System.out.println("YES");
            System.out.print(sb);
        }
    }