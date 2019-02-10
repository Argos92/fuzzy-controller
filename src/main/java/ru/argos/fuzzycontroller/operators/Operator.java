package ru.argos.fuzzycontroller.operators;

import ru.argos.fuzzycontroller.variables.Term;

import java.util.List;
import java.util.Map;

/**
 * Логический оператор.
 *
 * @author a.k.pohresnyi
 */
public interface Operator {

    /**
     * Приведение к четкости. Преобразование логического оператора в четкое
     * число.
     *
     * @param parameters Входные параметры, где {@link Map.Entry#getKey()} -
     *                  название лингвистической переменной, а
     *                  {@link Map.Entry#getValue()} - входное значение.
     * @return Четкое значение.
     */

    double calc(Map<String, Double> parameters);

    /**
     * Получает термы содержащиеся в логическом операторе.
     *
     * @return Термы содержащиеся в логическом операторе.
     */
    List<Term> getTerms();
}
