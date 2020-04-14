package lesson5;

import java.util.Arrays;

public class ProcessTimeTest {

    static final int size = 10000000;
    static float[] arr = new float[size];


    public static void createArrWithSingleTreading (float[] array) {
        Arrays.fill(array, 1);
        long timeStart = System.currentTimeMillis();
        for (int i = 0; i < array.length; i++) {
            array[i] = (float)(array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long timeEnd = System.currentTimeMillis();
        System.out.println("Array with single treading: \n" + (timeEnd - timeStart) + " milliseconds spent processing");

    }

    public static void createArrWithMultiTreading (float[] array) throws InterruptedException {
        Arrays.fill(array, 1);
        int halfArraySize = array.length/2;

        float[] array1 = new float[halfArraySize];
        float[] array2 = new float[halfArraySize];


        long timeStart = System.currentTimeMillis();

        System.arraycopy(array, 0, array1, 0, halfArraySize);
        System.arraycopy(array, halfArraySize, array2, 0, halfArraySize);

        Thread thread1 = new Thread(()-> {
            for (int i = 0; i < array1.length; i++) {
                array1[i] = (float)(array1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        }, "Thread1");


        Thread thread2 = new Thread(()-> {
            for (int i = 0; i < array2.length; i++) {
                long k = i + halfArraySize;
                array2[i] = (float)((array2[i]) * Math.sin(0.2f + k / 5) * Math.cos(0.2f + k / 5) * Math.cos(0.4f + k / 2));
            }
        }, "Thread2");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.arraycopy(array1, 0, array, 0, halfArraySize);
        System.arraycopy(array2, 0, array, halfArraySize, halfArraySize);

        long timeEnd = System.currentTimeMillis();
        System.out.println("Array with multi treading: \n" + (timeEnd - timeStart) + " milliseconds spent processing");
    }

    public static void sumArray (float[] array) {
        float sum = 0;
        for (float v : array) {
            sum += v;
        }
        System.out.println("Array amount: " + sum);

    }

    public static void main(String[] args) throws InterruptedException {
        createArrWithSingleTreading(arr);
        sumArray(arr);

        System.out.println();

        createArrWithMultiTreading(arr);
        sumArray(arr);
    }
}
