package ru.argos.fuzzycontroller.algorithms;

import ru.argos.fuzzycontroller.mf.MembershipFunction;

import java.util.Map;

/**
 * Нечекий алгоритм вывода Ларсена.
 *
 * @author a.k.pohresnyi
 */
public interface Larsen {

    /**
     * Композиция. Нахождение функции принадлежности для выходной
     * лингвистической переменной.
     *
     * @param parameters Входные параметры, где {@link Map.Entry#getKey()} -
     *                  название лингвистической переменной, а
     *                  {@link Map.Entry#getValue()} - входное значение.
     * @return Функция принадлежности.
     */
    MembershipFunction compose(Map<String, Double> parameters);

    /**
     * Нечеткое правило.
     *
     * @author a.k.pohresnyi
     */
    interface Rule {

        /**
         * Композиция. Нахождение усеченной функции принадлежности правила.
         *
         * @param parameters Входные параметры, где {@link Map.Entry#getKey()} -
         *                  название лингвистической переменной, а
         *                  {@link Map.Entry#getValue()} - входное значение.
         * @return Функция принадлежности.
         */
        MembershipFunction compose(Map<String, Double> parameters);
    }
}
