    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.StringTokenizer;
    import java.util.List;
    import java.util.ArrayList;

    public class Main {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());

            final int COUNT = Integer.parseInt(st.nextToken());
            int budget = Integer.parseInt(st.nextToken());

            List<Taste> list = new ArrayList<>();
            
            for (int i = 0; i < COUNT; i++) {
                st = new StringTokenizer(br.readLine());
                
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());

                list.add(new Taste(A, B));
            }

            list.sort(null);

            int answer = 0;

            for (int i = 0; i < COUNT; i++) {
                Taste buffer = list.get(i);

                int A = buffer.A;
                int B = buffer.B;

                if (budget == 5000 && i == COUNT - 1) {
                    if(A > B) {
                        answer += A;
                        budget -= 5000;
                        continue;
                    }

                    answer += B;
                    budget -= 1000;
                } else if (budget > 5000 && budget - 5000 >= (COUNT - (i + 1)) * 1000) {
                    if(A > B) {
                        answer += A;
                        budget -= 5000;
                        continue;
                    }

                    answer += B;
                    budget -= 1000;
                } else {
                    answer += B;
                    budget -= 1000;
                }
            }

            System.out.print(answer);
        }

        static class Taste implements Comparable<Taste> {
            int A;
            int B;

            Taste(int A, int B) {
                this.A = A;
                this.B = B;
            }

            @Override
            public int compareTo(Taste o) {
                int THIS = this.A - this.B;
                int OTHER = o.A - o.B;

                return OTHER - THIS;
            }
        }
    }
    