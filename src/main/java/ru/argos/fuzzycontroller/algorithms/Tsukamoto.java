package ru.argos.fuzzycontroller.algorithms;

import java.util.Map;

/**
 * Нечекий алгоритм вывода Цукамото.
 *
 * @author a.k.pohresnyi
 */
public interface Tsukamoto {

    /**
     * Приведение к четкости. Преобразование нечеткого набора выводов в четкое число.
     *
     * @param parameters Входные параметры, где {@link Map.Entry#getKey()} - название лингвистической переменной,
     *                   а {@link Map.Entry#getValue()} - входное значение.
     * @return Четкое значение.
     */
    double clarity(Map<String, Double> parameters);

    /**
     * Нечеткое правило.
     *
     * @author a.k.pohresnyi
     */
    interface Rule {

        /**
         * Вычисляет степень истинности условия нечеткого правила.
         *
         * @param parameters Входные параметры, где {@link Map.Entry#getKey()} - название лингвистической переменной,
         *                   а {@link Map.Entry#getValue()} - входное значение.
         * @return Степень истинности условния.
         */
        double condition(Map<String, Double> parameters);

        /**
         * Вычисляет входное заначение у закличения. По соглащению функция, которая задана в заключении является монотонной.
         *
         * @param probability Значение функции принадлежности, для которого необходимо най входное значение.
         * @param delta Допустимая погрешность.
         * @return Входное значение
         */
        double conclusion(double probability, double delta);
    }
}
