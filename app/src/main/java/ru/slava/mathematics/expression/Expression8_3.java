package ru.slava.mathematics.expression;

import java.util.Random;

public class Expression8_3 {
    private int x;
    private int y;
    private String line;

    public Expression8_3(int x, int y, String line) {
        this.x = x;
        this.y = y;
        this.line = line;
    }

    public Expression8_3() {
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getLine() {
        return line;
    }

    public Expression8_3 generateExpression1(int left, int right) {
        int b, y, k, x;

        Random random = new Random();

        b = random.nextInt(right) - left;
        x = random.nextInt(right) - left;

        do {
            k = random.nextInt(right) - left;
        } while (k == 0);

        y = x * b + k;

        if (k > 0)
            return new Expression8_3(x, y, "y = " + b + "x" + " + " + k);
        else
            return new Expression8_3(x, y, "y = " + b + "x" + " " + k);
    }

    public Expression8_3 generateExpression2(int left, int right) {
        int b, y, k, x;

        Random random = new Random();

        b = random.nextInt(right) - left;
        x = random.nextInt(right) - left;

        do {
            k = random.nextInt(right) - left;
        } while (k == 0);

        y = x * b + k;

        if (k > 0)
            return new Expression8_3(x, y, "y = " + b + "x" + " + " + k);
        else
            return new Expression8_3(x, y, "y = " + b + "x" + " " + k);
    }
}
