import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

import org.w3c.dom.Node;

public class Main {

  static List<Input> list = new ArrayList<>();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int count = Integer.parseInt(br.readLine());

    init(count, br, list);

    List<State> history = new ArrayList<>();
    history.add(new State(0, ""));

    for (int i = 0; i < list.size(); i++) {
      Input cur = list.get(i);

      switch (cur.getCommand()) {
        case TYPE:
        char c1 = cur.getCharacter();
        int time1 = cur.getTime();
          String str = history.get(history.size() - 1).str;

          StringBuilder sb = new StringBuilder();
          history.add(new State(time1, sb.append(str).append(c1).toString()));

          break;
        case UNDO:
          for (int j = history.size() - 1; j >= 0; j--) {
            State current = history.get(j);

            int canExcute = (cur.getTime() - cur.getUndoTime());
            canExcute = canExcute < 0 ? 0 : canExcute;

            if (canExcute == 0) {
              history.add(new State(cur.getTime(), ""));
              break;
            }

            if (current.time < canExcute) {
              StringBuilder sb2 = new StringBuilder();
              history.add(new State(cur.getTime(), sb2.append(current.str).toString()));

              break;
            }
          }
      }
    }

    System.out.print(history.get(history.size() - 1).str);
  }

  static class State{
    int time;
    String str;

    State(int time, String str) {
      this.time = time;
      this.str = str;
    }
  }

  private static void init(int count, BufferedReader br, List<Input> list) throws IOException {
    StringTokenizer st;

    for (int i = 0; i < count; i++) {
      st = new StringTokenizer(br.readLine());

      Command command = Command.from(st.nextToken());

      switch (command) {
        case TYPE:
          char c = st.nextToken().charAt(0);
          int time = Integer.parseInt(st.nextToken());

          list.add(new Input(command, c, null, time));

          break;
        case UNDO:
          int undoTime = Integer.parseInt(st.nextToken());
          int time2 = Integer.parseInt(st.nextToken());

          list.add(new Input(command, null, undoTime, time2));

      }
    }
  }

  static class Input {
    private final Command command;

    private Character character;

    private Integer undoTime;

    private final int time;

    public Input(Command command, Character character, Integer undoTime, int time) {
      this.command = command;
      this.character = character;
      this.undoTime = undoTime;
      this.time = time;
    }

    public Command getCommand() {
      return command;
    }

    public Character getCharacter() {
      if (this.character == null) {
        throw new RuntimeException("Character NOO");
      }

      return character;
    }

    public Integer getUndoTime() {
      if (undoTime == null) {
        throw new RuntimeException("UndoTime NOO");
      }
      return undoTime;
    }

    public int getTime() {
      return time;
    }
  }

  enum Command {
    TYPE("type"), UNDO("undo");

    private final String name;

    public String getName() {
      return name;
    }

    Command(String name) {
      this.name = name;
    }

    public static Command from(String name) {
      for (Command value : Command.values()) {
        if (value.getName().equals(name)) {
          return value;
        }
      }

      throw new RuntimeException("NOOO");
    }
  }
}

