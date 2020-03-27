package lesson1;

public class RunningTrack implements Barrier {

    private final int length;

    public RunningTrack(int length) {
        this.length = length;
    }

    public boolean isCanDo (MovingEntity MovingEntity) {
        int runLength = MovingEntity.run();
        if (runLength >= length) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void isDone (MovingEntity MovingEntity) {
        MovingEntity.running();
        if (isCanDo(MovingEntity)) {
            System.out.println("И пробегает эту дистанцию");
        } else {
            System.out.println("Но у него не получилось и он выбывает из состязания!");
        }
    }
}
