package ru.argos.fuzzycontroller.variables;

import ru.argos.fuzzycontroller.operators.Operator;

/**
 * Лингвистическая переменная.
 *
 * @author Anatoliy Pokhresnyi
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
     * Получает терм лингвистической переменной.
     *
     * @param term Название терма.
     * @return Терм лингвистической переменной.
     */
    Term get(String term);

    /**
     * Название лингвистической переменной.
     *
     * @return Название лингвистической переменной.
     */
    String name();

    /**
     * Начало интервала лингвистической переменной.
     *
     * @return Начало интервала лингвистической переменной.
     */
    double startWith();

    /**
     * Конец интервала лингвистической переменной.
     *
     * @return Конец интервала лингвистической переменной.
     */
    double endWith();

    /**
     * Шаг.
     *
     * @return Шаг.
     */
    double step();
}
