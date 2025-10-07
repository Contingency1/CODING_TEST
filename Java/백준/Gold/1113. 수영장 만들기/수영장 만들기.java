import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.ArrayDeque;

public class Main {

    static int ROW_COUNT, COL_COUNT;
    static int[][] board;
    static boolean[][] possible;
    static int[][] offset = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    static List<Integer> leftNumber = new ArrayList<>();
    static int MAX = 0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        init(br);
        makeImpossible();

        int answer = 0;

        for (int number: leftNumber) {
            for (int i = 1; i < ROW_COUNT - 1; i++) {
                for (int j = 1; j < COL_COUNT - 1; j++) {
                    if (possible[i][j] && board[i][j] == number) {
                        answer += answerBfs(i, j, number);
                    }
                }
            }

        }

        System.out.print(answer);

    }

    static int answerBfs(int row, int col, int goal) {
        ArrayList<Coordinate> buffer = new ArrayList<>();
        Queue<Coordinate> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[ROW_COUNT][COL_COUNT];

        buffer.add(new Coordinate(row, col));
        queue.add(new Coordinate(row, col));
        visited[row][col] = true;

        int min = MAX;

        while (!queue.isEmpty()) {
            Coordinate polled = queue.poll();

            int curRow = polled.row;
            int curCol = polled.col;

            for (int[] set: offset) {
                int newRow = curRow + set[0];
                int newCol = curCol + set[1];

                if (newRow < 0 || newRow >= ROW_COUNT || 
                newCol < 0 || newCol >= COL_COUNT || visited[newRow][newCol]) {
                    continue;
                }

                if (board[newRow][newCol] < goal) {
                    return 0;
                } else if (board[newRow][newCol] > goal) {
                    min = Math.min(min, board[newRow][newCol]);
                    visited[newRow][newCol] = true;
                    continue;
                }

                queue.add(new Coordinate(newRow, newCol));
                buffer.add(new Coordinate(newRow, newCol));
                visited[newRow][newCol] = true;
            }
        }

        for (Coordinate b: buffer) {
            int curRow = b.row;
            int curCol = b.col;

            board[curRow][curCol] = min;
        }

        for (Coordinate b: buffer) {
            int curRow = b.row;
            int curCol = b.col;

            boolean flag = false;

            for (int[] set: offset) {
                int newRow = curRow + set[0];
                int newCol = curCol + set[1];
                
                if (newRow < 0 || newRow >= ROW_COUNT || 
                newCol < 0 || newCol >= COL_COUNT) {
                    continue;
                }
            
                if(board[curRow][curCol] == board[newRow][newCol] && !possible[newRow][newCol]) {
                    flag = true;
                    makeImpossibleBfs(curRow, curCol);
                    break;
                }
            }

            if (flag) {
                break;
            }
        }

        return buffer.size() * (min - goal);
    }

    // static void print() {
    //     for (int[] b: board) {
    //         System.out.println(Arrays.toString(b));
    //     }

    //     for (boolean[] b: possible) {
    //         System.out.println(Arrays.toString(b));
    //     }
    // }

    static void makeImpossible() {
        for (int i = 0; i < COL_COUNT; i++) {
            makeImpossibleBfs(0, i);
            makeImpossibleBfs(ROW_COUNT - 1, i);
        }

        for (int i = 1; i < ROW_COUNT - 1; i++) {
            makeImpossibleBfs(i, 0);
            makeImpossibleBfs(i, COL_COUNT - 1);
        }

        for (int i = 1; i < ROW_COUNT - 1; i++) {
            for (int j = 1; j < COL_COUNT - 1; j++) {
                if(board[i][j] == MAX) {
                    possible[i][j] = false;
                }
            }
        }
    }

    static void makeImpossibleBfs(int row, int col) {
        Queue<Coordinate> queue = new ArrayDeque<>();

        queue.add(new Coordinate(row, col));
        possible[row][col] = false;

        while (!queue.isEmpty()) {
            Coordinate polled = queue.poll();
            
            int curRow = polled.row;
            int curCol = polled.col;

            for (int[] off: offset) {
                int newRow = curRow + off[0];
                int newCol = curCol + off[1];

                if (newRow < 0 || newRow >= ROW_COUNT || 
                newCol < 0 || newCol >= COL_COUNT ||
                board[row][col] != board[newRow][newCol] || !possible[newRow][newCol]) {
                    continue;
                }

                possible[newRow][newCol] = false;
                queue.add(new Coordinate(newRow, newCol));
            }
        }

    }

    static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        ROW_COUNT = Integer.parseInt(st.nextToken());
        COL_COUNT = Integer.parseInt(st.nextToken());

        board = new int[ROW_COUNT][COL_COUNT];

        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < ROW_COUNT; i++) {
            int j = 0;

            for (char c: br.readLine().toCharArray()) {
                board[i][j] = c - '0';
                set.add(board[i][j]);
                MAX = Math.max(MAX, board[i][j]);
                j++;
            }
        }

        for (int s: set) {
            leftNumber.add(s);
        }

        leftNumber.sort(null);

        if(leftNumber.get(leftNumber.size() - 1) == MAX) {
            leftNumber.remove(leftNumber.size() - 1);
        }

        possible = new boolean[ROW_COUNT][COL_COUNT];
        
        for (int i = 1; i < ROW_COUNT - 1; i++) {
            for (int j = 1; j < COL_COUNT -1; j++) {
                possible[i][j] = true;
            }
        }
    }

    static class Coordinate {
        int row;
        int col;

        Coordinate (int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}