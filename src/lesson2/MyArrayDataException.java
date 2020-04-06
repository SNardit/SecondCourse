package lesson2;

public class MyArrayDataException extends NumberFormatException {

    public MyArrayDataException(int i, int j) {
        super("В ячейке с координатами [" + i + "][" + j + "] не числовое значение!");
    }

}
