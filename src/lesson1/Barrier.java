package lesson1;

public interface Barrier {
    boolean isCanDo(MovingEntity a); // возвращение значения сможет ли субъект преодолеть препядствие

    void isDone (MovingEntity a); // вывод в консоль результата преодоления препядствия
}
