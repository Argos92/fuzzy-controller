package ru.argos.fuzzycontroller.variables;

import ru.argos.fuzzycontroller.operators.Operator;

/**
 * Лингвистическая переменная.
 *
 * @author a.k.pohresnyi
 */
public interface Variable {

    /**
     * Создает из терма лингвистической переменной логический оператор.
     *
     * @param term Название терма.
     * @return Логический оператор.
     */
    Operator is(String term);

    /**
     * Получает терм лигвистической переменной
     *
     * @param term Название терма.
     * @return Терм лигвистической переменной.
     */
    Term get(String term);

    /**
     * Название лигвистической переменной.
     *
     * @return Название лигвистической переменной.
     */
    String name();

    /**
     * Начало интервала лигвистической переменной.
     *
     * @return Начало интервала лигвистической переменной.
     */
    double startWith();

    /**
     * Конец интервала лигвистической переменной.
     *
     * @return Конец интервала лигвистической переменной.
     */
    double endWith();

    /**
     * Шаг.
     *
     * @return Шаг.
     */
    double step();
}
