import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int count = Integer.parseInt(br.readLine());
        int[] parent = new int[count];

        StringTokenizer st = new StringTokenizer(br.readLine());

        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(new ArrayList<>());
        }

        int root = 0;
        
        for (int i = 0; i < count; i++) {
            int cur = Integer.parseInt(st.nextToken());
            
            if (cur == -1) {
                root = i;
                continue;
            }

            parent[i] = cur; 
            list.get(parent[i]).add(i);
        }

        int delete = Integer.parseInt(br.readLine());
        if (delete == root) {
            System.out.print(0);
            return;
        }

        for (int i = 0; i < list.get(parent[delete]).size(); i++) {
            if(list.get(parent[delete]).get(i) == delete) {
                list.get(parent[delete]).remove(i);
            }
        }

        if (list.get(root).size() == 0) {
            System.out.print(1);
            return;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(root);

        int answer = 0;

        while (!queue.isEmpty()) {
            int polled = queue.poll();

            if (list.get(polled).size() != 0) {
                for (int l: list.get(polled)) {
                    queue.add(l);
                }
            } else {
                answer++;
            }
        }

        System.out.print(answer);
    }
}