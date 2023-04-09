package ru.slava.mathematics.expression;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class Expression8_1 {
    private float x;
    private String line;

    public Expression8_1(float x, String line) {
        this.x = x;
        this.line = line;
    }

    public Expression8_1() {

    }

    public float getX() {
        return x;
    }

    public String getLine() {
        return line;
    }

    public Expression8_1 generateExpression8_1(int border){
        Random random = new Random();

        while (true) {
            int n = random.nextInt(border) + 1;
            double sqrt = Math.sqrt(n);

            BigDecimal bd = new BigDecimal(sqrt).setScale(1, RoundingMode.HALF_UP);
            float result = bd.floatValue();

            if (sqrt % 1 != 0) {
                return new Expression8_1(result, Integer.toString(n));
            }
        }
    }
}
