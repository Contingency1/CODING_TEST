import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

    static int NODE_COUNT, STATUS_COUNT;

    static List<List<NodeJS>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        NODE_COUNT = Integer.parseInt(st.nextToken());
        STATUS_COUNT = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= NODE_COUNT; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i < NODE_COUNT; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(from).add(new NodeJS(to, cost));
            graph.get(to).add(new NodeJS(from, cost));
        }

        for (int i = 0; i < STATUS_COUNT; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            System.out.println(bfs(start, end));
            
        }
    }

    static int bfs(int start, int end) {
        Queue<Status> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[NODE_COUNT + 1];

        queue.add(new Status(start, 0));
        visited[start] = true;

        int answer = 0;
        while (!queue.isEmpty()) {
            Status polled = queue.poll();

            int cur = polled.cur;
            int acc = polled.acc;

            if (cur == end) {
                answer = acc;
            }

            for (NodeJS n: graph.get(cur)) {
                int to = n.to;

                if (visited[to]) {
                    continue;
                }

                int newAcc = acc + n.cost;
                
                queue.add(new Status(to, newAcc));
                visited[to] = true;
            }
        }

        return answer;
    }

    static class Status {
        int cur;
        int acc;

        Status(int cur, int acc) {
            this.cur = cur;
            this.acc = acc;
        }
    }

    static class NodeJS {
        int to;
        int cost;

        NodeJS(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
}