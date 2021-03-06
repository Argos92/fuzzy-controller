package com.github.onotoliy.fuzzycontroller.algorithms;

import com.github.onotoliy.fuzzycontroller.mf.MembershipFunction;

import java.util.Map;

/**
 * Нечеткий алгоритм логического вывода. Алгоритм Ларсена.
 *
 * @author Anatoliy Pokhresnyi
 */
public interface Larsen {

    /**
     * Композиция. Нахождение усеченной функции принадлежности для выходной
     * лингвистической переменной.
     *
     * @param parameters Входные параметры, где {@link Map.Entry#getKey()} -
     *                  название лингвистической переменной, а
     *                  {@link Map.Entry#getValue()} - входное значение.
     * @return Усеченная функция принадлежности.
     */
    MembershipFunction compose(Map<String, Double> parameters);

    /**
     * Нечеткое правило логического вывода.
     *
     * @author Anatoliy Pokhresnyi
     */
    interface Rule {

        /**
         * Композиция. Нахождение усеченной функции принадлежности нечеткого
         * правила.
         *
         * @param parameters Входные параметры, где {@link Map.Entry#getKey()} -
         *                  название лингвистической переменной, а
         *                  {@link Map.Entry#getValue()} - входное значение.
         * @return Усеченная функция принадлежности.
         */
        MembershipFunction compose(Map<String, Double> parameters);
    }
}
