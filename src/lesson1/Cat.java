package lesson1;

public class Cat implements MovingEntity {
    public static final int RUN_LENGTH = 400; //метры
    public static final int JUMP_HEIGHT = 180; //сантиметры

    @Override
    public void running() {
        System.out.println("Кот бежит!");

    }

    @Override
    public void jumping() {
        System.out.println("Кот прыгает!");

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
