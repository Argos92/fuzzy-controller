package ru.argos.fuzzycontroller.cm;

import java.util.function.BiFunction;

/**
 * Статический класс, описывающий стандартные методы дефаззификации.
 *
 * @author Anatoliy Pokhresnyi
 */
public final class ClarityMethodBuilder {

    /**
     * Начало интервала.
     */
    private static final double START_WITH = 0.0;

    /**
     * Конец интервала.
     */
    private static final double END_WITH = 1.0;

    /**
     * Шаг.
     */
    private static final double STEP = 0.0001;

    /**
     * Приватный конструктор.
     */
    private ClarityMethodBuilder() {

    }

    /**
     * Метод центра тяжести.
     *
     * @return Метод дефаззификации.
     */
    public static ClarityMethod centerOfGravity() {
        return centerOfGravity(START_WITH, END_WITH, STEP);
    }

    /**
     * Метод центра тяжести.
     *
     * @param startWith Начало интервала.
     * @param endWith Конец интервала.
     * @param step Шаг.
     * @return Метод дефаззификации.
     */
    public static ClarityMethod centerOfGravity(final double startWith,
                                                final double endWith,
                                                final double step) {
        return mf -> {
            double numerator = 0.0, denominator = 0.0;

            for (double x = startWith; x <= endWith; x += step) {
                numerator += x * mf.calc(x);
                denominator += x;
            }

            return numerator / denominator;
        };
    }

    /**
     * Метод среднего максимума.
     *
     * @return Метод дефаззификации.
     */
    public static ClarityMethod averageMaximum() {
        return averageMaximum(START_WITH, END_WITH, STEP);
    }

    /**
     * Метод среднего максимума.
     *
     * @param startWith Начало интервала.
     * @param endWith Конец интервала.
     * @param step Шаг.
     * @return Метод дефаззификации.
     */
    public static ClarityMethod averageMaximum(final double startWith,
                                               final double endWith,
                                               final double step) {
        return mf -> {
            double count = 0.0, amount = 0.0, max = 0.0;

            for (double x = startWith; x <= endWith; x += step) {
                double probability = mf.calc(x);

                if (max == probability) {
                    count += 1.0;
                    amount += x;
                }

                if (max < probability) {
                    count = 1.0;
                    amount = x;
                    max = probability;
                }
            }

            return (1.0 / count) * amount;
        };
    }

    /**
     * Метод левого максимума.
     *
     * @return Метод дефаззификации.
     */
    public static ClarityMethod leftMaximum() {
        return leftMaximum(START_WITH, END_WITH, STEP);
    }

    /**
     * Метод левого максимума.
     *
     * @param startWith Начало интервала.
     * @param endWith Конец интервала.
     * @param step Шаг.
     * @return Метод дефаззификации.
     */
    public static ClarityMethod leftMaximum(final double startWith,
                                            final double endWith,
                                            final double step) {
        return leftOrRightMaximum(startWith, endWith, step, Double::min);
    }

    /**
     * Метод правого максимума.
     *
     * @return Метод дефаззификации.
     */
    public static ClarityMethod rightMaximum() {
        return rightMaximum(START_WITH, END_WITH, STEP);
    }

    /**
     * Метод правого максимума.
     *
     * @param startWith Начало интервала.
     * @param endWith Конец интервала.
     * @param step Шаг.
     * @return Метод дефаззификации.
     */
    public static ClarityMethod rightMaximum(final double startWith,
                                             final double endWith,
                                             final double step) {
        return leftOrRightMaximum(startWith, endWith, step, Double::max);
    }

    /**
     * Метод левого/правого максимума.
     *
     * @param startWith Начало интервала.
     * @param endWith Конец интервала.
     * @param step Шаг.
     * @param function Функция выбора значения.
     * @return Метод дефаззификации.
     */
    private static ClarityMethod
        leftOrRightMaximum(final double startWith,
                           final double endWith,
                           final double step,
                           final BiFunction<Double, Double, Double> function) {

        return mf -> {
            double max = 0.0, result = 0.0;

            for (double x = startWith; x < endWith; x += step) {
                double probability = mf.calc(x);
                if (max == probability) {
                    result = function.apply(x, result);
                }

                if (max < probability) {
                    max = probability;
                    result = x;
                }
            }

            return result;
        };
    }
}
