package ru.argos.fuzzycontroller.algorithms;

import ru.argos.fuzzycontroller.algorithms.Sugeno.Rule;
import ru.argos.fuzzycontroller.operators.Operator;

import java.util.List;
import java.util.Map;

/**
 * Статический класс, описания системы нечеткого логического вывода по аглогитму
 * Сугено.
 *
 * @author a.k.pohresnyi
 */
public final class SugenoBuilder {

    /**
     * Приватный конструктор.
     */
    private SugenoBuilder() {

    }

    /**
     * Создает систему нечеткого логического вывода по алгоритму Сугено.
     *
     * @param rules База нечетких правил.
     * @return Система нечеткого логического вывода по алгоритму Сугено.
     */
    public static Sugeno of(final List<Rule> rules) {
        return parameters -> {
            double numerator = 0.0, denominator = 0.0;

            for (Rule rule : rules) {
                double conclusion = rule.conclusion(parameters);

                numerator += rule.condition(parameters) * conclusion;
                denominator += conclusion;
            }

            return numerator / denominator;
        };
    }

    /**
     * Создает нечеткое правило.
     *
     * @param condition Условие нечеткого правила.
     * @param conclusion Заключение нечеткого правила.
     * @return Нечеткое правило.
     */
    public static Rule rule(final Operator condition,
                            final Operator conclusion) {
        return new Rule() {
            @Override
            public double condition(final Map<String, Double> parameters) {
                return condition.calc(parameters);
            }

            @Override
            public double conclusion(final Map<String, Double> parameters) {
                return conclusion.calc(parameters);
            }
        };
    }
}
