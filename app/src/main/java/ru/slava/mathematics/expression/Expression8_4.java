package ru.slava.mathematics.expression;

import java.util.Random;

public class Expression8_4 {
    private int x;
    private String line;

    public Expression8_4(int x, String line) {
        this.x = x;
        this.line = line;
    }

    public Expression8_4() {
    }

    public int getX() {
        return x;
    }

    public String getLine() {
        return line;
    }

    public Expression8_4 generateExpression(int left, int right){
        Random random = new Random();
        int totalArea = random.nextInt(right) + left;

        // Вычисляем площадь зерновых и зернобобовых культур
        int grainArea = totalArea * 7 / 9;
        int legumeArea = totalArea - grainArea;

        // Генерируем текст задания
        String task = "Площадь земельного участка, отведенных под посадку сельскохозяйственных культур, составляет " +
                totalArea + " га и распределена между зерновыми и зернобобовыми культурами в соотношении 7:2 " +
                "соответственно. Сколько гектаров занимают зернобобовые культуры?";

        return new Expression8_4(legumeArea, task);
    }
}
