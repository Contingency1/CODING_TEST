import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Main {

    static List<Character> list = new ArrayList<>();
    static Map<Character, Char> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // q -> u -> a -> c -> k
        char[] input = br.readLine().toCharArray();

        init();

        for (char i: input) {
            boolean flag = false;

            Char curCharacter = map.get(i);

            for (int j = 0; j < list.size(); j++) {
                char cur = list.get(j);

                if (cur == curCharacter.prev.character) {
                    list.set(j, curCharacter.character);
                    flag = true;
                    break;
                }
            }

            if(flag) {
                continue;
            }

            if (curCharacter.character != 'q') {
                System.out.print(-1);
                return;
            } else {
                list.add('q');
            }
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != 'k') {
                System.out.print(-1);
                return;
            }
        }

        System.out.print(list.size());
    }

    static Char init() {
        Char q = new Char('q', null);
        Char u = new Char('u', q);
        Char a = new Char('a', u);
        Char c = new Char('c', a);
        Char k = new Char('k', c);

        q.prev = k;

        map.put('q', q);
        map.put('u', u);
        map.put('a', a);
        map.put('c', c);
        map.put('k', k);

        return q;
    }

    static class Char {
        char character;
        Char prev;
    
        Char(char character, Char o) {
            this.character = character;
            this.prev = o;
        }
    }

}