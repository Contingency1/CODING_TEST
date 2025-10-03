import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

public class Main {

    static int CITY_COUNT, ROAD_COUNT, ROAD_COST;
    static List<Edge> edge = new ArrayList<>();
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        init(br, st);

        int result = 0;
        int count = 0;
        for (Edge e: edge) {
            if (count == CITY_COUNT - 1) {
                break;
            }

            int from = e.from;
            int to = e.to;
            int cost = e.cost;

            if (find(from) == find(to)) {
                continue;
            }

            union(from, to);
            result += cost + count * ROAD_COST;
            count++;
        }

        System.out.print(result);
    }

    static void union (int x, int y) {

        int parentX = find(x);
        int parentY = find(y);

        if(parentX != parentY) {
            parent[parentX] = parent[parentY];
        }
    }

    static int find (int idx) {
        if (parent[idx] == idx) {
            return idx;
        }

        return parent[idx] = find(parent[idx]);
    }


    static void init(BufferedReader br, StringTokenizer st) throws IOException{
        CITY_COUNT = Integer.parseInt(st.nextToken());
        ROAD_COUNT = Integer.parseInt(st.nextToken());
        ROAD_COST = Integer.parseInt(st.nextToken());

        parent = new int[CITY_COUNT + 1];

        for (int i = 1; i <= CITY_COUNT; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < ROAD_COUNT; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            
            edge.add(new Edge(from, to, cost));
        }

        edge.sort(null);
    }

    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int cost;

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }

        Edge(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }


}