package main.java.org.example.usecases;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ShiftAndUsecase {

    public Map<Integer, Integer> execute(String text, String search) {
        Map<Integer, Integer> markMap = new TreeMap<>();
        int size = search.length();

        Map<Character, int[]> map = preProcess(search);

        int[] currentThread = new int[size];
        int[] constThread = new int[size];
        Arrays.fill(constThread, 0);
        Arrays.fill(currentThread, 0);
        constThread[0] = 1;

        for (int i = 0; i < text.length(); i++) {
            char character = text.charAt(i);

            int[] orResultThread = new int[size];
            int[] andResultThread = new int[size];

            int[] threadOfChar = map.getOrDefault(character, null);
            if (threadOfChar == null) {
                currentThread = new int[size];
            } else {
                shift(currentThread);

                for (int j = 0; j < size; j++)
                    orResultThread[j] = currentThread[j] == 1 || constThread[j] == 1 ? 1 : 0;

                for (int j = 0; j < size; j++)
                    andResultThread[j] = orResultThread[j] == 1 && threadOfChar[j] == 1 ? 1 : 0;

                if (andResultThread[size - 1] == 1) {
                    if (i + 1 < text.length()) {
                        if (text.charAt(i + 1) == ' ') {
                            if (i - size + 1 >= 0) {
                                if (text.charAt(i - size) == ' ') {
                                    markMap.put(i - size + 1, i);
                                }
                            } else {
                                markMap.put(i - size + 1, i);
                            }
                        }
                    } else {
                        if (i - size >= 0) {
                            if (text.charAt(i - size) == ' ') {
                                markMap.put(i - size + 1, i);
                            }
                        } else {
                            markMap.put(i - size + 1, i);
                        }
                    }
                }

                currentThread = andResultThread;
            }
        }

        return markMap;
    }

    public void shift(int[] thread) {
        for (int j = thread.length - 1; j > 0; j--)
            thread[j] = thread[j - 1];
        thread[0] = 0;
    }

    public Map<Character, int[]> preProcess(String search) {
        Map<Character, int[]> map = new HashMap<>();
        int size = search.length();

        for (int i = 0; i < size; i++) {
            char value = search.charAt(i);

            int[] thread = new int[size];
            if (map.containsKey(value))
                thread = map.get(value);

            thread[i] = 1;
            map.put(value, thread);
        }

        return map;
    }
}
