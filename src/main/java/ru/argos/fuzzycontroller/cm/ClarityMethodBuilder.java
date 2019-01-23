package ru.argos.fuzzycontroller.cm;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.function.Function;
import java.util.stream.DoubleStream;

/**
 * Статический класс в котором реализованы стандартные методы дефаззификации.
 *
 * @author a.k.pohresnyi
 */
public final class ClarityMethodBuilder {


    /**
     * Приватный конструктор.
     */
    private ClarityMethodBuilder() {

    }

    /**
     * Метод центра тяжести.
     *
     * @return Метод дефаззификации.
     */
    public static ClarityMethod centerOfGravity() {
        return variables -> {
            double numerator = 0.0, denominator = 0.0;

            for (ClarityVariable variable : variables) {
                numerator += variable.getProbability() * variable.getWeight();
                denominator += variable.getProbability();
            }

            return numerator / denominator;
        };
    }

    /**
     * Метод среднего максимума.
     *
     * @return Метод дефаззификации.
     */
    public static ClarityMethod averageMaximum() {
        return variables -> {
            double count = 0.0, amount = 0.0, max = max(variables);

            for (ClarityVariable variable : variables) {
                if (variable.getProbability() == max) {
                    amount += variable.getWeight();
                    count += 1.0;
                }
            }

            return (1.0 / count) * amount;
        };
    }

    /**
     * Метод левого максимума.
     *
     * @return Метод дефаззификации.
     */
    public static ClarityMethod leftMaximum() {
        return leftOrRightMaximum(DoubleStream::min);
    }

    /**
     * Метод правого максимума.
     *
     * @return Метод дефаззификации.
     */
    public static ClarityMethod rightMaximum() {
        return leftOrRightMaximum(DoubleStream::max);
    }

    /**
     * Метод левого/правого максимума.
     *
     * @return Метод дефаззификации.
     */
    private static ClarityMethod leftOrRightMaximum(Function<DoubleStream, OptionalDouble> function) {
        return variables -> {
            double max = max(variables);

            return function.apply(variables.stream()
                                           .filter(variable -> variable.getProbability() == max)
                                           .mapToDouble(ClarityVariable::getWeight))
                           .orElseThrow(IllegalAccessError::new);
        };
    }

    /**
     * Нахождение максимального значения функции принадлежности.
     *
     * @param variables Нечетких переменных.
     * @return Максимального значения функции принадлежности.
     */
    private static double max(List<ClarityVariable> variables) {
        return Collections.max(variables, Comparator.comparingDouble(ClarityVariable::getProbability)).getProbability();
    }
}
