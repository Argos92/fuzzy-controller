package com.github.onotoliy.fuzzycontroller.cm;

import com.github.onotoliy.fuzzycontroller.mf.MembershipFunction;

/**
 * Метод дефаззификации.
 *
 * @author Anatoliy Pokhresnyi
 */
public interface ClarityMethod {

    /**
     * Дефаззификация. Преобразование функции принадлежности в четкое число.
     *
     * @param mf Функция принадлежности.
     * @return Результат дефаззификации.
     */
    double calc(MembershipFunction mf);
}
