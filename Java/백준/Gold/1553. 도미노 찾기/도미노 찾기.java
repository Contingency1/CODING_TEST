import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int[][] board = new int[8][7];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        init(br);
        
        boolean[][] visited = new boolean[8][7];
        boolean[][] usedDomino = new boolean[7][7]; 

        System.out.print(back(0, 0, visited, usedDomino));
    }
    
    static void init(BufferedReader br) throws IOException {
        for (int i = 0; i < 8; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < 7; j++) {
                board[i][j] = input[j] - '0';
            }
        }

    }

    static int back(int row, int col, boolean[][] visited, boolean[][] usedDomino) {
        int answer = 0;

        if (row > 7) {
            return 1;
        }

        int originNumber = board[row][col];

        if (!visited[row][col]) {
            if (col + 1 < 7 && !usedDomino[originNumber][board[row][col + 1]] 
            && !visited[row][col + 1]) {

                usedDomino[originNumber][board[row][col + 1]] = true;
                usedDomino[board[row][col + 1]][originNumber] = true;
                visited[row][col] = true;
                visited[row][col + 1] = true;
                
                int newRow, newCol;
    
                if (col + 2 >= 7) {
                    newCol = 0;
                    newRow = row + 1;
                } else {
                    newRow = row;
                    newCol = col + 2;
                }
    
                answer += back(newRow, newCol, visited, usedDomino);
                
                usedDomino[originNumber][board[row][col + 1]]  = false;
                usedDomino[board[row][col + 1]][originNumber]  = false;
                visited[row][col + 1] = false;
                visited[row][col] = false;
            }
            
            if (row + 1 < 8 && !usedDomino[board[row + 1][col]][originNumber] 
            && !visited[row + 1][col]) {
                usedDomino[originNumber][board[row + 1][col]] = true;
                usedDomino[board[row + 1][col]][originNumber] = true;
                visited[row][col] = true;
                visited[row + 1][col] = true;

                int newRow, newCol;
    
                if (col + 1 >= 7) {
                    newCol = 0;
                    newRow = row + 1;
                } else {
                    newRow = row;
                    newCol = col + 1;
                }

                answer += back(newRow, newCol, visited, usedDomino);

                visited[row][col] = false;
                visited[row + 1][col] = false;
                usedDomino[originNumber][board[row + 1][col]] = false;
                usedDomino[board[row + 1][col]][originNumber] = false;
            }
        } else {
            int newRow, newCol;
    
            if (col + 1 >= 7) {
                newCol = 0;
                newRow = row + 1;
            } else {
                newRow = row;
                newCol = col + 1;
            }

            answer += back(newRow, newCol, visited, usedDomino);
        }

        return answer;
    }

    static boolean check(boolean[][] visited) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                if (!visited[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }
}