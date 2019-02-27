package ru.argos.fuzzycontroller.variables;

/**
 * Терм лингвистической переменной.
 *
 * @author Anatoliy Pokhresnyi
 */
public interface Term {

    /**
     * Название терма.
     *
     * @return Название терма.
     */
    String name();

    /**
     * Вычисляет значение функции принадлежности.
     *
     * @param x Величина, относительно которой формулируется функция
     *         принадлежности.
     * @return Результат функции принадлежности.
     */
    double calc(double x);

    /**
     * Лингвистическая переменная, которой принадлежит терм.
     *
     * @return Лингвистическая переменная.
     */
    Variable variable();
}
