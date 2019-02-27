package ru.argos.fuzzycontroller.algorithms;

import ru.argos.fuzzycontroller.algorithms.Mamdani.Rule;
import ru.argos.fuzzycontroller.operators.Operator;
import ru.argos.fuzzycontroller.variables.Term;

import java.util.List;

/**
 * Статический класс, описывающий систему нечеткого логического вывода по
 * алгоритму Мамдани.
 *
 * @author Anatoliy Pokhresnyi
 */
public final class MamdaniBuilder {

    /**
     * Приватный конструктор.
     */
    private MamdaniBuilder() {

    }

    /**
     * Создает систему нечеткого логического вывода по алгоритму Мамдани.
     *
     * @param rules База нечетких правил.
     * @return Система нечеткого логического вывода по алгоритму Мамдани.
     */
    public static Mamdani of(final List<Rule> rules) {
        return parameters ->
                x -> rules.stream()
                          .map(rule -> rule.compose(parameters))
                          .map(mf -> mf.calc(x))
                          .max(Double::compareTo)
                          .orElseThrow(IllegalAccessError::new);
    }

    /**
     * Создает нечеткое правило.
     *
     * @param condition Условие нечеткого правила.
     * @param conclusion Терм выходной лингвистической переменной.
     * @return Нечеткое правило.
     */
    public static Rule rule(final Operator condition, final Term conclusion) {
        return parameters ->
                x -> Double.min(condition.calc(parameters), conclusion.calc(x));
    }
}
