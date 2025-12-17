    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;

    public class Main {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String source = br.readLine();
            String target = br.readLine();

            Char buffer = new Char(target);

            int diff = target.length() - source.length();

            for (int i = 0; i < diff; i++) {
                buffer.reverseCalculate();
            }

            System.out.print(buffer.check(source) ? 1 : 0);
        }

        static class Char{
            StringBuilder str;

            Char(String input) {
                str = new StringBuilder();
                str.append(input);
            }

            public void reverseCalculate() {
                if (this.str.charAt(this.str.length() - 1) == 'B') {
                    this.str.deleteCharAt(this.str.length() - 1);
                    this.str.reverse();
                    return;
                }

                this.str.deleteCharAt(this.str.length() - 1);
            }

            public boolean check(String input) {
                char[] strArr = this.str.toString().toCharArray();
                char[] inputArr = input.toCharArray();

                for (int i = 0; i < input.length(); i++) {
                    if(strArr[i] != inputArr[i]) {
                        return false;
                    }
                }

                return true;
            }
        }
    }