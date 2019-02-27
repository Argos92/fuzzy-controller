package com.github.onotoliy.fuzzycontroller.mf;

/**
 * Функция принадлежности.
 *
 * @author Anatoliy Pokhresnyi
 */
public interface MembershipFunction {

    /**
     * Вычисляет результат функции принадлежности.
     *
     * @param x Входное значение.
     * @return Результат функции принадлежности.
     */
    double calc(double x);
}
