package inputdata;

/**
 * Created by ivand on 19.06.2017.
 */
public class ConvertData {

    private ImportData importData = new ImportData();

    private int matrixLine;
    private int matrixColumns;

    private char[][] inputMatrix = new char[getMatrixLine()][getMatrixColumn()];

    //Определяем количество строк
    public int getMatrixLine() {
        String firstLine = importData.readData().get(0);
        char[] matrixSize = firstLine.toCharArray();
        matrixLine = Character.getNumericValue(matrixSize[0]);
        return matrixLine;
    }

    //Определяем количество столбцов
    public int getMatrixColumn() {
        String firstLine = importData.readData().get(0);
        char[] matrixSize = firstLine.toCharArray();
        matrixColumns = Character.getNumericValue(matrixSize[2]);
        return matrixColumns;
    }

    //Создаем матрицу
    public char[][] createMatrix() {
        for (int i = 0; i < getMatrixLine(); i++) {
            inputMatrix[i] = importData.readData().get(i + 1).toCharArray();
        }
        return inputMatrix;
    }
}

