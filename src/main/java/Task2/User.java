package Task2;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User {
    private String name;
    private int age;

    User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public static void main(String[] args) throws IOException {
        Scanner fileDirectory = new Scanner(System.in);
        System.out.print("Enter the directory of the file: ");
        String directory = fileDirectory.nextLine();

        File file = new File(directory);
        //Перевірка чи файл дійсно є за зазначеним шляхом.
        if (!file.exists()) {
            System.out.println("File not found.");
            return;
        }
        fileDirectory.close();

        File inputFile = new File(directory);
        FileWriter outputFile = new FileWriter("user.json");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<User> userList = new ArrayList<>(); //Створення List для зберігання данних.

        try (Scanner scanner = new Scanner(inputFile)) {
            String[] headers = scanner.nextLine().split(" ");
            while (scanner.hasNextLine()) {  //Зчитування данних з файлу та внесення їх в наш List.
                String[] value = scanner.nextLine().split(" ");
                User user = new User(value[0], Integer.parseInt(value[1]));
                userList.add(user);
            }
        }
        //Створюємо json файл та перевіряємо буффер, після чого закриваємо потік.
        gson.toJson(userList, outputFile);
        outputFile.flush();
        outputFile.close();
    }
}
