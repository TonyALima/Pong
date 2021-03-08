package tests;

public class TestRandom {

    public static void main(String[] args) {
        double fracAngle;
        double angle;

        for (int i = 1; i < 5; i++) {
            if ((i % 2) == 0) {
                do {
                    fracAngle = Math.random();
                } while (fracAngle < (1f / 4) || fracAngle > (3f / 4));
            }else {
                do {
                    fracAngle = (Math.random() + 1);
                } while (fracAngle > (7f / 4) || fracAngle < (5f / 4));
            }

            angle = fracAngle * Math.PI;

            System.out.print("angle: ");
            System.out.println(Math.toDegrees(angle));
            System.out.print("cos: ");
            System.out.println(Math.cos(angle));
            System.out.print("sin: ");
            System.out.println(Math.sin(angle));
            System.out.println("");
        }
    }
}
