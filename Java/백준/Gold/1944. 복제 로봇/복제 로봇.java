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
    public static int MIRROR, KEY_COUNT;
    static char[][] board;
    static int[][] offset = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    static int[] start = new int[2];
    static int[][] node;
    static List<Edge> edge = new ArrayList<>();
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        init(br);

        bfs(start[0], start[1]);

        if(edge.size() != KEY_COUNT) {
            System.out.print(-1);
            return;
        }

        for (int i = 1; i < MIRROR - 1; i++) {
            for (int j = 1; j < MIRROR - 1; j++) {
                if (board[i][j] == 'K') {
                    bfs(i, j);
                }
            }
        }

        edge.sort(null);

        int count = 0;
        int idx = 0;

        int answer = 0;

        while (count < KEY_COUNT) {
            Edge cur = edge.get(idx);
            int curFrom = cur.from;
            int curTo = cur.to;
            int curCost = cur.cost;
            idx++;

            if(find(curFrom) == find(curTo)) {
                continue;
            }

            union(curFrom, curTo);
            answer += curCost;
            count++;
        }

        System.out.print(answer);
    }

    static void init(BufferedReader br) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());

        MIRROR = Integer.parseInt(st.nextToken());
        KEY_COUNT = Integer.parseInt(st.nextToken());

        board = new char[MIRROR][MIRROR];
        node = new int[MIRROR][MIRROR];

        int keyNumber = 2;
        for (int i = 0; i < MIRROR; i++) {
            char[] buffer = br.readLine().toCharArray();

            for (int j = 0; j < MIRROR; j++) {
                if (buffer[j] == 'S') {
                    start[0] = i;
                    start[1] = j;
                    node[i][j] = 1;
                } else if (buffer[j] == 'K') {
                    node[i][j] = keyNumber++;
                }

                board[i][j] = buffer[j];
            }
        }

        parent = new int[KEY_COUNT + 2];
 
        for (int i = 1; i < parent.length; i++) {
            parent[i] = i;
        }
    }

    static void union(int x, int y) {
        int parentX = find(x);
        int parentY = find(y);

        if(parentX != parentY) {
            parent[parentX] = parent[parentY];
        }
    }

    static int find(int idx) {
        if (parent[idx] == idx) {
            return idx;
        }

        return parent[idx] = find(parent[idx]);
    }

    static void bfs (int row, int col) {
        Queue<Node> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[MIRROR][MIRROR];

        queue.add(new Node(row, col, 0));
        visited[row][col] = true;

        while (!queue.isEmpty()) {
            Node polled = queue.poll();

            int curRow = polled.row;
            int curCol = polled.col;
            int curCost = polled.cost;

            for (int[] set: offset) {
                int newRow = curRow + set[0];
                int newCol = curCol + set[1];
                int newCost = curCost + 1;

                if (board[newRow][newCol] == '1' || visited[newRow][newCol]) {
                    continue;
                }

                queue.add(new Node(newRow, newCol, newCost));
                visited[newRow][newCol] = true;

                if (board[newRow][newCol] == 'S' || board[newRow][newCol] == 'K') {
                    edge.add(new Edge(node[row][col], node[newRow][newCol], newCost));
                }
            }
        }
    }

    static class Node {
        int row;
        int col;
        int cost;
        
        Node(int row, int col, int cost) {
            this.row = row;
            this.col = col;
            this.cost = cost;
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