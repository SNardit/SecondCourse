package lesson2;

import javax.lang.model.type.NullType;

public class Array {
    private static final int SIZE = 4;

    public static void main(String[] args) {

        try {
            String [][] arr = new String[SIZE][SIZE];
            System.out.println("Сумма чисел в массиве равна " + array(arr));
        }
        catch (MyArraySizeException | MyArrayDataException | MyNullException e) {
            System.out.println(e.getMessage());
        }

    }

    public static int array (String [][] arr ) {
        if (arr.length != 4) {
            throw new MyArraySizeException();
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                arr[i][j] = "0" + i+j;
            }
        }
        arr [1][2] = "Oh";
        arr [3][2] = null;

        int sum = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {

                if ((String) arr[i][j] == null) {
                    throw new MyNullException(i, j);
                }
                if (arr[i][j].matches("\\D*")) {
                    throw new MyArrayDataException(i, j);
                }

                sum += (Integer.parseInt(arr[i][j], 10));

            }
        }

        return sum;
    }


}
