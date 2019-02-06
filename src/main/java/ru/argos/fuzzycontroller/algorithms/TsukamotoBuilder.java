package ru.argos.fuzzycontroller.algorithms;

import ru.argos.fuzzycontroller.algorithms.Tsukamoto.Rule;
import ru.argos.fuzzycontroller.operators.Operator;
import ru.argos.fuzzycontroller.variables.Term;

import java.util.List;
import java.util.Map;

/**
 * Статический класс, описания системы нечеткого логического вывода по аглогитму Цукамото.
 *
 * @author a.k.pohresnyi
 */
public class TsukamotoBuilder {

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
    public static Tsukamoto of(List<Rule> rules) {
        return of(rules, 0.001);
    }

    /**
     * Создает систему нечеткого логического вывода по алгоритму Цукамото.
     *
     * @param rules База нечетких правил.
     * @param delta Погрешность вычислений.
     * @return Система нечеткого логического вывода по алгоритму Цукамото.
     */
    public static Tsukamoto of(List<Rule> rules, double delta) {
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
    public static Rule rule(Operator condition, Term conclusion) {
        return new Rule() {
            @Override
            public double condition(Map<String, Double> parameters) {
                return condition.calc(parameters);
            }

            @Override
            public double conclusion(double probability, double delta) {
                double startX = conclusion.calc(conclusion.variable().startWith());
                double endX = conclusion.calc(conclusion.variable().endWith());

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
     * @param expected Значение функции принадлежности, для которого необходимо най входное значение.
     * @param startWith Начала интервала лингвистической переменной.
     * @param endWith Конец интервала лингвистической переменной.
     * @param term Терм линвистической переменной. По соглащению функция принадлежности заданая является монотонной.
     * @param delta Допустимая погрешность.
     * @param descending Убывающий график.
     * @return Входное значение
     */
    private static double search(double expected, double startWith, double endWith, Term term, double delta, boolean descending) {
        double half = startWith + Math.abs(startWith - endWith) / 2;
        double actual = term.calc(half);

        if (Math.abs(actual - expected) <= delta) {
            return half;
        }

        return actual > expected
                ? search(expected, descending ? half : startWith, descending ? endWith : half, term, delta, descending)
                : search(expected, descending ? startWith : half, descending ? half : endWith, term, delta, descending);
    }
}
