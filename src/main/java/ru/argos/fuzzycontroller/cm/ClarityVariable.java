package ru.argos.fuzzycontroller.cm;

/**
 * Преобразованое правило в нечеткую переменую.
 *
 * @author a.k.pohresnyi
 */
public class ClarityVariable {

    /**
     * Вес правила.
     */
    private final double weight;

    /**
     * Значение функции принадлежности.
     */
    private final double probability;

    /**
     * Нечеткая переменная.
     *
     * @param probability Значение функции принадлежности.
     * @param weight Вес правила.
     */
    public ClarityVariable(double probability, double weight) {
        this.probability = probability;
        this.weight = weight;
    }

    /**
     * Получает значение функции принадлежности.
     *
     * @return Значение функции принадлежности.
     */
    public double getProbability() {
        return probability;
    }

    /**
     * Получает вес правила.
     * @return Вес правила.
     */
    public double getWeight() {
        return weight;
    }
}
