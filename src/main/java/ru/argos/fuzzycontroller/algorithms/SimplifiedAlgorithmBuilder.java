package ru.argos.fuzzycontroller.algorithms;

import ru.argos.fuzzycontroller.algorithms.SimplifiedAlgorithm.Rule;
import ru.argos.fuzzycontroller.operators.Operator;

import java.util.List;
import java.util.Map;

/**
 * Статический класс, описания системы нечеткого логического вывода по
 * упрощенному алгоритму.
 *
 * @author a.k.pohresnyi
 */
public final class SimplifiedAlgorithmBuilder {

    /**
     * Приватный конструктор.
     */
    private SimplifiedAlgorithmBuilder() {

    }

    /**
     * Создает систему нечеткого логического вывода по упрощенному алгоритму.
     *
     * @param rules База нечетких правил.
     * @return Система нечеткого логического вывода по упрощенному алгоритму.
     */
    public static SimplifiedAlgorithm of(final List<Rule> rules) {
        return parameters -> {
            double numerator = 0.0, denominator = 0.0;

            for (Rule rule : rules) {
                numerator += rule.condition(parameters) * rule.conclusion();
                denominator += rule.conclusion();
            }

            return numerator / denominator;
        };
    }

    /**
     * Создает нечеткое правило.
     *
     * @param condition Условие нечеткого правила.
     * @param conclusion Вес нечетного правила.
     * @return Нечеткое правило.
     */
    public static Rule rule(final Operator condition, final double conclusion) {
        return new Rule() {
            @Override
            public double condition(final Map<String, Double> parameters) {
                return condition.calc(parameters);
            }

            @Override
            public double conclusion() {
                return conclusion;
            }
        };
    }
}
