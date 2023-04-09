package ru.slava.mathematics.expression;

import java.util.Random;

public class Expression8_2 {
    private float sin;
    private String line;
    private int[] ints;

    public Expression8_2(float sin, String line, int[] ints) {
        this.sin = sin;
        this.line = line;
        this.ints = ints;
    }

    public Expression8_2() {
    }

    public float getSin() {
        return sin;
    }

    public String getLine() {
        return line;
    }

    public int[] getInts() {
        return ints;
    }

    public Expression8_2 generateExpression8_2() {
        double sin, cos;
        int side, base, height;
        Random random = new Random();

        sin = ((double) (random.nextInt(9) + 1)) / 10;
        cos = 1.0 - sin;

        do {
            side = random.nextInt(100) + 1;
            base = (int) (cos * side);
            height = random.nextInt(100) + 1;
        } while (
                sin !=  (double) height / (double) side
                && side + (base * 2) < side
                && side % 2 != 0
                && base % 2 != 0
                && cos != (double) ((double) base / (double) side)
        );

//        } while (
//                        sin != (double) height / (double) side
//                        &&
//                        cos != ((double) base / 2.0) / (double) side && side + side < base
//                        &&
//                        base % 2 != 0
//                        &&
//                        side % 2 != 0

//        );

//        do {
//            side = random.nextInt(100) + 1;
//            angle = random.nextInt(89) + 1;
//            sin = Math.sin(Math.toRadians(angle));
//            height = side * sin;
//            base = 2 * height / sin;
//        } while (side + side < base);

//        side = Math.random() * 100 + 1;
//        angle = Math.random() * 89 + 1;
//        double sine = Math.sin(Math.toRadians(angle));
//        height = side * sine;
//        base = 2 * height / sine;
//        if (base <= side) {
//            generateExpression8_2();
//        }
//        else {
//            return new Expression8_2((float)sine, Double.toString(sine), new int[]{(int) side, (int) base});
//        }
        return new Expression8_2((float) sin, Double.toString(sin), new int[]{side, base * 2, height});
    }
}
