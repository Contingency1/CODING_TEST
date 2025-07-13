import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class Main {


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    final int WORD_COUNT = Integer.parseInt(br.readLine());

    List<Word> wordList = new ArrayList<>();

    for (int i = 0; i < WORD_COUNT; i++) {
      wordList.add(new Word(br.readLine().toCharArray()));
    }
    wordList.sort(Comparable::compareTo);

    Map<Integer, List<Character>> map = new HashMap<>();
    for (Word word : wordList) {
      char[] wordArr = word.wordArray;

      int start = wordArr.length - 1;

      for (int i = 0; i < wordArr.length; i++) {
        int index = start - i;
        if (map.containsKey(index)) {
          map.get(index).add(wordArr[i]);
        } else {
          map.put(index, new ArrayList<>());
          map.get(index).add(wordArr[i]);
        }
      }
    }

    Map<Character, Integer> charMap = new HashMap<>();

    for (Integer i : map.keySet()) {
      for (Character chr : map.get(i)) {
        int multi = (int) Math.pow(10, i);

        if (charMap.containsKey(chr)) {
          charMap.put(chr, charMap.get(chr) + multi);
        } else {
          charMap.put(chr, multi);
        }
      }
    }

    PriorityQueue<FinalPriority> pq = new PriorityQueue<>();
    for (Entry<Character, Integer> entry : charMap.entrySet()) {
      pq.add(new FinalPriority(entry.getKey(), entry.getValue()));
    }
    int criteria = 9;
    int answer = 0;
    while (!pq.isEmpty()) {
      answer += pq.poll().priority * criteria--;
    }
    System.out.print(answer);

    //    B:9  P:8  D:7  A:6  G:5  C:4  E:3
    //    3323

  }


  static class FinalPriority implements Comparable<FinalPriority> {

    char character;
    int priority;

    public FinalPriority(char character, int priority) {
      this.character = character;
      this.priority = priority;
    }


    @Override
    public int compareTo(FinalPriority o) {
      return o.priority - priority;
    }
  }

  static class Word implements Comparable<Word> {

    char[] wordArray;

    public Word(char[] wordArray) {
      this.wordArray = wordArray;
    }

    @Override
    public int compareTo(Word o) {
      return o.wordArray.length - this.wordArray.length;
    }
  }

}

