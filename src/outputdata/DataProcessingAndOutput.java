package outputdata;

import inputdata.ConvertData;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * Created by ivand on 21.06.2017.
 */
public class DataProcessingAndOutput {

    private ConvertData convertData = new ConvertData();

    private static final String OUTPUT_FILE_PATH = "C:\\Users\\ivand\\Desktop\\output.txt";
    private static final String SOUGHT_LINE = "onetwotrip";
    private static final String OUTPUT_FAIL = "Impossible";
    private static final String CONSOLE_VIEW_SUCCESS = "Файл с результатом создан.\nСтрока успешно сформирована!";
    private static final String CONSOLE_VIEW_FAIL =
            "Файл с результатом создан.\nСтрока не сфомирована, отсутствуют необходимые символы в исходном файле!";

    private char[] soughtLineArr = SOUGHT_LINE.toCharArray();
    private char[] resultCharacters = new char[SOUGHT_LINE.length()];

    private String[] outputStrings = new String[SOUGHT_LINE.length()];

    private char[][] chArr = convertData.createMatrix();

    //Формирование массивов строк и символов с выходными данными, вывод результата вычислений в консоль
    public void matrixResult() {
        for (int i = 0; i < soughtLineArr.length; i++) {
            iterateThroughTheMatrix(chArr, i);
        }
        printConsoleResult();
    }

    //Поиск символов в исходной матрице
    private char[][] iterateThroughTheMatrix(char[][] charMatrix, int character) {
        char soughtCharacter = soughtLineArr[character];
        for (int line = 0; line < convertData.getMatrixLine(); line++) {
            for (int column = 0; column < convertData.getMatrixColumn(); column++) {
                char matrixCharacter = charMatrix[line][column];
                if (soughtCharacter == matrixCharacter || Character.toUpperCase(soughtCharacter) == matrixCharacter) {
                    resultCharacters[character] = matrixCharacter;
                    charMatrix[line][column] = '\u0000';
                    outputStrings[character] = resultCharacters[character] + " - (" + line + ", " + column + ");";
                    return charMatrix;
                }
            }
        }
        return charMatrix;
    }

    //Вывод данных в файл
    public void writeData() {
        try {
            File file = new File(OUTPUT_FILE_PATH);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            String newLine = System.getProperty("line.separator");

            if (isDecisionExist(resultCharacters, SOUGHT_LINE) == 0) {
                for (String outputString : outputStrings) {
                    bufferedWriter.write(outputString + newLine);
                }
            } else {
                bufferedWriter.write(OUTPUT_FAIL);
            }
            bufferedWriter.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //Сравниваем результат и исходные данные
    private int isDecisionExist(char[] ch, String r) {
        String s = new String(ch);
        return s.compareToIgnoreCase(r);
    }

    //Печатаем результат вычислений в консоль
    private void printConsoleResult() {
        if (isDecisionExist(resultCharacters, SOUGHT_LINE) == 0) {
            System.out.println(CONSOLE_VIEW_SUCCESS);
        } else {
            System.out.println(CONSOLE_VIEW_FAIL);
        }
    }
}