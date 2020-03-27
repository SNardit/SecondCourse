package lesson1;

public interface MovingEntity {

        // вывод в консоль субъекта и его действия
        void running();
        void jumping();

        int run(); // для возвращения значения длины дистанции которую может пробежать субъект
        int jump(); // для возвращения значения высоты прыжка субъекта


}
