package lesson3;


import java.util.*;

public class DoubleWords {
    public static void main(String[] args) {


    String[] arr = {"apple", "orange", "strawberries", "blueberry", "banana", "blackberries",
            "clementine", "peach", "orange", "raspberries", "strawberries",  "orange", "plum", "raspberries", "apple",
            "apple", "apple"};

    
    Map<String, Integer> wordsRepeatCount = new TreeMap<>();

        for (String s : arr) {
            if (!wordsRepeatCount.containsKey(s)) {
                wordsRepeatCount.put(s, 0);
            }
            wordsRepeatCount.put(s, wordsRepeatCount.get(s) + 1);
        }

       wordsRepeatCount.forEach((k ,v) -> {
            System.out.println(k + " - " + v);
        });

    }
}

