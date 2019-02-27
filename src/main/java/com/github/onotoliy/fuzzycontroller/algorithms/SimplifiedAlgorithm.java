package com.github.onotoliy.fuzzycontroller.algorithms;

import java.util.Map;

/**
 * Упрощенный алгоритм нечеткого вывода.
 *
 * @author Anatoliy Pokhresnyi
 */
public interface SimplifiedAlgorithm {

    /**
     * Приведение к четкости. Преобразование нечеткого набора выводов в четкое
     * число.
     *
     * @param parameters Входные параметры, где {@link Map.Entry#getKey()} -
     *                  название лингвистической переменной, а
     *                  {@link Map.Entry#getValue()} - входное значение.
     * @return Четкое значение.
     */
    double clarity(Map<String, Double> parameters);

    /**
     * Нечеткое правило логического вывода.
     *
     * @author Anatoliy Pokhresnyi
     */
    interface Rule {

        /**
         * Вычисляет степень истинности условия нечеткого правила.
         *
         * @param parameters Входные параметры, где {@link Map.Entry#getKey()} -
         *                  название лингвистической переменной, а
         *                  {@link Map.Entry#getValue()} - входное значение.
         * @return Степень истинности условния.
         */
        double condition(Map<String, Double> parameters);

        /**
         * Вес правила.
         *
         * @return Вес правила.
         */
        double conclusion();
    }
}
