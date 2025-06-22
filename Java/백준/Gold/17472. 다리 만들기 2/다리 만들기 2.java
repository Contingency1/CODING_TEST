import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

  static int[][] matrix;
  static boolean[][] visited;
  static int[][] offset = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
  static int[] root;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    final int ROW_COUNT = Integer.parseInt(st.nextToken());
    final int COL_COUNT = Integer.parseInt(st.nextToken());

    // 초기 설정
    initMatrixAndVisited(ROW_COUNT, COL_COUNT, br);

    // 집합 만들기
    List<List<Point>> matrixList = new ArrayList<>();
    for (int i = 0; i < ROW_COUNT; i++) {
      for (int j = 0; j < COL_COUNT; j++) {
        if (matrix[i][j] == 1) {
          if (visited[i][j]) {
            continue;
          }
          bfs(i, j, matrixList);
        }
      }
    }

    // 간선 만들기
    PriorityQueue<Length> pq = new PriorityQueue<>();
    for (int i = 0; i < matrixList.size() - 1; i++) {
      for (int j = i + 1; j < matrixList.size(); j++) {
        List<Point> list1 = matrixList.get(i);
        List<Point> list2 = matrixList.get(j);
        for (Point pt1 : list1) {
          for (Point pt2 : list2) {

            int pt1Col = pt1.col;
            int pt1Row = pt1.row;

            int pt2Col = pt2.col;
            int pt2Row = pt2.row;

            // 어느 한 줄도 공유하지 않는다면 skip
            if (pt1Col != pt2Col && pt1Row != pt2Row) {
              continue;
            }

            boolean isRowEqual = false;
            // -면 pt1 이 더 작음
            // +면 pt1 이 더 큼
            int distance = pt1Row - pt2Row;

            // 행 공유
            if (pt1Row == pt2Row) {
              isRowEqual = true;
              distance = pt1Col - pt2Col;
            }

            if (Math.abs(distance) <= 2) {
              continue;
            }

//            System.out.println("pt1 = " + pt1);
//            System.out.println("pt2 = " + pt2);
            boolean stop = false;

            // row가 같을 때
            if (isRowEqual) {
              // pt1 더 큼
              if (distance > 0) {
                for (int k = pt1Col - 1; k > pt2Col; k--) {
                  if (matrix[pt1Row][k] == 1) {
                    stop = true;
                    break;
                  }
                }
//                System.out.println("stop1 = " + stop);

                if (stop) {
//                  System.out.println("=====");
                  continue;
                }

                Length l = new Length(i, j, Math.abs(distance) - 1);
                pq.add(new Length(i, j, Math.abs(distance) - 1));
//                System.out.println("l = " + l);
//                System.out.println("=====");
                // pt1 이 더 작음
              } else {
//                System.out.println("distance = " + distance);
                for (int k = pt1Col + 1; k < pt2Col; k++) {
                  if (matrix[pt1Row][k] == 1) {
                    stop = true;
                    break;
                  }
                }
//                System.out.println("stop2 = " + stop);

                if (stop) {
//                  System.out.println("=====");
                  continue;
                }

                Length l = new Length(i, j, Math.abs(distance) - 1);
                pq.add(new Length(i, j, Math.abs(distance) - 1));
//                System.out.println("l = " + l);
//                System.out.println("=====");
              }
              // col이 같을때
            } else {
              // pt1이 더 큼
              if (distance > 0) {
                for (int k = pt1Row - 1; k > pt2Row; k--) {
                  if (matrix[k][pt1Col] == 1) {
                    stop = true;
                    break;
                  }
                }
//                System.out.println("stop3 = " + stop);
                if (stop) {
//                  System.out.println("=====");
                  continue;
                }

                Length l = new Length(i, j, Math.abs(distance) - 1);
                pq.add(new Length(i, j, Math.abs(distance) - 1));
//                System.out.println("l = " + l);

//                System.out.println("=====");
                //pt1이 더 작음
              } else {
                for (int k = pt1Row + 1; k < pt2Row; k++) {
//                  System.out.println("k = " + k);
//                  System.out.println("matrix[k][pt1Col] = " + matrix[k][pt1Col]);
                  if (matrix[k][pt1Col] == 1) {
                    stop = true;
                    break;
                  }
                }

//                System.out.println("stop4 = " + stop);
                if (stop) {
//                  System.out.println("=====");
                  continue;
                }

                Length l = new Length(i, j, Math.abs(distance) - 1);
                pq.add(new Length(i, j, Math.abs(distance) - 1));
//                System.out.println("l = " + l);
//                System.out.println("=====");
              }
            }


          }
        }

      }
    }

    // 최적의 수 구하기
    initRoot(matrixList);

    int answer = 0;
    int count = 0;

    while (!pq.isEmpty()) {
      Length poll = pq.poll();
      int to = poll.to;
      int from = poll.from;
      int length = poll.length;

      if (find(from) == find(to)) {
        continue;
      }

      union(to, from);
      answer += length;
      count++;
    }

    if (count != matrixList.size() - 1) {
      System.out.print(-1);
      return;
    }

    System.out.print(answer);
  }


  private static void initRoot(List<List<Point>> matrixList) {
    root = new int[matrixList.size()];
    for (int i = 0; i < matrixList.size(); i++) {
      root[i] = i;
    }
  }

  private static void bfs(int row, int col, List<List<Point>> matrixList) {
    matrixList.add(new ArrayList<>());

    ArrayDeque<Point> queue = new ArrayDeque<>();
    queue.add(new Point(row, col));
    visited[row][col] = true;

    while (!queue.isEmpty()) {
      Point poll = queue.poll();
      int r = poll.row;
      int c = poll.col;
      matrixList.get(matrixList.size() - 1).add(new Point(r, c));

      for (int[] ints : offset) {
        int nextRow = r + ints[0];
        int nextCol = c + ints[1];

        if (nextRow < 0 || nextRow >= matrix.length ||
            nextCol < 0 || nextCol >= matrix[0].length ||
            visited[nextRow][nextCol] || matrix[nextRow][nextCol] == 0) {
          continue;
        }

        queue.add(new Point(nextRow, nextCol));
        visited[nextRow][nextCol] = true;
      }

    }

  }

  private static void initMatrixAndVisited(int N, int M, BufferedReader br) throws IOException {
    StringTokenizer st;
    matrix = new int[N][M];
    visited = new boolean[N][M];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        matrix[i][j] = Integer.parseInt(st.nextToken());
      }
    }
  }

  private static void union(int x, int y) {
    int xRoot = find(x);
    int yRoot = find(y);

    if (xRoot == yRoot) {
      return;
    }

    root[yRoot] = xRoot;
  }

  private static int find(int x) {
    if (x == root[x]) {
      return x;
    }

    return root[x] = find(root[x]);
  }

  static class Length implements Comparable<Length> {

    int from;
    int to;
    int length;

    public Length(int from, int to, int length) {
      this.from = from;
      this.to = to;
      this.length = length;
    }

    @Override
    public String toString() {
      return "Length{" +
          "from=" + from +
          ", to=" + to +
          ", length=" + length +
          '}';
    }

    @Override
    public int compareTo(Length o) {
      return this.length - o.length;
    }
  }

  static class Point {

    int row;
    int col;

    public Point(int row, int col) {
      this.row = row;
      this.col = col;
    }

    @Override
    public String toString() {
      return "Point{" +
          "row=" + row +
          ", col=" + col +
          '}';
    }
  }
}