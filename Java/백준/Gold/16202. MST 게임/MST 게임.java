import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.LinkedList;

public class Main {
    static int NODE_COUNT, EDGE_COUNT, TURN_COUNT;
    static List<Edge> edge = new LinkedList<>();
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        init(br, st);

        int[] answer = new int[TURN_COUNT];

        for (int i = 0; i < TURN_COUNT; i++) {
            int result = 0;

            if (edge.size() < NODE_COUNT - 1) {
                break;
            }

            for (Edge e: edge) {
                int from = e.from;
                int to = e.to;
                int cost = e.cost;

                if (find(from) == find(to)) {
                    continue;
                }

                union(from, to);
                result += cost;
            }


            if (isDiffParent()) {
                break;
            }

            edge.remove(0);
            initParent();
            answer[i] = result;
        }

        StringBuilder sb = new StringBuilder();
        for (int a: answer) {
            sb.append(a).append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.print(sb);
    }

    static void union(int x, int y) {
        int parentX = find(x);
        int parentY = find(y);

        if (parentX != parentY) {
            parent[parentX] = parent[parentY];
        }
    }

    static int find(int idx) {
        if (parent[idx] == idx) {
            return idx;
        }

        return parent[idx] = find(parent[idx]);
    }

    static void init(BufferedReader br, StringTokenizer st) throws IOException{
        NODE_COUNT = Integer.parseInt(st.nextToken());
        EDGE_COUNT = Integer.parseInt(st.nextToken());
        TURN_COUNT = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= EDGE_COUNT; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = i;

            edge.add(new Edge(from, to, cost));
        }

        edge.sort(null);

        parent = new int[NODE_COUNT + 1];

        initParent();
    }

    static void initParent() {
        for (int i = 1; i <= NODE_COUNT; i++) {
            parent[i] = i;
        }
    }

    static boolean isDiffParent() {
        int now = find(1);

        for (int i = 2; i <= NODE_COUNT; i++) {
            if (now != find(i)) {
                return true;
            }
        }

        return false;
    }

    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int cost;

        @Override
        public int compareTo(Edge o){
            return this.cost - o.cost;
        }

        Edge (int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
}