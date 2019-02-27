package com.github.onotoliy.fuzzycontroller.algorithms;

import com.github.onotoliy.fuzzycontroller.operators.Operator;
import com.github.onotoliy.fuzzycontroller.variables.Term;
import com.github.onotoliy.fuzzycontroller.algorithms.Tsukamoto.Rule;

import java.util.List;
import java.util.Map;

/**
 * Статический класс, описывающий систему нечеткого логического вывода по
 * алгоритму Цукамото.
 *
 * @author Anatoliy Pokhresnyi
 */
public final class TsukamotoBuilder {

    /**
     * Погрешность вычислений.
     */
    private static final double DELTA = 0.001;

    /**
     * Приватный конструктор.
     */
    private TsukamotoBuilder() {

    }

    /**
     * Создает систему нечеткого логического вывода по алгоритму Цукамото.
     *
     * @param rules База нечетких правил.
     * @return Система нечеткого логического вывода по алгоритму Цукамото.
     */
    public static Tsukamoto of(final List<Rule> rules) {
        return of(rules, DELTA);
    }

    /**
     * Создает систему нечеткого логического вывода по алгоритму Цукамото.
     *
     * @param rules База нечетких правил.
     * @param delta Погрешность вычислений.
     * @return Система нечеткого логического вывода по алгоритму Цукамото.
     */
    public static Tsukamoto of(final List<Rule> rules, final double delta) {
        return parameters -> {
            double numerator = 0.0, denominator = 0.0;

            for (Rule rule : rules) {
                double probability = rule.condition(parameters);
                double x = rule.conclusion(probability, delta);

                numerator += probability * x;
                denominator += probability;
            }

            return numerator / denominator;
        };
    }

    /**
     * Создает нечеткое правило.
     *
     * @param condition Условие нечеткого правила.
     * @param conclusion Терм выходной лингвистической переменной.
     * @return Нечеткое правило.
     */
    public static Rule rule(final Operator condition, final Term conclusion) {
        return new Rule() {
            @Override
            public double condition(final Map<String, Double> parameters) {
                return condition.calc(parameters);
            }

            @Override
            public double conclusion(final double probability,
                                     final double delta) {
                double startX = conclusion.calc(
                        conclusion.variable().startWith());
                double endX = conclusion.calc(
                        conclusion.variable().endWith());

                return search(
                        probability,
                        conclusion.variable().startWith(),
                        conclusion.variable().endWith(),
                        conclusion,
                        delta,
                        startX > endX);
            }
        };
    }

    /**
     * Вычисляет входное значение.
     *
     * @param expected Значение функции принадлежности, для которого необходимо
     *                найти входное значение.
     * @param startWith Начало интервала лингвистической переменной.
     * @param endWith Конец интервала лингвистической переменной.
     * @param term Терм линвистической переменной. По соглащению заданная
     *             функция принадлежности является монотонной.
     * @param delta Допустимая погрешность.
     * @param descending Убывающий график.
     * @return Входное значение
     */
    private static double search(final double expected,
                                 final double startWith,
                                 final double endWith,
                                 final Term term,
                                 final double delta,
                                 final boolean descending) {
        double half = startWith + Math.abs(startWith - endWith) / 2;
        double actual = term.calc(half);

        if (Math.abs(actual - expected) <= delta) {
            return half;
        }

        double sW = actual > expected
                ? descending ? half : startWith
                : descending ? startWith : half;
        double eW = actual > expected
                ? descending ? endWith : half
                : descending ? half : endWith;

        return search(expected, sW, eW, term, delta, descending);
    }
}
