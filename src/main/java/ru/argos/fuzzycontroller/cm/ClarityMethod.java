package ru.argos.fuzzycontroller.cm;

import ru.argos.fuzzycontroller.mf.MembershipFunction;

/**
 * Функция дефаззификации.
 *
 * @author a.k.pohresnyi
 */
public interface ClarityMethod {

    /**
     * Метод дефаззификации.
     *
     * @param mf Функция принадлежности.
     * @return Результат функции дефаззификации.
     */
    double calc(MembershipFunction mf);
}
