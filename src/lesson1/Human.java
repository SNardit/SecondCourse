package lesson1;

public class Human implements MovingEntity {
    public static final int RUN_LENGTH = 1500; //метры
    public static final int JUMP_HEIGHT = 50; //сантиметры

    @Override
    public void running() {
        System.out.println("Человек бежит!");

    }

    @Override
    public void jumping() {
        System.out.println("Человек прыгает!");
    }

    @Override
    public int run() {
        return RUN_LENGTH;
    }

    @Override
    public int jump() {
        return JUMP_HEIGHT;
    }


}
