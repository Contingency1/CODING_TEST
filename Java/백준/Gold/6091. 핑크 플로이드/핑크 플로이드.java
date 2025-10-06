import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static int NODE_COUNT;
    static List<Edge> edge = new ArrayList<>();
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        init(br);

        edge.sort(null);

        List<List<Integer>> answer = new ArrayList<>();

        for (int i = 0; i <= NODE_COUNT; i++) {
            answer.add(new ArrayList<>());
        }

        int count = 0;
        int idx = 0;

        while (count < NODE_COUNT - 1) {
            Edge e = edge.get(idx++);

            int from = e.from;
            int to = e.to;

            if (find(from) == find(to)) {
                continue;
            }

            answer.get(from).add(to);
            answer.get(to).add(from);

            union(from, to);

            count++;
        }

        StringBuilder sb = new StringBuilder();
        
        for (int i = 1; i <= NODE_COUNT; i++) {
            sb.append(answer.get(i).size()).append(" ");
            answer.get(i).sort(null);

            for (int a: answer.get(i)) {
                sb.append(a).append(" ");
            }
            sb.deleteCharAt(sb.length() - 1).append("\n");
        }

        System.out.print(sb);
    }

    static int find(int idx) {
        if (parent[idx] == idx) {
            return idx;
        }

        return parent[idx] = find(parent[idx]);
    }

    static void union(int x, int y) {
        int parentX = find(x);
        int parentY = find(y);

        if (parentX != parentY) {
            parent[parentX] = parent[parentY];
        }
    }

    static void init(BufferedReader br) throws IOException{
        NODE_COUNT = Integer.parseInt(br.readLine());

        parent = new int[NODE_COUNT + 1];
        
        for (int i = 1; i <= NODE_COUNT; i++) {
            parent[i] = i;
        }

        for (int i = 1; i < NODE_COUNT; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = i + 1; j <= NODE_COUNT; j++) {
                edge.add(new Edge(i, j, Integer.parseInt(st.nextToken())));
            }
        }
    }

    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int cost;

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
        
        Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
}