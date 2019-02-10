package ru.argos.fuzzycontroller.mf;

/**
 * Функция приндлежности.
 *
 * @author a.k.pohresnyi
 */
@FunctionalInterface
public interface MembershipFunction {

    /**
     * Метод построения функции принадлежности.
     *
     * @param x Величина, относительно которой формулируется функция
     *         принадлежности.
     * @return Результат функции принадлежности.
     */
    double calc(double x);
}
