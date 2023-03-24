package Task1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumbersValidator {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the directory of the file: ");
        String directory = scanner.nextLine();
        //Перевірка чи файл дійсно є за зазначеним шляхом
        File file = new File(directory);
        if (!file.exists()) {
            System.out.println("File not found.");
            return;
        }
        scanner.close();

        try (Scanner fileScanner = new Scanner(file)) {
            Pattern pattern1 = Pattern.compile("\\(\\d{3}\\) \\d{3}-\\d{4}");
            Pattern pattern2 = Pattern.compile("\\d{3}-\\d{3}-\\d{4}");

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                Matcher matcher1 = pattern1.matcher(line);
                Matcher matcher2 = pattern2.matcher(line);

                if (matcher1.matches() || matcher2.matches()) {
                    System.out.println(line);
                }
            }
        }
    }
}