package ru.argos.fuzzycontroller.algorithms;

import ru.argos.fuzzycontroller.algorithms.Larsen.Rule;
import ru.argos.fuzzycontroller.operators.Operator;
import ru.argos.fuzzycontroller.variables.Term;

import java.util.List;

/**
 * Статический класс, описания системы нечеткого логического вывода по алгоритму Ларсена.
 *
 * @author a.k.pohresnyi
 */
public class LarsenBuilder {

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
    public static Larsen of(List<Rule> rules) {
        return parameters -> x -> rules.stream()
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
    public static Rule rule(Operator condition, Term conclusion) {
        return parameters -> x -> condition.calc(parameters) * conclusion.calc(x);
    }
}
