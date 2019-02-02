package ru.argos.fuzzycontroller.algorithms;

import ru.argos.fuzzycontroller.algorithms.Sugeno.Rule;
import ru.argos.fuzzycontroller.operators.Operator;

import java.util.List;
import java.util.Map;

import static ru.argos.fuzzycontroller.utils.Utils.getOrThrow;

/**
 * Статический класс, описания системы нечеткого логического вывода по аглогитму Сугено.
 *
 * @author a.k.pohresnyi
 */
public class SugenoBuilder {

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
    public static Sugeno of(List<Rule> rules) {
        return parameters -> {
            double numerator = 0.0, denominator = 0.0;

            for (Rule rule : rules) {
                numerator += rule.condition(parameters) * rule.conclusion(parameters);
                denominator += rule.conclusion(parameters);
            }

            return numerator / denominator;
        };
    }

    /**
     * Создает нечеткое правило.
     *
     * @param condition Условие нечеткого правила.
     * @return Нечеткое правило.
     */
    public static Rule rule(Operator condition) {
        return new Rule() {
            @Override
            public double condition(Map<String, Double> parameters) {
                return condition.calc(parameters);
            }

            @Override
            public double conclusion(Map<String, Double> parameters) {
                return condition.getTerms()
                                .stream()
                                .mapToDouble(term -> {
                                    double x = getOrThrow(parameters, term.variable().name());

                                    return x * term.calc(x);
                                })
                                .sum();
            }
        };
    }
}
