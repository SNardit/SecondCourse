package lesson1;

public class Game {
    public static void doGame(MovingEntity[] participants, Barrier[] barriers) {
        for (int i = 0; i < barriers.length; i++) {
            System.out.println("------------------Состязание " + (i+1) + " --------------------------");
            for (int j = 0; j < participants.length; j++) {
                logicGame(participants, barriers, i, j);
            }
        }
    }

    private static void logicGame(MovingEntity[] participants, Barrier[] barriers, int i, int j) {
        int stepCompetition = i;
        int countWin = 0;
        while (stepCompetition>=0) {
            if  (barriers[i - stepCompetition].isCanDo(participants[j])) {
                countWin++;
            }
            stepCompetition--;
        }
        if (i == 0 || (countWin == i+1 && barriers[i-1].isCanDo(participants[j]))) {
            barriers[i].isDone(participants[j]);
            System.out.println();


        } else if (!barriers[i].isCanDo(participants[j]) && countWin == i) {
            barriers[i].isDone(participants[j]);
            System.out.println();
        }
    }
}
