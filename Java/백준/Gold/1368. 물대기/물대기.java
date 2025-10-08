import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

public class Main {
    static int FIELD_COUNT;
    static List<Edge> edge = new ArrayList<>();
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        init(br);

        int count = 0;
        int idx = 0;

        int answer = 0;

        while (count < FIELD_COUNT ) {
            Edge cur = edge.get(idx++);

            int from = cur.from;
            int to = cur.to;
            int cost = cur.cost;

            if (find(from) == find(to)) {
                continue;
            }

            union(from, to);

            answer += cost;
            count++;
        }

        System.out.print(answer);

    }

    static int find(int x) {
        if (x == parent[x]) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        int parentX = find(x);
        int parentY = find(y);

        if(parentX != parentY) {
            parent[parentX] = parent[parentY];
        }
    }

    static void init(BufferedReader br) throws IOException {
        FIELD_COUNT = Integer.parseInt(br.readLine());

        parent = new int[FIELD_COUNT + 1];

        initParent();

        for (int i = 1; i <= FIELD_COUNT; i++) {
            edge.add(new Edge(0, i, Integer.parseInt(br.readLine())));
        }

        for (int i = 1; i <= FIELD_COUNT; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 1; j < i + 1; j++) {
                st.nextToken();
            }

            for (int j = i + 1; j <= FIELD_COUNT; j++) {
                int cur = Integer.parseInt(st.nextToken());
                
                edge.add(new Edge(i, j, cur));
            }
        }

        edge.sort(null);

    }

    static void initParent() {
        for (int i = 1; i <= FIELD_COUNT; i++) {
            parent[i] = i;
        }
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