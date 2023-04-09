package ru.slava.mathematics.equation;

import java.util.Random;

public class QuadraticEquation {
    private String line;
    private int[] ints = new int[2];

    public QuadraticEquation() {

    }

    public QuadraticEquation(String line, int[] ints) {
        this.line = line;
        this.ints = ints;
    }

    public String getLine() {
        return line;
    }

    public int[] getInts() {
        return ints;
    }

    public QuadraticEquation generateQuadraticEquation(int left, int right) {
        Random random = new Random();
        int a, b, c;
        double D, x1, x2;

        do {
            a = random.nextInt(right) - left;
        } while (a == 0);

        do {
            b = random.nextInt(right) - left;
            c = random.nextInt(right) - left;
            D = b * b - 4 * a * c;
        } while (D < 0);

        x1 = (-b + Math.sqrt(D)) / (2 * a);
        x2 = (-b - Math.sqrt(D)) / (2 * a);

        if (x1 != (int) x1 || x2 != (int) x2) {
            return generateQuadraticEquation(left, right);
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(a).append("x").append("²");
            if (b > 0)
                stringBuilder.append(" + ").append(b);
            else if (b < 0)
                stringBuilder.append(" ").append(b);
            stringBuilder.append("x");
            if (c > 0)
                stringBuilder.append(" + ").append(c);
            else if (c < 0)
                stringBuilder.append(" ").append(c);
            stringBuilder.append(" = 0");
            return new QuadraticEquation(stringBuilder.toString(), new int[]{(int) x1, (int) x2});
        }

    }
}
