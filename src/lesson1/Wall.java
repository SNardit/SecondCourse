package lesson1;

public class Wall implements Barrier {

    private final int height;

    public Wall(int height) {
        this.height = height;
    }

    public boolean isCanDo (MovingEntity MovingEntity) {
        int jumpHeight = MovingEntity.jump();
        if (jumpHeight >= height) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void isDone(MovingEntity MovingEntity) {
        MovingEntity.jumping();
        if (isCanDo(MovingEntity)) {
            System.out.println("И перепрыгивает препятствие!");
        } else {
            System.out.println("К сожеленю прыжок завершился неудачей! И он выбывает из состязания");
        }
    }


}
