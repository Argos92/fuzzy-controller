package ru.argos.fuzzycontroller.cm;

import java.util.List;

/**
 * Функция дефаззификации.
 *
 * @author a.k.pohresnyi
 */
public interface ClarityMethod {

    /**
     * Метод дефаззификации.
     *
     * @param variables Нечеткое множество.
     * @return Результат метода дефаззификации.
     */
    double calc(List<ClarityVariable> variables);
}
