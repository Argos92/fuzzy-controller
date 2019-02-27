package com.github.onotoliy.fuzzycontroller.algorithms;

import com.github.onotoliy.fuzzycontroller.algorithms.Larsen.Rule;
import com.github.onotoliy.fuzzycontroller.operators.Operator;
import com.github.onotoliy.fuzzycontroller.variables.Term;

import java.util.List;

/**
 * Статический класс, описывающий систему нечеткого логического вывода по
 * алгоритму Ларсена.
 *
 * @author Anatoliy Pokhresnyi
 */
public final class LarsenBuilder {

    /**
     * Приватный конструктор.
     */
    private LarsenBuilder() {

    }

    /**
     * Создает систему нечеткого логического вывода по алгоритму Ларсена.
     *
     * @param rules База нечетких правил.
     * @return Система нечеткого логического вывода по алгоритму Ларсена.
     */
    public static Larsen of(final List<Rule> rules) {
        return parameters ->
                x -> rules.stream()
                          .map(rule -> rule.compose(parameters))
                          .map(mf -> mf.calc(x))
                          .max(Double::compareTo)
                          .orElseThrow(IllegalArgumentException::new);
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
                x -> condition.calc(parameters) * conclusion.calc(x);
    }
}
