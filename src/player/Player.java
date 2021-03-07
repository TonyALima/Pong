package player;

public class Player {

    private static int numberPlayer, record, currentScore;
    private static String name;

    // Setters

    public static void setNumberPlayer(int numberPlayer) {
        Player.numberPlayer = numberPlayer;
    }

    public static void setRecord(int record) {
        Player.record = record;
    }

    public static void setCurrentScore(int currentScore) {
        Player.currentScore = currentScore;
    }

    public static void setName(String name) {
        Player.name = name;
    }
}
