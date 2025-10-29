import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.ArrayDeque;

public class Main {

    static int DANGEROUS_COUNT, DEATH_COUNT;
    static int[][] board;
    static boolean[][] visited;
    static int[][] offset = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        init(br);

        if (board[500][500] == 2) {
            System.out.print(-1);
            return;
        }

        Queue<Status> queue = new PriorityQueue<>();
        queue.add(new Status(0, 0, 0));
        visited[0][0] = true;

        int answer = -1;

        while (!queue.isEmpty()) {
            Status polled = queue.poll();

            int row = polled.row;
            int col = polled.col;
            int cost = polled.cost;

            if (row == 500 && col == 500) {
                answer = cost;
                
                if (cost == 0) {
                    answer = 0;
                }
                
                break;
            }

            for (int[] set: offset) {
                int newRow = row + set[0];
                int newCol = col + set[1];

                if (newRow >=0 && newRow <= 500 
                && newCol >=0 && newCol <= 500
                && !visited[newRow][newCol] && board[newRow][newCol] <= 1) {
                    if(board[newRow][newCol] == 1) {
                        queue.add(new Status(newRow, newCol, cost + 1));
                    } else {
                        queue.add(new Status(newRow, newCol, cost));
                    }

                    visited[newRow][newCol] = true;
                }

            }

        }

        System.out.print(answer);
    }

    static class Status implements Comparable<Status>{
        int row;
        int col;
        int cost;

        @Override
        public int compareTo(Status o) {
            return this.cost - o.cost;
        }

        Status(int row, int col, int cost) {
            this.row = row;
            this.col = col;
            this.cost = cost;
        }
    }

    static void init(BufferedReader br) throws IOException {
        DANGEROUS_COUNT = Integer.parseInt(br.readLine());
        board = new int[501][501];
        visited = new boolean[501][501];
        
        StringTokenizer st;

        for (int i = 0; i < DANGEROUS_COUNT; i++) {
            st = new StringTokenizer(br.readLine());

            int row1 = Integer.parseInt(st.nextToken());
            int col1 = Integer.parseInt(st.nextToken());
            int row2 = Integer.parseInt(st.nextToken());
            int col2 = Integer.parseInt(st.nextToken());

            int rStart, rEnd;

            if (row1 <= row2) {
                rStart = row1;
                rEnd = row2;
            } else {
                rStart = row2;
                rEnd = row1;
            }

            int cStart, cEnd;

            if (col1 <= col2) {
                cStart = col1;
                cEnd = col2;
            } else {
                cStart = col2;
                cEnd = col1;
            }

            for (int r = rStart; r <= rEnd; r++) {
                for (int c = cStart; c <= cEnd; c++) {
                    board[r][c] = 1;
                }
            }
        }

        DEATH_COUNT = Integer.parseInt(br.readLine());

        for (int i = 0; i < DEATH_COUNT; i++) {
            st = new StringTokenizer(br.readLine());

            int row1 = Integer.parseInt(st.nextToken());
            int col1 = Integer.parseInt(st.nextToken());
            int row2 = Integer.parseInt(st.nextToken());
            int col2 = Integer.parseInt(st.nextToken());

            int rStart, rEnd;

            if (row1 <= row2) {
                rStart = row1;
                rEnd = row2;
            } else {
                rStart = row2;
                rEnd = row1;
            }

            int cStart, cEnd;

            if (col1 <= col2) {
                cStart = col1;
                cEnd = col2;
            } else {
                cStart = col2;
                cEnd = col1;
            }

            for (int r = rStart; r <= rEnd; r++) {
                for (int c = cStart; c <= cEnd; c++) {
                    board[r][c] = 2;
                }
            }
        }
    }

}