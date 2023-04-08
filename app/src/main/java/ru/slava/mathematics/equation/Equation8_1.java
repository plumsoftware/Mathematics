package ru.slava.mathematics.equation;

import java.util.Random;

public class Equation8_1 {
    private String line;
    private int[] ints;

    public Equation8_1(String line, int[] ints) {
        this.line = line;
        this.ints = ints;
    }

    public String getLine() {
        return line;
    }

    public int[] getInts() {
        return ints;
    }

    public static Equation8_1 generateEquation8_1(int left, int right) {
        int a, b, c, d, x, y, z;
        boolean w = true;
        Random random = new Random();

        do {
            // Генерируем случайные значения коэффициентов a, b, c, d
            // в диапазоне от 1 до 10
            a = random.nextInt(right) - left + 1;
            b = random.nextInt(right) - left + 1;
            c = random.nextInt(right) - left + 1;
            d = random.nextInt(right) - left + 1;

            // Вычисляем произведение a*c
            z = a * c;
        } while (z == 0); // Проверяем, что произведение не равно нулю

        do {
            // Генерируем случайное целое число x
            // в диапазоне от -10 до 10
            x = random.nextInt(right) - left + 1;

            // Вычисляем значение y
            y = (a * x + b) * (c * x + d);

            // Проверяем, является ли y корнем уравнения
            if (y == 0) {
                w = false;
            }
        } while (w);


        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("(")
                .append(a)
                .append("x ");
        if (b < 0)
            stringBuilder.append(b);
        else
            stringBuilder.append("+ ").append(b);
        stringBuilder
                .append(")")
                .append("(")
                .append(c)
                .append("x ");
        if (d < 0)
            stringBuilder.append(d);
        else
            stringBuilder.append("+ ").append(d);
        stringBuilder
                .append(") = ").append((a * x + b) * (c * x + d));

        return new Equation8_1(stringBuilder.toString(), new int[]{x});
    }
}
