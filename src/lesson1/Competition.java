package lesson1;



public class Competition {
    public static void main(String[] args) {
        // создания массива участников
        MovingEntity[] participants = new MovingEntity[3];
        participants[0] = new Human();
        participants[1] = new Cat();
        participants[2] = new Robot();


        // создание массива препятствий
        Barrier[] barriers = new Barrier[5];
        barriers[0] = new RunningTrack(100);
        barriers[1] = new Wall(10);
        barriers[2] = new RunningTrack(600);
        barriers[3] = new Wall(40);
        barriers[4] = new RunningTrack(1900);


        // соревнование где учатники не прошедшие состязание не переходят в следующий тур
        Game.doGame(participants, barriers);


    }


}
