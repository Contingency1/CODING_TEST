    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.StringTokenizer;
    import java.util.Arrays;

    public class Main {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            final int count = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] arr = new int[count];

            for (int i = 0; i < count; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int answer = 0;

            if(check(arr)) {
                System.out.print(0);
                return;
            }

            while (true) {
                if (canDivide(arr)) {
                    for (int i = 0; i < arr.length; i++) {
                        arr[i] /= 2;
                    }
                    answer++;
                } else {
                    for (int i = 0; i < arr.length; i++) {
                        if(arr[i] > 0 && arr[i] % 2 != 0) {
                            arr[i]--;
                            break;
                        }
                    }
                    answer++;
                }

                if (check(arr)) {
                    break;
                }
            }

            System.out.print(answer);
        }

        static boolean check(int[] arr) {
            for (int i = 0; i < arr.length; i++) {
                if(arr[i] != 0) {
                    return false;
                }
            }

            return true;
        }

        static boolean canDivide(int[] arr) {
            for (int i = 0; i < arr.length; i++) {
                if(arr[i] % 2 != 0) {
                    return false;
                }
            }

            return true;
        }

    }