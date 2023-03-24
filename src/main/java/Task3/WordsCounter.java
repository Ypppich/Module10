package Task3;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Scanner;
public class WordsCounter {
    public static void main(String[] args) throws IOException {
        Scanner fileDirectory = new Scanner(System.in);
        System.out.print("Enter the directory of the file: ");
        String directory = fileDirectory.nextLine();

        File words = new File(directory);
        //Перевірка чи файл дійсно є за зазначеним шляхом.
        if (!words.exists()) {
            System.out.println("File not found.");
            return;
        }
        fileDirectory.close();

        printWordsCounter(wordsFrequencyCount(directory));
    }

    public static Map<String, Integer> wordsFrequencyCount(String directory) throws IOException {
        Map<String, Integer> wordCount = new HashMap<>(); //Створюємо HashMap для зберігання слів та кількості їх повторень.

        try (Scanner scanner = new Scanner(new File(directory))) {
            while (scanner.hasNext()) {
                String[] words = scanner.nextLine().split("\\s+"); //Розділяємо рядки на масиви, щоб в подальшому їх перебрати.
                for(String word: words) {
                    if(!word.isEmpty()) {
                        if (wordCount.containsKey(word)) {
                            wordCount.put(word, wordCount.get(word) + 1); //Добавляємо до лічильника слова (+1) якщо слово повторюється.
                        } else {
                            wordCount.put(word, 1); //Додаємо новий ключ word з лічильником 1.
                        }
                    }
                }
            }
        }
        return wordCount;
    }

    public static void printWordsCounter(Map<String, Integer> wordCounts) {
        //Створюємо TreeMap для сортування за зростанням кількості входжень.
        Map<Integer, String> sortedWordCounts = new TreeMap<>();
        for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
            sortedWordCounts.put(entry.getValue(), entry.getKey());
        }
        //Вивід у консоль.
        for (Map.Entry<Integer, String> entry : sortedWordCounts.entrySet()) {
            System.out.println(entry.getValue() + " " + entry.getKey());
        }
    }
}
