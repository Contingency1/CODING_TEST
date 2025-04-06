import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int[] input = Arrays.stream(br.readLine().split(" "))
        .mapToInt(Integer::parseInt).toArray();

    int ladderCount = input[0];
    int snakeCount = input[1];

    HashMap<Integer, Integer> ladders = new HashMap<>();

    for (int i = 0; i < ladderCount; i++) {
      int[] buffer = Arrays.stream(br.readLine().split(" "))
          .mapToInt(Integer::parseInt).toArray();

      ladders.put(buffer[0], buffer[1]);
    }

    HashMap<Integer, Integer> snakes = new HashMap<>();

    for (int i = 0; i < snakeCount; i++) {
      int[] buffer = Arrays.stream(br.readLine().split(" "))
          .mapToInt(Integer::parseInt).toArray();

      snakes.put(buffer[0], buffer[1]);
    }

    int[] offset = {1, 2, 3, 4, 5, 6};

    int answer = Integer.MAX_VALUE;
    ArrayDeque<NodeJS> arr = new ArrayDeque<>();
    boolean[] visited = new boolean[101];

    arr.add(new NodeJS(1, 0));
    visited[1] = true;

    while (!arr.isEmpty()) {
      NodeJS nd = arr.poll();

      for (int set : offset) {
        int newLocation = nd.location + set;

        if (newLocation == 100) {
          answer = Math.min(nd.count + 1, answer);
          arr.clear();
          break;
        }

        if (newLocation > 100) {
          break;
        }

        if (visited[newLocation]) {
          continue;
        }

        if (ladders.containsKey(newLocation)) {
          arr.add(new NodeJS(ladders.get(newLocation), nd.count + 1));
          visited[ladders.get(newLocation)] = true;
        } else if (snakes.containsKey(newLocation)) {
          arr.add(new NodeJS(snakes.get(newLocation), nd.count + 1));
          visited[snakes.get(newLocation)] = true;
        } else {
          arr.add(new NodeJS(newLocation, nd.count + 1));
          visited[newLocation] = true;
        }
      }
    }

    sb.append(answer);

    System.out.print(sb);
  }
}

class NodeJS {

  int location;
  int count;

  public NodeJS(int location, int count) {
    this.location = location;
    this.count = count;
  }
}
