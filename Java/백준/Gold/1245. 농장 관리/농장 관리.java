import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.Queue;
import java.util.ArrayDeque;

public class Main {
    static int ROW_COUNT, COL_COUNT;
    static int[][] board;
    static boolean[][] visited;
    static int[][] offset = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}, 
                            {1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        init(br);

        for (int i = 0; i < ROW_COUNT; i++) {
            for (int j = 0; j < COL_COUNT; j++) {
                if (board[i][j] > 0 && !visited[i][j]) {
                    if(!bfs(i, j)) {
                        answer++;
                    }
                }
            }
        }

        System.out.print(answer);
    }

    static boolean bfs(int inputRow, int inputCol) {
        Queue<NodeJS> queue = new ArrayDeque<>();
        boolean flag = false;

        int curValue = board[inputRow][inputCol];

        visited[inputRow][inputCol] = true;
        queue.add(new NodeJS(inputRow, inputCol));

        while (!queue.isEmpty()) {
            NodeJS polled = queue.poll();

            int row = polled.row;
            int col = polled.col;

            for (int[] off: offset) {
                int newRow = row + off[0];
                int newCol = col + off[1];

                if (newRow >= 0 && newCol >= 0 &&
                newRow < ROW_COUNT && newCol < COL_COUNT &&
                board[newRow][newCol] > 0) {
                    if(board[newRow][newCol] > curValue) {
                        flag = true;
                    }
                    
                    if (!visited[newRow][newCol] && board[newRow][newCol] == curValue) {
                        visited[newRow][newCol] = true;
                        queue.add(new NodeJS(newRow, newCol));
                    }
                }
            }
        }

        return flag;
    }

    static void init(BufferedReader br) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());

        ROW_COUNT = Integer.parseInt(st.nextToken());
        COL_COUNT = Integer.parseInt(st.nextToken());

        board = new int[ROW_COUNT][COL_COUNT];
        visited = new boolean[ROW_COUNT][COL_COUNT];

        for (int i = 0; i < ROW_COUNT; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < COL_COUNT; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

    }

    static class NodeJS {
        int row;
        int col;
        
        NodeJS(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}