import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;


public class Main {

    static int CRANE_COUNT, BOX_COUNT;
    static List<Integer> craneWeightLimit, boxWeight;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        CRANE_COUNT = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        craneWeightLimit = new ArrayList<>();

        for (int i = 0; i < CRANE_COUNT; i++) {
            craneWeightLimit.add(Integer.parseInt(st.nextToken()));
        }

        BOX_COUNT = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        boxWeight = new ArrayList<>();

        for (int i = 0; i < BOX_COUNT; i++) {
            boxWeight.add(Integer.parseInt(st.nextToken()));
        }

        boxWeight.sort(Collections.reverseOrder());
        craneWeightLimit.sort(Collections.reverseOrder());

        if (craneWeightLimit.get(0) < boxWeight.get(0)) {
            System.out.print(-1);
            return;
        }

        int answer = 0;
        while (!boxWeight.isEmpty()) {
            answer++;
            int craneIdx = 0;
            int boxIdx = 0;

            while (craneIdx < CRANE_COUNT) {
                if (boxIdx >= boxWeight.size()) {
                    break; 
                }
                
                if (craneWeightLimit.get(craneIdx) >= boxWeight.get(boxIdx)) {
                    boxWeight.remove(boxIdx);
                    craneIdx++;
                } else {
                    boxIdx++; 
                }            
            }
        }
        
        System.out.print(answer);
    }
}