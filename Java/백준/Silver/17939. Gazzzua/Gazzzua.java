import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] count = new int[10_001];
        int[] arr = new int[N];

        List<Integer> list = new ArrayList<>();
        
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            count[arr[i]]++;

            list.add(arr[i]);
        }

        list.sort(Comparator.reverseOrder());

        int answer = 0;
        int lastIdx = 0;

        for (int i = 0; i < arr.length; i++) {
            while (true) {
                if (count[list.get(0)] == 0) {
                    list.remove(0);
                } else {
                    break;
                }
            }

            int max = list.get(0);

            if (arr[i] == max) {
                count[arr[i]]--;
                
                for (int j = lastIdx; j < i; j++) {
                    count[arr[j]]--;
    
                    if(max > arr[j]) {
                        answer += max - arr[j];
                    }
                }
                
                lastIdx = i + 1;
                list.remove(0);
            }
        }

        System.out.print(answer);
    }
}