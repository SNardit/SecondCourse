package lesson2;

public class MyNullException extends NullPointerException {

    public MyNullException(int i, int j) {
        super("Ячейке с координатами [" + i + "][" + j + "] значение не присвоено!");
    }
}
