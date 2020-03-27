package lesson1;

public class Robot implements MovingEntity {
    public static final int RUN_LENGTH = 4000; //метры
    public static final int JUMP_HEIGHT = 30; //сантиметры

    @Override
    public void running() {
         System.out.println("Робот бежит!");

    }

    @Override
    public void jumping() {
         System.out.println("Робот прыгает!");
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
