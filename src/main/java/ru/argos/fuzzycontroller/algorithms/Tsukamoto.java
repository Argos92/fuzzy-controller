package ru.argos.fuzzycontroller.algorithms;

import java.util.Map;

/**
 * Нечеткий алгоритм логического вывода. Алгоритм Цукамото.
 *
 * @author Anatoliy Pokhresnyi
 */
public interface Tsukamoto {

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
         * Вычисляет входное значение у заключения. По соглащению функция,
         * которая задана в заключении, является монотонной.
         *
         * @param probability Значение функции принадлежности, для которого
         *                    необходимо найти входное значение.
         * @param delta Допустимая погрешность.
         * @return Входное значение
         */
        double conclusion(double probability, double delta);
    }
}
