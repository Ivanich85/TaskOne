package inputdata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by ivand on 18.06.2017.
 */
public class ImportData {

    private static String inputFilePath = "C:\\Users\\ivand\\Desktop\\input.txt";
    private static final String INVALID_FILE_PATH =
            "Файл отсутствует! Проверьте путь к файлу.\nДля выхода из программы введите \"exit\"";

    private ArrayList lines = new ArrayList();

    //Чтение данных и ообработка ошибки, если файл отсутствует
    public ArrayList<String> readData() {
        try {
            File inputFile = new File(inputFilePath);

            FileReader fileReader = new FileReader(inputFile);
            BufferedReader reader = new BufferedReader(fileReader);

            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();

        } catch (Exception ex) {
            System.out.println(INVALID_FILE_PATH);
            Scanner scanner = new Scanner(System.in);
            String newFilePath = scanner.next();
            inputFilePath = newFilePath;
            if (inputFilePath.equals("exit")) {
                System.exit(0);
            }
            readData();
        }
        return lines;
    }
}